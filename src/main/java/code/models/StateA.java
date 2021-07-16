package code.models;

import code.interfaces.State;

public class StateA extends State{


	@Override
	public Class<? extends State> calculate(Object event) {
		// TODO Auto-generated method stub
		return StateA.class;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


}
