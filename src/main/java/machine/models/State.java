package machine.models;

import java.util.List;

public abstract class State {
	
	public final String name = this.getClass().getSimpleName();
	
	/**
	 * The method calculates the state that the machine should be switched into.
	 * 
	 * @param event an object the machine will receive as input.
	 * @return an integer - the index in the returned value of
	 *         {@link #getAllPossibleCalculations()} method which the machine should
	 *         be switched into. returns Integer.MAX_VALUE in order to stay at the same state. 
	 */
	public abstract Class<? extends State> calculate(Event<?> event);

	@Override
	public abstract String toString();
	

}
