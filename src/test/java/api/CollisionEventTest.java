package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import api.modules.Collision;
import api.modules.utils.CollisionUnityJson;

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
		
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		CollisionUnityJson values = (CollisionUnityJson) gson.fromJson(syncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
		assertFalse(values.getAstraCardinalDirection().equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	@Test
	public void twoCollisionSyncEventTest() {
		
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		CollisionUnityJson values = (CollisionUnityJson) gson.fromJson(syncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
		assertFalse(values.getAstraCardinalDirection().equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
		
		//remove the one chosen
		listCardinals.remove(values.getAstraCardinalDirection());
		
		//collided again in the same object, coming from the same direction  
		syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		values = (CollisionUnityJson) gson.fromJson(syncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
		assertFalse(values.getAstraCardinalDirection().equals(values.getCardinalDirection()));
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
		
		CollisionUnityJson values = (CollisionUnityJson) gson.fromJson(asyncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(AstraApi.SOUTH.equals(values.getCardinalDirection()));
		
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
		assertFalse(values.getAstraCardinalDirection().equals(values.getCardinalDirection()));
		//first time should be one of these three 
		listCardinals.remove(values.getCardinalDirection());
		assertTrue(listCardinals.size() == 3);
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}
	
	private void singleCollisionSyncEventTest(String agent) {		
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		CollisionUnityJson values = (CollisionUnityJson) gson.fromJson(syncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(listCardinals.contains(values.getCardinalDirection()));
		
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
		assertFalse(values.getAstraCardinalDirection().equals(values.getCardinalDirection()));
		assertTrue(listCardinals.contains(values.getAstraCardinalDirection()));
	}	
}
