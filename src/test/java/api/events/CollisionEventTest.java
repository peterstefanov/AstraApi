package api.events;

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
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class CollisionEventTest extends EventTypeTest{

	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	
	private static final Double X_N = new Double("-1.649999976158142");
	private static final Double Y_N = new Double("-1.0");
	private static final Double Z_N = new Double("-2.700000047683716");

	private static final int INSTANCE_ID = 9670;
	
	private List<String> listCardinals = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
	
	public static final String SOUTH_COLLISION = "{\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NORTH_COLLISION = "{\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	
	public static final String NEGATIVE_SOUTH_COLLISION = "{\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NEGATIVE_NORTH_COLLISION = "{\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	@Before
	public void setUp() {	
		super.setUp();
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
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});	
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION});
		getEventResponse(agent, listEvents);
		
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
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});	
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});	
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NEGATIVE_SOUTH_COLLISION});
		getEventResponse(agent, listEvents);
		
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
	
	private void getEventResponse(String agent, LinkedList<String> listEvents) {
		String asyncEventPosition = null;
		int count = 0;
		while(count < 5) {
			asyncEventPosition = api.receive(agent, EventType.COLLISION);
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
}
