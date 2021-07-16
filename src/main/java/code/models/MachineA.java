package code.models;

import java.util.List;
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
		System.out.println(this);
		this.printCurrentState();
	}

	public void process(Object event) {
		Class<? extends State> nextStateClass = this.currentState.calculate(event);
		this.currentState = this.getStateByClass(nextStateClass);
		this.printCurrentState();
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < this.states.size(); i++) {
			String stateString = this.states.get(i).getClass().getSimpleName();
			String possibleStates = String.format(" -> [%s]\n", getpossibleStatesString(this.states.get(i)));
			stringBuilder.append(stateString).append(possibleStates);
		}
		return stringBuilder.toString();
	}

	public void printCurrentState() {
		System.out.println(String.format("Current state: %s.", currentState.getClass().getSimpleName()));
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

	private static String getpossibleStatesString(State state) {
		return String.join(", ", state.getAllPossibleCalculations().stream().map(Class::getSimpleName).toList());
	}

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
