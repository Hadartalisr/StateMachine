package code.models;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import code.abstracts.State;
import code.utils.ConsoleColors;

public class Machine {

	private State currentState;
	private String currentColor = ConsoleColors.getRandomColor();
	private List<State> states; // all of all the machine's states
	private boolean isRunning = false; // indicates if the machine is running

	public Machine(List<State> states, int startStateIndex) {
		Machine.validateMachineInput(states, startStateIndex);
		this.states = states;
		this.currentState = states.get(startStateIndex);
		System.out.println(this);
		this.printCurrentState();
		this.isRunning = true;
	}

	public void process(Object event) {
		int index = this.currentState.calculate(event);
		try {
			Class<? extends State> nextStateClass = this.currentState.getAllPossibleCalculations().get(index);
			this.updateState(nextStateClass);
		} catch (Exception e) { // if (index < 0) or (index > currentState.getAllPossibleCalculations().size())
			System.out.println(e.getMessage());
			System.out.println();
		} finally {
			this.printCurrentState();
		}
	}

	private void updateState(Class<? extends State> nextStateClass) {
		if (!nextStateClass.equals(this.currentState.getClass())) {
			this.currentColor = ConsoleColors.getRandomColor(this.currentColor);
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
		String decorator = String.format("\n%s\n", "*".repeat(100));
		String currentStateStr = String.format("Current state: %s.", currentState.getClass().getSimpleName());
		System.out.println(currentColor + decorator + currentStateStr + decorator + ConsoleColors.RESET);
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
