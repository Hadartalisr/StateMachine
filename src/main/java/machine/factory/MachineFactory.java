package machine.factory;

import java.util.Set;
import machine.models.Machine;
import machine.models.State;

public class MachineFactory {

	public Machine getMachine(Set<Class<? extends State>> statesClasses, Class<? extends State> entryStateClass) {
		return new MachineA(statesClasses, entryStateClass);
	}
	
}
