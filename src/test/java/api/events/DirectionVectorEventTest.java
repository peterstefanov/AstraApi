package api.events;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.EventType;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class DirectionVectorEventTest extends EventTypeTest{
	  
	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
		
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventSouthTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ZERO, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventNorthTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ZERO, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ONE, position.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventWestTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.WEST + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(new Double(-AstraApi.ONE.doubleValue()), position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventEastTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(AstraApi.ONE, position.getX());
		assertEquals(AstraApi.ZERO, position.getY());		
		assertEquals(AstraApi.ZERO, position.getZ());
	}
}
