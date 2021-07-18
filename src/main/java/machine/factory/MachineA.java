package machine.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;
import machine.models.Status;

class MachineA implements Machine {

	private State currentState;
	private Set<Class<? extends State>> statesClasses; // all the machine's states classes
	private boolean isRunning = false; // indicates if the machine is running

	/**
	 * The method construct a new machine. The machine is not yet running (use
	 * {@link #start()} and {@link #stop()} methods) .
	 * 
	 * @param statesClasses
	 * @param entryStateClass
	 */
	public MachineA(Set<Class<? extends State>> statesClasses, Class<? extends State> entryStateClass) {
		this.validateMachineInput(statesClasses, entryStateClass);
		this.statesClasses = statesClasses;
		this.currentState = this.getStateByClass(entryStateClass);
	}
	
	/*
	 * The method helps the machine constructor validate its input
	 */
	private void validateMachineInput(Set<Class<? extends State>> statesClasses, Class<? extends State> entryStateClass) {
		if (statesClasses == null || statesClasses.size() == 0) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
		if (entryStateClass == null) {
			throw new RuntimeException("ERROR - The entryState class should be a legal state class.");
		}
		boolean statesContainsEntryState = false;
		for(Class<? extends State> clazz : statesClasses) {
			if(clazz.equals(entryStateClass)) {
				statesContainsEntryState = true;
			}
		}
		if(!statesContainsEntryState) {
			throw new RuntimeException("ERROR - statesClasses should contain the entryStateClass.");
		}
		// TODO insert validation that i have in memory all the possible states
		// TODO insert validation that there all the states done have themselves in the
		// possible states list
	}
	
	@Override
	public void start() {
		this.isRunning = true;
	}

	@Override
	public void stop() {
		this.isRunning = false;
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
	public MachineProcessResponse process(Event<?> event) {
		Status status = Status.OK;
		String message = null;
		if (this.isRunning) { // if the machine is running we should process the event
			Class<? extends State> nextStateClass = this.currentState.calculate(event);
			this.updateState(nextStateClass);
		} else { // if the machine is not running
			status = Status.ERROR;
			message = "ERROR - the machine is in STOP mode.";
		}
		return new MachineProcessResponse(status, message, this.currentState);
	}


	private void updateState(Class<? extends State> nextStateClass) {
		if (!nextStateClass.equals(this.currentState.getClass())) {
			this.currentState = this.getStateByClass(nextStateClass);
		}
	}

	private State getStateByClass(Class<? extends State> stateClass) {
		State state = null;
		try {
			Constructor<? extends State> constructor = stateClass.getConstructor();
			state = constructor.newInstance(new Object[] {});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
		
	}



}
