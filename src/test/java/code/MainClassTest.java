package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import code.abstracts.Event;
import code.abstracts.State;
import code.models.Machine;

class MainClassTest {

//	private static final String ENTER_EVENT_STRING = "Enter event :";

	public static void main(String[] args) {
		System.out.println("here");
		List<Event<Integer>> intEvents = new ArrayList<Event<Integer>>(); 
		for(int i = 0; i < 5; i++) {
			intEvents.add(new Event<Integer>(i));
		}
		List<Event<Integer>> strEvents = new ArrayList<Event<Integer>>(); 
		for(int i = 0; i < 5; i++) {
			strEvents.add(new Event<Integer>(i));
		}
		for(int i = 0; i < 5; i++) {
			long id = intEvents.get(i).getID();
			System.out.println(intEvents.get(i).getEventType() + " " + id);
		}
		System.out.println();
	}

}
