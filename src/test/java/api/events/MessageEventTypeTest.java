package api.events;

import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.EventType;
import api.modules.utils.UnityJson;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageEventTypeTest extends EventTypeTest {
	
	public static final int INSTANCE_ID = 7777777;
	
	public static final String SOUTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NORTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String EAST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String WEST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";
	
	@Before
	public void setUp() {	
		super.setUp();
	}

	@Test
	public void messageFromAstraOnceConditionsAreMetTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		String agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		
		LinkedList<String> listEvents = new LinkedList<String>();
		LinkedList<String> listMessageEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 5; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z + i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}
		for (int i = 0; i <= 4; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z_N - i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}
		
		assertEquals(listEvents.size(), 12);
		
		//add to collisions event with instanceId 7777777 to satisfy the requirements for sending message
		//should be more than 2 events with instanceId 7777777
		LinkedList<String> listCollisionsEvents = new LinkedList<String>();
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION});	
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION});
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		
		assertEquals(listCollisionsEvents.size(), 3);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);
		assertTrue(listMessageEvents.size() == 1);
		String message = listMessageEvents.getFirst();
		UnityJson values = (UnityJson) gson.fromJson(message, UnityJson.class);		
		assertEquals("Ready", values.getMessage());
	}
	
	@Test
	public void messageNotSentFromAstraNoInstanceIdTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		String agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		
		LinkedList<String> listEvents = new LinkedList<String>();
		LinkedList<String> listMessageEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 3; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z + i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}			
		assertTrue(listEvents.size() == 5);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);

		assertTrue(listMessageEvents.size() == 0);
	}
	
	@Test
	public void messageNotSentFromAstraOnkyOneInstanceIdTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		String agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		
		LinkedList<String> listEvents = new LinkedList<String>();
		LinkedList<String> listMessageEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 3; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z + i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}			
		assertTrue(listEvents.size() == 5);
		
		//only one instanceId 7777777
		LinkedList<String> listCollisionsEvents = new LinkedList<String>();
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		
		assertEquals(listCollisionsEvents.size(), 1);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);

		assertTrue(listMessageEvents.size() == 0);
	}
	
	@Test
	public void messageNotSentFromAstraOnlyTwoInstanceIdTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		String agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		
		LinkedList<String> listEvents = new LinkedList<String>();
		LinkedList<String> listMessageEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 3; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z + i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}			
		assertTrue(listEvents.size() == 5);
		
		//only two instanceId 7777777
		LinkedList<String> listCollisionsEvents = new LinkedList<String>();
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION});	
		getEventResponse(agent, listCollisionsEvents, EventType.COLLISION);
		
		assertEquals(listCollisionsEvents.size(), 2);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);

		assertTrue(listMessageEvents.size() == 0);
	}
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "GridMapGenerator");
	}
}
