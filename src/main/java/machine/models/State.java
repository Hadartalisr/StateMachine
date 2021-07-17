package machine.models;

import java.util.List;

public abstract class State {

	/**
	 * The method calculates the state that the machine should be switched into.
	 * 
	 * @param event an object the machine will receive as input.
	 * @return an integer - the index in the returned value of
	 *         {@link #getAllPossibleCalculations()} method which the machine should
	 *         be switched into
	 */
	public abstract int calculate(Object event);

	/**
	 * @return all the possible State classes which the machine is able to be
	 *         switched into
	 */
	public abstract List<Class<? extends State>> getAllPossibleCalculations();
	
	@Override
	public abstract String toString();
	

}
