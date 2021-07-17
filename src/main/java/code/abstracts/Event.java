package code.abstracts;

/**
 *  It is better for Event to be an interface rather than abstract class because 
 *  we would want to treat the events first of all by their object schema, and then as an input to out machine
 */
public interface Event {

	public Integer getID(); // each event should have his own ID
	
	public Integer getTypeID(); // each event should have a type 
	
	public Object getData(); // the actual data which is within the event 

}
