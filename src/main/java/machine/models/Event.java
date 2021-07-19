package machine.models;

import java.util.concurrent.atomic.AtomicLong;

public class Event<T>{

	private static final AtomicLong seq = new AtomicLong();
	private long id; 
	private Class<?> eventType; 
	private T eventData;
	
	public Event(T eventData){
		this.id = seq.getAndIncrement();
		this.eventData = eventData;
		this.eventType = eventData.getClass();
	}
	
	public long getId() {
		return id;
	}

	public Class<?> getEventType() {
		return eventType;
	}

	public T getEventData() {
		return eventData;
	}

}
