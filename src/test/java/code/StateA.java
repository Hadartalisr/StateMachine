package code;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

import code.interfaces.State;

public class StateA extends State {

	List<Class<? extends State>> allPossibleCalculations;

	public StateA() {
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
