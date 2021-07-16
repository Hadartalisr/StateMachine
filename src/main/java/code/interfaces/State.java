package code.interfaces;

import java.util.List;

public abstract class State {
		
	/**
	 * The method calculates the next state which the machine should be switched to.
	 * @param <T> 
	 * @param event
	 * @return
	 */
	public abstract Class<? extends State> calculate(Object event);
	
	public abstract List<Class<? extends State>> getAllPossibleCalculations();
	
}
