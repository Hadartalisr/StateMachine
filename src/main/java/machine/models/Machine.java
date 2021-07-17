package machine.models;

public interface Machine {
	
	public void start();

	public void stop();
	
	public MachineProcessResponse process(Event<?> event);
	
	public State getCurrentState();
	
}