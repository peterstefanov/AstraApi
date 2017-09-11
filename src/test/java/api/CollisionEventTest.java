package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import api.modules.Collision;
import api.modules.Position;
import api.modules.utils.CollisionUnityJson;

public class CollisionEventTest extends EventTypeTest{

	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	private static final int INSTANCE_ID = 9670;
	
	List<String> listCardinals = new ArrayList<String>(Arrays.asList(Collision.CARDINAL_DIRECTION));
	
	public static final String eventOne = "{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + Collision.SOUTH+"}";
	public static final String eventTwo = "{\"x\":-10.0,\"y\":0.5,\"z\":0.0,\"instanceId\":9696,\"cardinalDirection\":\"West\"}";
	
	@Before
	public void setUp() {	
		super.setUp();
	}
	
	@Test
	public void singleCollisionSyncEventTest() {
		String agent = api.createAgent("9670", "Player");
		
		//Agent created
		//String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.COLLISION, new Object[] {eventOne});		
		
		CollisionUnityJson values = (CollisionUnityJson) gson.fromJson(syncEventPosition, CollisionUnityJson.class);
		
		assertEquals(INSTANCE_ID, values.getInstanceId());
		assertTrue(listCardinals.contains(values.getCardinalDirection()));
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
}
