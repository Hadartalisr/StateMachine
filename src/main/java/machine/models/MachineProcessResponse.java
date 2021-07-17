package machine.models;

public class MachineProcessResponse {

	public Status status;

	public String message;

	public State currentState;

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
