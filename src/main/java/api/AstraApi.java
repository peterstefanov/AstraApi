package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AstraApi {

	public static final String AGENT_ALREADY_EXISTS = "Agent with this name already exists!";
	public static final String AGENT_DOES_NOT_EXISTS = "Agent with this name does not exists!";
	public static final String AGENT_CREATION_FAILS = "Agent cannot be created at the moment!";
	public static final String AGENT_CREATION_NO_SUCH_CLASS = "No such Class provided by the Api!";
	public static final String AGENT_TERMINATED = "Agent was removed from registry!";
	public static final String AGENT_NOT_TERMINATED = "Agent cannot be removed from registry!";

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

	/**
	 * Create an Agent with a given name and Class name to match the desirable
	 * capabilities of this Agent. The methods allows to verify if Agent was
	 * successful created by returning the provided Agent name. If Agent with the
	 * same name already exists or an Exception is thrown during the the process of
	 * Agent creation an appropriate message is returned.
	 * 
	 * @param name
	 *            - Agent identifier
	 * @param className
	 *            - Class name for this Agent
	 * @return name if created successful and a message if agent with the same name
	 *         already exists
	 */
	public String createAgent(String name, String className);

	/**
	 * Ability to terminate and remove Agent form the registry. If Agent exists in
	 * registry, will be removed and appropriate message is sent back. If no Agent
	 * with this name in registry, no action taken and appropriate message is
	 * returned.
	 * 
	 * @param name
	 *            - Agent identifier
	 * @return message if removal of the Agent was successful
	 */
	public String removeAgent(String name);

	/**
	 * Add an asynchronous event from Unity to an Agent based on the type. The type
	 * used in Unity should match the one supported by the API. If the agent doesn't
	 * exists bind the event to the current agent. Event is added to the Queue and
	 * once is processed can be retrieved form the Queue using {@link #receive} 
	 */
	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);

	/**
	 * Add synchronous event from Unity to an Agent based on the type. The type
	 * used in Unity should match the one supported by the API. If the agent doesn't
	 * exists bind the event to the current agent. The method returns the processed event. 
	 */
	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] eventArgs);

	public void submitCommand(String agentIdentifier, String eventIdentifier, String command);

	/**
	 * Retrieve an event form the Queue stored in a Map with a key the Agent identifier concatenated with the Event type.
	 * @param agentIdentifier
	 * @param eventIdentifier
	 * @return Json format of the processed event.
	 */
	public String receive(String agentIdentifier, String eventIdentifier);

	/**
	 * Remove a specified event from the agents event Map, if such entry exists in
	 * the Map, by removing all of the elements from Event Queue. The Event Queue
	 * will be empty after this method returns.
	 * 
	 * @param agentIdentifier
	 * @param eventIdentifier
	 */
	public void clear(String agentIdentifier, String eventIdentifier);

}
