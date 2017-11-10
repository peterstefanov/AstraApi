package api.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.EventType;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class PositionVectorEventTest extends EventTypeTest{
	
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventSouthTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ZERO, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventNorthTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ZERO, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ONE, position.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventWestTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.WEST + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventEastTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ONE, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
	}
	
	@Test
	public void singlePositionVectorAsyncEventTest() {
		//Agent created
		String agent = createAgent();			
		
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}";			
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});	
				
		String asyncEventPosition = null;
		int count = 0;
		while(count < 5) {
			asyncEventPosition = api.receive(agent, EventType.POSITION_VECTOR);
			if (asyncEventPosition != null) {
				break;
			} else {			
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			count ++;
		}
		
		UnityJson json = (UnityJson) gson.fromJson(asyncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ZERO, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ONE, position.getZ());
	}
	
	@Test
	public void multiplePositionVectorSyncEventChangeDirectionTest() {
		//Agent created
		String agent = createAgent();			
		UnityJson values= null;
		String syncEventPosition = null;
		
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";			
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = values.getPosition();
		
		assertEquals(AstraApi.ONE, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());

		//verify next one won't change as x coordinates increased  
		event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_1 + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";			
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		position = values.getPosition();
		
		assertEquals(AstraApi.ONE, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
		
		//verify x coordinates is negative as x decreased  
		event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";			
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {event});						
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		position = values.getPosition();
		
		assertEquals(new Double(AstraApi.ONE.doubleValue()), position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
	}
	
	@Test
	public void multipleAsyncPositionVectorUpdateNorthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();
			
			assertEquals(AstraApi.ZERO, position.getX());
			assertEquals(AstraApi.ZERO, position.getY());		
			if (countItems == 0) {
				assertEquals(AstraApi.ONE, position.getZ());
			} else if (countItems == 1) {
				assertEquals(AstraApi.ONE, position.getZ());
			} else if (countItems == 2) {
				assertEquals(AstraApi.ONE, position.getZ());
			} else if (countItems == 3) {
				assertEquals(AstraApi.ONE, position.getZ());
			} else if (countItems == 4) {
				assertEquals(AstraApi.ONE, position.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionVectorUpdateSouthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();
			
			assertEquals(AstraApi.ZERO, position.getX());
			assertEquals(AstraApi.ZERO, position.getY());		
			if (countItems == 0) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionVectorDecreaseUpdateSouthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();
			
			assertEquals(AstraApi.ZERO, position.getX());
			assertEquals(AstraApi.ZERO, position.getY());		
			if (countItems == 0) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(AstraApi.ONE.doubleValue()), position.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionVectorUpdateSouthNoChangeTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();
			
			assertEquals(AstraApi.ZERO, position.getX());
			assertEquals(AstraApi.ZERO, position.getY());			
			if (countItems == 0) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateRepeatTest() {
		multipleAsyncPositionVectorUpdateNorthTest();
		multipleAsyncPositionVectorUpdateNorthTest();
		multipleAsyncPositionVectorUpdateNorthTest();
	}
}
