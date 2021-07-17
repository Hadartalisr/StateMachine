package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import code.abstracts.State;
import code.models.Machine;

class MainClassTest {

	private static final String ENTER_EVENT_STRING = "Enter event :";

	public static void main(String[] args) {
		List<State> states = new ArrayList<>();
		states.add(new StateA());
		states.add(new StateB());
		Machine machineA = new Machine(states, 0);
		///////////////////////

		Scanner scanner = new Scanner(System.in);
		System.out.println(ENTER_EVENT_STRING);
		while (scanner.hasNext() == true) {
			String input = scanner.next();
			machineA.process(input);
			System.out.println(ENTER_EVENT_STRING);
		}
		;
		scanner.close();
	}

}
