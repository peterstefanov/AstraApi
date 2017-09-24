package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AstraApi {
	
	public static final Double ONE = new Double("1");
	public static final Double API_CHANGE_RATE = new Double("0.7");
	public static final Double ZERO = new Double("0");
	public static final Double INITIAL_CHANGE = new Double("0.1");
	
	public static final String NORTH = "North";
	public static final String EAST = "East";
	public static final String SOUTH = "South";
	public static final String WEST = "West";
	public static final String[] CARDINAL_DIRECTION = { NORTH, EAST, SOUTH, WEST };
	public static final List<String> LIST_CARDINAL_DIRECTIONS = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
	
	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);
	
	public void submitCommand(String agentIdentifier, String eventIdentifier, String command);
	
	public String receive(String agentIdentifier, String eventIdentifier);
	
	public void clear(String agentIdentifier, String eventIdentifier);
	
}
