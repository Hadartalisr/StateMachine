package machine.models;

import java.util.concurrent.atomic.AtomicLong;

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
