package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import api.modules.utils.PositionUnityJson;

public class CollisionEventTest extends EventTypeTest{

	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	private static final int INSTANCE_ID = 9670;
	
	private List<String> listCardinals = new ArrayList<String>(Arrays.asList(AstraApi.CARDINAL_DIRECTION));
	
	public static final String eventOne = "{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	
	@Before
	public void setUp() {	
		super.setUp();
	}
	
	@Test
	public void singleCollisionSyncEventTest() {

		// Agent created
		String agent = createAgent();

		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] { eventOne });

		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);

		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));

		String astraCardinalDirection = values.getAstraCardinalDirection();

		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.SOUTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.WEST:
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		case AstraApi.EAST:
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		default:
		}

		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		// first time should be one of these three
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void twoCollisionSyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();

		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.SOUTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.WEST:
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		case AstraApi.EAST:
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		default:
		}
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
		
		//remove the one chosen
		listCardinals.remove(values.getAstraCardinalDirection());
		
		//collided again in the same object, coming from the same direction  
		syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		astraCardinalDirection = values.getAstraCardinalDirection();

		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.SOUTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.WEST:
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		case AstraApi.EAST:
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		default:
		}
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.size() == 2);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void multipleSyncCollisionUpdateRepeatTest() {
		//Agent created
		String agent = createAgent();
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
		singleCollisionSyncEventTest(agent);
	}
	
	@Test
	public void singleCollisionAsyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 10) {
			asyncEventPosition = api.receive(agent, EventType.COLLISION);
			if (asyncEventPosition != null) {
				break;
			} else {			
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			count ++;
		}
		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(asyncEventPosition, PositionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();

		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.SOUTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.WEST:
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		case AstraApi.EAST:
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		default:
		}
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	private void singleCollisionSyncEventTest(String agent) {		
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(listCardinals.contains(values.getCardinalDirection()));
		
		String astraCardinalDirection = values.getAstraCardinalDirection();

		// based on astra cardinal direction verify coordinates
		switch (astraCardinalDirection) {
		case AstraApi.NORTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.SOUTH:
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());
			assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			break;
		case AstraApi.WEST:
			assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		case AstraApi.EAST:
			assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
			assertEquals(Y, values.getY());
			assertEquals(Z, values.getZ());
			break;
		default:
		}
		
		assertFalse(astraCardinalDirection.equals(values.getCardinalDirection()));
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}	
}
