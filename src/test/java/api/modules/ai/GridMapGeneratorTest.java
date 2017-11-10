package api.modules.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.EventType;
import api.events.EventTypeTest;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class GridMapGeneratorTest extends EventTypeTest {	
	
	private List<String> listCardinals = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));

	@Before
	public void setUp() {	
		super.setUp();
	}
	
	@Test
	public void multipleAsyncPositionVectorMovingNorthTest() {
			
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
	
	@Test
	public void singleSouthCollisionSyncEventTest() {

		// Agent created
		String agent = createAgent();

		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] { SOUTH_COLLISION });

		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);

		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));

		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		// first time should be one of these three
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void singleNegativeSouthCollisionSyncEventTest() {

		// Agent created
		String agent = createAgent();

		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] { NEGATIVE_SOUTH_COLLISION });

		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);

		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));

		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertNegativeSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		// first time should be one of these three
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void singleNorthCollisionSyncEventTest() {

		// Agent created
		String agent = createAgent();

		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] { NORTH_COLLISION });

		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);

		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.NORTH.equals(values.getCardinalDirection()));

		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertNorthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		// first time should be one of these three
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void singleNegativeNorthCollisionSyncEventTest() {

		// Agent created
		String agent = createAgent();

		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] { NEGATIVE_NORTH_COLLISION });

		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);

		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.NORTH.equals(values.getCardinalDirection()));

		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertNegativeNorthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		// first time should be one of these three
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void twoSouthCollisionSyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});		
		
		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();

		assertSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
		
		//remove the one chosen
		listCardinals.remove(values.getAstraCardinalDirection());
		
		//collided again in the same object, coming from the same direction  
		syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});		
		
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		astraCardinalDirection = values.getAstraCardinalDirection();

		assertSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.size() == 2);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void twoNorthCollisionSyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION});		
		
		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.NORTH.equals(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();

		assertNorthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
		
		//remove the one chosen
		listCardinals.remove(values.getAstraCardinalDirection());
		
		//collided again in the same object, coming from the same direction  
		syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION});		
		
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.NORTH.equals(values.getCardinalDirection()));
		
		astraCardinalDirection = values.getAstraCardinalDirection();

		assertNorthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.size() == 2);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void multipleSouthSyncCollisionUpdateRepeatTest() {
		//Agent created
		String agent = createAgent();
		
		singleSouthCollisionSyncEventTest(agent);
		singleSouthCollisionSyncEventTest(agent);
		singleSouthCollisionSyncEventTest(agent);
		singleSouthCollisionSyncEventTest(agent);
		singleSouthCollisionSyncEventTest(agent);
	}
	
	@Test
	public void multipleNegativeSouthSyncCollisionUpdateRepeatTest() {
		//Agent created
		String agent = createAgent();
		
		singleNegativeSouthCollisionSyncEventTest(agent);
		singleNegativeSouthCollisionSyncEventTest(agent);
		singleNegativeSouthCollisionSyncEventTest(agent);
		singleNegativeSouthCollisionSyncEventTest(agent);
		singleNegativeSouthCollisionSyncEventTest(agent);
	}
	
	@Test
	public void singleCollisionAsyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});		
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 5) {
			asyncEventPosition = api.receive(agent, EventType.COLLISION);
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
		
		UnityJson values = (UnityJson) gson.fromJson(asyncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();
		assertSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void multipleSouthCollisionAsyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});
		getEventResponse(agent, listEvents, EventType.COLLISION);
		
		assertTrue(listEvents.size() == 5);
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			assertEquals(INSTANCE_ID, values.getInstanceId());
			assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
			
			String astraCardinalDirection = values.getAstraCardinalDirection();
			assertSouthCollisionAstraResponse(values, astraCardinalDirection);
			
			assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));

		}
	}
	
	@Test
	public void multipleNegativeSouthCollisionAsyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});	
		getEventResponse(agent, listEvents, EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});
		getEventResponse(agent, listEvents, EventType.COLLISION);
		
		assertTrue(listEvents.size() == 5);
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			assertEquals(INSTANCE_ID, values.getInstanceId());
			assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
			
			String astraCardinalDirection = values.getAstraCardinalDirection();
			assertNegativeSouthCollisionAstraResponse(values, astraCardinalDirection);
			
			assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));

		}
	}
	
	private void singleSouthCollisionSyncEventTest(String agent) {		
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});		
		
		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(listCardinals.contains(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	private void singleNegativeSouthCollisionSyncEventTest(String agent) {		
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});		
		
		UnityJson values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(listCardinals.contains(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();
		
		assertNegativeSouthCollisionAstraResponse(values, astraCardinalDirection);
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	private void assertSouthCollisionAstraResponse(UnityJson values, String astraCardinalDirection) {
		
		Position position = values.getPosition();	
		
		int assertTest = 0;
		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertTest ++;
			assertEquals(X, position.getX());
			assertEquals(Y, position.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), position.getZ());
			break;
		case AstraApi.WEST:
			assertTest ++;
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y, position.getY());
			assertEquals(Z, position.getZ());
			break;
		case AstraApi.EAST:
			assertTest ++;
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y, position.getY());
			assertEquals(Z, position.getZ());
			break;
		default:
		}

		assertTrue(assertTest == 1);
	}
	
	private void assertNegativeSouthCollisionAstraResponse(UnityJson values, String astraCardinalDirection) {
		
		Position position = values.getPosition();	
		
		int assertTest = 0;
		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertTest ++;
			assertEquals(X_N, position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(new Double(Z_N + AstraApi.API_CHANGE_RATE), position.getZ());
			break;
		case AstraApi.WEST:
			assertTest ++;
			assertEquals(new Double(X_N - AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(Z_N, position.getZ());
			break;
		case AstraApi.EAST:
			assertTest ++;
			assertEquals(new Double(X_N + AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(Z_N, position.getZ());
			break;
		default:
		}

		assertTrue(assertTest == 1);
	}
	
	private void assertNorthCollisionAstraResponse(UnityJson values, String astraCardinalDirection) {
		
		Position position = values.getPosition();	
		
		int assertTest = 0;
		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.SOUTH:
			assertTest ++;
			assertEquals(X, position.getX());
			assertEquals(Y, position.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), position.getZ());
			break;
		case AstraApi.WEST:
			assertTest ++;
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y, position.getY());
			assertEquals(Z, position.getZ());
			break;
		case AstraApi.EAST:
			assertTest ++;
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y, position.getY());
			assertEquals(Z, position.getZ());
			break;
		default:
		}

		assertTrue(assertTest == 1);
	}
	
	private void assertNegativeNorthCollisionAstraResponse(UnityJson values, String astraCardinalDirection) {
		
		Position position = values.getPosition();	
		
		int assertTest = 0;
		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.SOUTH:
			assertTest ++;
			assertEquals(X_N, position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), position.getZ());
			break;
		case AstraApi.WEST:
			assertTest ++;
			assertEquals(new Double(X_N - AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(Z_N, position.getZ());
			break;
		case AstraApi.EAST:
			assertTest ++;
			assertEquals(new Double(X_N + AstraApi.API_CHANGE_RATE), position.getX());
			assertEquals(Y_N, position.getY());
			assertEquals(Z_N, position.getZ());
			break;
		default:
		}

		assertTrue(assertTest == 1);
	}	
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "GridMapGenerator");
	}
}
