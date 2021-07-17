package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import code.abstracts.Event;
import code.abstracts.State;

class MainClassTest {

//	private static final String ENTER_EVENT_STRING = "Enter event :";

	public static void main(String[] args) {
		System.out.println("here");
		List<Event<Integer>> intEvents = new ArrayList<Event<Integer>>(); 
		for(int i = 0; i < 5; i++) {
			intEvents.add(new Event<Integer>(i));
		}
		List<Event<String>> strEvents = new ArrayList<Event<String>>(); 
		for(int i = 0; i < 5; i++) {
			strEvents.add(new Event<String>((char)(i+40)+""));
		}
		for(int i = 0; i < 5; i++) {
			long id = intEvents.get(i).getID();
			System.out.println("id: " + id + ", eventType: " + intEvents.get(i).getEventType() + " ,data: " + intEvents.get(i).getEventData());
		}
		for(int i = 0; i < 5; i++) {
			long id = strEvents.get(i).getID();
			System.out.println("id: " + id + ", eventType: " + strEvents.get(i).getEventType() + " ,data: " + strEvents.get(i).getEventData());
		}
		System.out.println();
	}

}
