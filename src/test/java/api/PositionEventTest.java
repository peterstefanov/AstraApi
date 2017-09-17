package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.modules.utils.PositionUnityJson;

public class PositionEventTest extends EventTypeTest{

	  
	private static final Double X = new Double("1.649999976158142");
	private static final Double X_1 = new Double("2.849999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	private static final Double Z_1 = new Double("3.900000047683716");
	private static final Double Z_2 = new Double("4.000000047683716");
	private static final Double Z_3 = new Double("5.100000047683716");
	private static final Double Z_4 = new Double("6.200000047683716");
	
	
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void initialSinglePositionSyncEventSouthTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventNorthTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventWestTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventEastTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void singlePositionAsyncEventTest() {
		//Agent created
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});	
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 20) {
			asyncEventPosition = api.receive(agent, EventType.POSITION);
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
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void multiplePositionSyncEventTest() {
		//Agent created
		String agent = createAgent();			
		PositionUnityJson values= null;
		String syncEventPosition = null;
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());

		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X_1 + ",\"y\":" + Y + ",\"z\":" + Z + "}"});
		values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X_1 + AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void multipleAsyncPositionUpdateTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}"});
		//api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}"});
		//api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}"});
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 50) {
			asyncEventPosition = api.receive(agent, EventType.POSITION);
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
			//System.out.println(count);
		}
		
		assertTrue(listEvents.size() == 3);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			PositionUnityJson values = (PositionUnityJson) gson.fromJson(listEvents.get(i), PositionUnityJson.class);
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());		
			if (countItems == 0) {
				assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1 + AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2 + AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(Z_3 + AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(Z_4 + AstraApi.API_CHANGE_RATE), values.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateRepeatTest() {
		multipleAsyncPositionUpdateTest();
		multipleAsyncPositionUpdateTest();
		multipleAsyncPositionUpdateTest();
		multipleAsyncPositionUpdateTest();
		multipleAsyncPositionUpdateTest();
	}
}
