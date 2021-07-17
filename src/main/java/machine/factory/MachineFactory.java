package machine.factory;

import java.util.List;

import machine.models.Machine;
import machine.models.State;

public class MachineFactory {

	public Machine getMachineA(List<State> states, int startStateIndex) {
		return new MachineA(states, startStateIndex);
	}
	
}
