package api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import api.modules.utils.PositionUnityJson;

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
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.ZERO_CHANGE, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventNorthTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.ZERO_CHANGE, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.DIRECTION, values.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventWestTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
	}
	
	@Test
	public void initialSingleDirectionvectorSyncEventEastTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.DIRECTION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.DIRECTION, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
	}
}
