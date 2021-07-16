package code.models;

import java.util.List;
import code.interfaces.Machine;
import code.interfaces.State;

public class MachineA {
	
	private Class<? extends State> currentState;
	private List<State> states;


	public MachineA(List<State> states, int StartState) {
		// some validation on the input
		
	}

	public String toString() {
		String statesString = String.join("\n", this.states.stream().map(State::toString).toList());
		return String.format("[\n %s \n]", statesString);
	}

	// nice to have a matrix to string



}
