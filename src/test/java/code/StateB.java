package code;

import java.util.ArrayList;
import java.util.List;

import code.interfaces.State;

public class StateB extends State{

	List<Class<? extends State>> allPossibleCalculations;

	public StateB() {
		this.allPossibleCalculations = new ArrayList<>();
		this.allPossibleCalculations.add(StateA.class);
		this.allPossibleCalculations.add(StateB.class);
	}
	
	@Override
	public Class<? extends State> calculate(Object event) {
		if(event instanceof String && event.equals("b")) {
			return StateB.class;
		}
		return StateA.class;
	}

	@Override
	public List<Class<? extends State>> getAllPossibleCalculations() {
		return this.allPossibleCalculations;
	}

}
