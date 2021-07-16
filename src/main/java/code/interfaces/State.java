package code.interfaces;

public abstract class State {
	
	public abstract String toString();
	
	/**
	 * The method calculates the next state which the machine should be switched to.
	 * @param <T> 
	 * @param event
	 * @return
	 */
	public abstract Class<? extends State> calculate(Object event);
	
}
