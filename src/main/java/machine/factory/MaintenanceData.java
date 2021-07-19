package machine.factory;

public class MaintenanceData {
	
	public String stateClassName;
	public String lastEvent;
	
	public MaintenanceData() {
		
	}

	public MaintenanceData(String stateClassName, String lastEvent) {
		this.stateClassName = stateClassName;
		this.lastEvent = lastEvent;
	}
}
