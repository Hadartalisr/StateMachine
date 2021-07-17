package machine.factory;

import java.util.List;

import machine.models.Event;
import machine.models.Machine;
import machine.models.MachineProcessResponse;
import machine.models.State;
import machine.models.Status;

class MachineA implements Machine {

	private State currentState;
	private List<State> states; // all of all the machine's states
	private boolean isRunning = false; // indicates if the machine is running

	/**
	 * The method construct a new machine. The machine is not yet running (use
	 * {@link #start()} and {@link #stop()} methods) .
	 * 
	 * @param states
	 * @param startStateIndex
	 */
	public MachineA(List<State> states, int startStateIndex) {
		MachineA.validateMachineInput(states, startStateIndex);
		this.states = states;
		this.currentState = states.get(startStateIndex);
		System.out.println(this);
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
	public State getCurrentState() {
		return this.currentState;
	}

	@Override
	public MachineProcessResponse process(Event<?> event) {
		Status status = Status.OK;
		String message = null;
		if (this.isRunning) { // if the machine is running we should process the event
			int index = this.currentState.calculate(event);
			try {
				Class<? extends State> nextStateClass = this.currentState.getAllPossibleCalculations().get(index);
				this.updateState(nextStateClass);
			} catch (Exception e) { // if (index < 0) or (index > currentState.getAllPossibleCalculations().size())
				message = e.getMessage();
			}
		} else { // if the machine is not running
			status = Status.ERROR;
			message = "ERROR - the machine is in STOP mode.";
		}
		return new MachineProcessResponse(status, message, this.currentState);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < this.states.size(); i++) {
			String stateString = this.states.get(i).getClass().getSimpleName();
			String possibleStates = String.format(" -> [%s]\n", getpossibleStatesString(this.states.get(i)));
			stringBuilder.append(stateString).append(possibleStates);
		}
		return stringBuilder.toString();
	}

	private void updateState(Class<? extends State> nextStateClass) {
		if (!nextStateClass.equals(this.currentState.getClass())) {
//			this.currentColor = ConsoleColors.getRandomColor(this.currentColor);
			this.currentState = this.getStateByClass(nextStateClass);
		}
	}

	private State getStateByClass(Class<? extends State> stateClass) {
		for (State state : this.states) {
			if (state.getClass().equals(stateClass)) {
				return state;
			}
		}
		String errorString = String.format(
				"ERROR - %s's calculation method returned a class which was not included at the getAllPossibleCalculations",
				stateClass.toString());
		throw new RuntimeException(errorString);
	}

	// static methods

	/**
	 * Static method which receives State and generates a string of all the
	 * accessible states for the machine by one event.
	 * 
	 * @param state
	 * @return
	 */
	private static String getpossibleStatesString(State state) {
		return String.join(", ", state.getAllPossibleCalculations().stream().map(Class::getSimpleName).toList());
	}

	/*
	 * Static method which helps the machine constructor validate its input
	 */
	private static void validateMachineInput(List<State> states, int startStateIndex) {
		if (states == null || states.size() == 0) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
		if (startStateIndex < 0 || startStateIndex > states.size() - 1) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
		// TODO insert validation that i have in memory all the possible states
	}

}
