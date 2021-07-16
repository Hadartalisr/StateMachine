package code.models;

import java.util.List;
import code.interfaces.Machine;
import code.interfaces.State;

public class MachineA {

	private State currentState;
	private List<State> states;
	private boolean isRunning = true;

	public MachineA(List<State> states, int startStateIndex) {
		MachineA.validateMachineInput(states, startStateIndex);
		this.states = states;
		this.currentState = states.get(startStateIndex);
		this.isRunning = true;
		this.printCurrentState();
	}

	public void process(Object event) {
		Class<? extends State> nextStateClass = this.currentState.calculate(event);
		this.currentState = this.getStateByClass(nextStateClass);
		this.printCurrentState();
	}
	
	public String toString() {
		String statesString = String.join("\n", this.states.stream().map(State::toString).toList());
		return String.format("[\n %s \n]", statesString);
	}

	private State getStateByClass(Class<? extends State> stateClass) {
		for (State state : this.states) {
			if (state.getClass().equals(stateClass)) {
				return state;
			}
		}
		String errorString = String.format("ERROR - %s's calculation method returned a class which was not included at the getAllPossibleCalculations", stateClass.toString());
		throw new RuntimeException(errorString);
	}
	
	private void printCurrentState() {
		System.out.println(String.format("Current state: %s.", currentState.getClass().getSimpleName()));
	}


	// nice to have a matrix to string

	// static methods

	private static void validateMachineInput(List<State> states, int startStateIndex) {
		if (states == null || states.size() == 0) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
		if (startStateIndex < 0 || startStateIndex > states.size() - 1) {
			throw new RuntimeException("ERROR - There must be at least one state in the machine.");
		}
		// TODO insert validation on the output of each state
	}

}
