package machine.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;
import machine.models.Status;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

class MachineA implements Machine {

	private State currentState;
	private Set<Class<? extends State>> statesClasses; // all the machine's states classes
	private boolean isRunning = false; // indicates if the machine is running
	private String lastEvent;
	private final String maintenanceConfigLocation = "C:\\Dev\\StateMachineImplementation\\state.json";

	/**
	 * The method construct a new machine. The machine is not yet running (use
	 * {@link #start()} and {@link #stop()} methods) .
	 * 
	 * @param statesClasses
	 * @param entryStateClass
	 */
	public MachineA(Set<Class<? extends State>> statesClasses) {
		this.validateMachineInput(statesClasses);
		this.statesClasses = statesClasses;
	}

	/*
	 * The method helps the machine constructor validate its input
	 */
	private void validateMachineInput(Set<Class<? extends State>> statesClasses) {
		if (statesClasses == null || statesClasses.size() == 0) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
	}

	@Override
	public void start(Class<? extends State> entryStateClass) {
		if (entryStateClass == null) {
			throw new RuntimeException("ERROR - The entryState class should be a legal state class.");
		}
		boolean statesContainsEntryState = false;
		for (Class<? extends State> clazz : statesClasses) {
			if (clazz.equals(entryStateClass)) {
				statesContainsEntryState = true;
			}
		}
		if (!statesContainsEntryState) {
			throw new RuntimeException("ERROR - statesClasses should contain the entryStateClass.");
		}
		this.updateState(entryStateClass);
		this.isRunning = true;
	}

	@Override
	public void stop() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			MaintenanceData maintenanceData = new MaintenanceData(this.currentState.getClass().getName(),
					this.lastEvent);
			mapper.writeValue(new File(maintenanceConfigLocation), maintenanceData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.isRunning = false;
	}

	@Override
	public void startAfterMaintenance() {
		//TODO throw exception if the machine is already running
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File(this.maintenanceConfigLocation);
			MaintenanceData maintenanceData = mapper.readValue(file, MaintenanceData.class);
			//TODO add try catch
			@SuppressWarnings("unchecked")
			Class<? extends State> stateClass = (Class<? extends State>) Class.forName(maintenanceData.stateClassName);
			this.currentState = this.getStateByClass(stateClass);
			this.lastEvent = maintenanceData.lastEvent;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.isRunning = true;
	}

	@Override
	public Boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public State getCurrentState() {
		return this.currentState;
	}

	@Override
	public Set<Class<? extends State>> getAllStates() {
		return this.statesClasses;
	}

	@Override
	public MachineProcessResponse process(Event<?> event) {
		this.updateLastEvent(event);
		Status status = Status.OK;
		String message = null;
		if (this.isRunning) { // if the machine is running we should process the event
			Class<? extends State> nextStateClass = this.currentState.calculate(event);
			this.updateState(nextStateClass); // TODO handle unknown class
		} else { // if the machine is not running
			status = Status.ERROR;
			message = "ERROR - the machine is in STOP mode.";
		}
		return new MachineProcessResponse(status, message, this.currentState);
	}

	private void updateState(Class<? extends State> nextStateClass) {
		if (this.currentState == null || !nextStateClass.equals(this.currentState.getClass())) {
			this.currentState = this.getStateByClass(nextStateClass);
		}
	}

	private void updateLastEvent(Event<?> event) {
		this.lastEvent = new Gson().toJson(event.getEventData());
	}

	private State getStateByClass(Class<? extends State> stateClass) { // TODO add data to object initialization
		State state = null;
		try {
			Constructor<? extends State> constructor = stateClass.getConstructor();
			state = constructor.newInstance(new Object[] {});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

}
