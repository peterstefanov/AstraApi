package api.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.EventType;
import api.modules.utils.UnityJson;

public class MessageEventTypeTest extends EventTypeTest {

	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	
	@Before
	public void setUp() {	
		super.setUp();
	}

	@Test
	public void messageSentFromAstraOnceConditionsAreMetTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		String agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		
		LinkedList<String> listEvents = new LinkedList<String>();
		LinkedList<String> listMessageEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 351; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + (Z + i) + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);		
		}			
		assertTrue(listEvents.size() == 353);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);
		assertTrue(listMessageEvents.size() == 1);
		String message = listMessageEvents.getFirst();
		UnityJson values = (UnityJson) gson.fromJson(message, UnityJson.class);		
		assertEquals("Ready", values.getMessage());
	}
	
	@Test
	public void messageDoesNotSentFromAstraOnceConditionsAreNotMetTest() {
			
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
		assertTrue(listEvents.size() == 7);
		
		getEventResponse(agentSmartyPans, listMessageEvents, EventType.MESSAGE);

		assertTrue(listMessageEvents.size() == 0);
	}
	
	private void getEventResponse(String agent, LinkedList<String> listEvents, String eventType) {
		String asyncEventPosition = null;
		int count = 0;
		while(count < 5) {
			asyncEventPosition = api.receive(agent, eventType);
			if (asyncEventPosition == null) {			
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			if (asyncEventPosition != null) {
				listEvents.add(asyncEventPosition);
				if (listEvents.size() == 3) {
					break;
				}
			}
			count ++;
		}
	}
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "GridMapGenerator");
	}
}
