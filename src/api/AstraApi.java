package api;

public interface AstraApi {
	
	public static final String EVENT_TYPE_POSITION = "position";
	
	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public void submitCommand(String jsonEvent);
	
	public String receive();
}
