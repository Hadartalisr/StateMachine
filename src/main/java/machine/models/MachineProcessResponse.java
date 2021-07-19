package machine.models;

public class MachineProcessResponse {

	private Status status;
	private String message;
	private State currentState;

	public MachineProcessResponse(Status status, String message, State currentState) {
		this.status = status;
		this.message = message;
		this.currentState = currentState;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public State getCurrentState() {
		return currentState;
	}

}
