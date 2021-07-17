package code.models;

import java.util.List;

import code.abstracts.State;
import code.abstracts.Machine;

public class MachineFactory {

	public Machine getMachine(List<State> states, int startStateIndex) {
		return new MachineA(states, startStateIndex);
	}
	
}
