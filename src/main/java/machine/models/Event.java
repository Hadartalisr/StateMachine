package machine.models;

import java.util.concurrent.atomic.AtomicLong;

/**
 *  It is better for Event to be an interface rather than abstract class because 
 *  we would want to treat the events first of all by their object schema, and then as an input to out machine
 */
public class Event<T>{

	private static final AtomicLong seq = new AtomicLong();
	private long ID; 
	private Class<?> eventType; 
	private T eventData;
	
	public Event(T eventData){
		this.ID = seq.getAndIncrement();
		this.eventData = eventData;
		this.eventType = eventData.getClass();
	}
	
	public long getID() {
		return ID;
	}

	public Class<?> getEventType() {
		return eventType;
	}

	public T getEventData() {
		return eventData;
	}
	

}
