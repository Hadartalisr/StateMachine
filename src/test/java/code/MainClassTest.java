package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import code.interfaces.State;
import code.models.MachineA;

@SpringBootTest
class MainClassTest {

	public static void main(String[] args) {
		List<State> states = new ArrayList<>();
		states.add(new StateA());
		states.add(new StateB());
		MachineA machineA = new MachineA(states, 0);
		///////////////////////
		
		
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext() == true) {
			System.out.println("Enter event:");
			String s1 = sc.next();
			machineA.process(s1);
			System.out.println();
		}
	}

}
