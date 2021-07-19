package machine.models;

import java.util.Set;

public interface Machine {
	
	public void start(Class<? extends State> entryStateClass);

	public void stop();
	
	public void startAfterMaintenance();
	
	public Boolean isRunning();
	
	public MachineProcessResponse process(Event<?> event);
	
	public State getCurrentState();
	
	public Set<Class<? extends State>> getAllStates();
	
}
