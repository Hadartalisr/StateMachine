package code.models;

public class MachineProcessResponse {

	public Status status;

	public String message;

	public MachineProcessResponse(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public enum Status {
		OK, ERROR
	}

}
