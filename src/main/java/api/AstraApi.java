package api;

public interface AstraApi {
		
	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public void submitCommand(String agentIdentifier, String eventIdentifier, String command);
	
	public String receive(String agentIdentifier, String eventIdentifier);
}
