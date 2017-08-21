package api;

public interface AstraApi {
	
	public void asyncEvent(String greetings, Object[] args);
	
	public String syncEvent(String greetings, Object[] args);
	
	public void submitCommand(String jsonEvent);
	
	public String receive();
}
