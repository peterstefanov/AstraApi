package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.modules.utils.PositionUnityJson;

public class PositionVectorEventTest extends EventTypeTest{

	  
	private static final Double X = new Double("1.649999976158142");
	private static final Double X_1 = new Double("2.849999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	private static final Double Z_1 = new Double("3.900000047683716");
	private static final Double Z_2 = new Double("4.000000047683716");
	private static final Double Z_3 = new Double("5.100000047683716");
	private static final Double Z_4 = new Double("6.200000047683716");
	
	private static final Double Z_N = new Double("-2.700000047683716");
	private static final Double Z_1_N = new Double("-3.900000047683716");
	private static final Double Z_2_N = new Double("-4.000000047683716");
	private static final Double Z_3_N = new Double("-5.100000047683716");
	private static final Double Z_4_N = new Double("-6.200000047683716");
	
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventSouthTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.ZERO_CHANGE, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventNorthTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.ZERO_CHANGE, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.DIRECTION, values.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventWestTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
	}
	
	@Test
	public void initialSinglePositionVectorSyncEventEastTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.DIRECTION, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
	}
	
	@Test
	public void singlePositionVectorAsyncEventTest() {
		//Agent created
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});	
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 20) {
			asyncEventPosition = api.receive(agent, EventType.POSITION_VECTOR);
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
		assertEquals(AstraApi.ZERO_CHANGE, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.DIRECTION, values.getZ());
	}
	
	//@Test
	public void multiplePositionVectorSyncEventChangeDirectionTest() {
		//Agent created
		String agent = createAgent();			
		PositionUnityJson values= null;
		String syncEventPosition = null;
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.DIRECTION, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());

		//verify next one won't change as x coordinates increased  
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X_1 + ",\"y\":" + Y + ",\"z\":" + Z + "}"});
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(AstraApi.DIRECTION, values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
		
		//verify x coordinates is negative as x decreased  
		syncEventPosition = api.syncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "}"});
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getX());
		assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
		assertEquals(AstraApi.ZERO_CHANGE, values.getZ());
	}
	
	@Test
	public void multipleAsyncPositionVectorUpdateNorthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}"});
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}"});
		//api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}"});
		//api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}"});
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 50) {
			asyncEventPosition = api.receive(agent, EventType.POSITION_VECTOR);
			if (asyncEventPosition == null) {			
				try {
					Thread.sleep(150);
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
		
		assertTrue(listEvents.size() == 3);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			PositionUnityJson values = (PositionUnityJson) gson.fromJson(listEvents.get(i), PositionUnityJson.class);
			assertEquals(AstraApi.ZERO_CHANGE, values.getX());
			assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
			if (countItems == 0) {
				assertEquals(AstraApi.DIRECTION, values.getZ());
			} else if (countItems == 1) {
				assertEquals(AstraApi.DIRECTION, values.getZ());
			} else if (countItems == 2) {
				assertEquals(AstraApi.DIRECTION, values.getZ());
			} else if (countItems == 3) {
				assertEquals(AstraApi.DIRECTION, values.getZ());
			} else if (countItems == 4) {
				assertEquals(AstraApi.DIRECTION, values.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionVectorUpdateSouthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}"});
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}"});
		//api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3_N + "}"});
		//api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4_N + "}"});
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 50) {
			asyncEventPosition = api.receive(agent, EventType.POSITION_VECTOR);
			if (asyncEventPosition == null) {			
				try {
					Thread.sleep(150);
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
		
		assertTrue(listEvents.size() == 3);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			PositionUnityJson values = (PositionUnityJson) gson.fromJson(listEvents.get(i), PositionUnityJson.class);
			assertEquals(AstraApi.ZERO_CHANGE, values.getX());
			assertEquals(AstraApi.ZERO_CHANGE, values.getY());		
			if (countItems == 0) {
				assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(-AstraApi.DIRECTION.doubleValue()), values.getZ());
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