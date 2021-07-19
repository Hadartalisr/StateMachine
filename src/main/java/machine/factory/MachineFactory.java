package machine.factory;

import java.util.Set;
import machine.models.Machine;
import machine.models.State;

public class MachineFactory {

	public Machine getMachine(Set<Class<? extends State>> statesClasses) {
		return new MachineA(statesClasses);
	}
	
}
