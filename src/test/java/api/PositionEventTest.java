package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

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
	
	private static final Double X_N = new Double("-1.649999976158142");
	private static final Double X_1_N = new Double("-2.849999976158142");
	
	private static final Double Z_N = new Double("-2.700000047683716");
	private static final Double Z_1_N = new Double("-3.900000047683716");
	private static final Double Z_2_N = new Double("-4.000000047683716");
	private static final Double Z_3_N = new Double("-5.100000047683716");
	private static final Double Z_4_N = new Double("-6.200000047683716");
	
    @Parameter(0)
    public Object[] jsonEvent;
    
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void initialSinglePositionSyncEventSouthPositiveTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());	
		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventSouthNegativeTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());	
		assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventNorthPositiveTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventNorthNegativeTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(X, values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(new Double(Z_N + AstraApi.API_CHANGE_RATE), values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventWestPositiveTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventWestNegativeTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X_N + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X_N - AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventEastPositiveTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventEastNegativeTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X_N + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"});		
		PositionUnityJson values = (PositionUnityJson) gson.fromJson(syncEventPosition, PositionUnityJson.class);
		assertEquals(new Double(X_N + AstraApi.API_CHANGE_RATE), values.getX());
		assertEquals(Y, values.getY());		
		assertEquals(Z, values.getZ());
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
	public void multipleAsyncPositionUpdateNorthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}"});
		getEventResponse(agent, listEvents);
		
		assertTrue(listEvents.size() == 5);
		
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
	public void multipleAsyncPositionUpdateSouthChageDirectionsTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}"});
		getEventResponse(agent, listEvents);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			PositionUnityJson values = (PositionUnityJson) gson.fromJson(listEvents.get(i), PositionUnityJson.class);
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());		
			if (countItems == 0) {
				assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 3) {
				//verify direction changed to North - increased
				assertEquals(new Double(Z_1_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 4) {
				//verify direction changed to North - increased
				assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), values.getZ());
			}					
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateSouthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3_N + "}"});
		getEventResponse(agent, listEvents);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4_N + "}"});
		getEventResponse(agent, listEvents);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			PositionUnityJson values = (PositionUnityJson) gson.fromJson(listEvents.get(i), PositionUnityJson.class);
			assertEquals(X, values.getX());
			assertEquals(Y, values.getY());		
			if (countItems == 0) {
				assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(Z_3_N - AstraApi.API_CHANGE_RATE), values.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(Z_4_N - AstraApi.API_CHANGE_RATE), values.getZ());
			}						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateRepeatTest() {
		multipleAsyncPositionUpdateNorthTest();
		multipleAsyncPositionUpdateNorthTest();
		multipleAsyncPositionUpdateNorthTest();
	}
	
	private void getEventResponse(String agent, LinkedList<String> listEvents) {
		String asyncEventPosition = null;
		int count = 0;
		while(count < 5) {
			asyncEventPosition = api.receive(agent, EventType.POSITION);
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
	
    @RunWith(Parameterized.class)
    public static class ParameterizedAsyncEventEachCardinalDirection extends PositionEventTest {

        @Parameters(name = "{index}: jsonEvent=\"{0}\";")
        public static Collection<Object[]> values() {
            return Arrays.asList(new Object[][] { 
                {new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.NORTH + "}"}}, 
                {new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.SOUTH + "}"}}, 
                {new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.WEST + "}"}},  
                {new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + ",\"cardinalDirection\":" + AstraApi.EAST + "}"}}
            });
        }
    	
    	@Test
    	public void singlePositionAsyncEventEachCardinalDirectionTest() {
    		//Agent created
    		String agent = createAgent();   		
    		
    		api.asyncEvent(agent, EventType.POSITION, jsonEvent);	
    		
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
    		
    		int verifyAssert = 0;
    		
    		PositionUnityJson send = (PositionUnityJson) gson.fromJson(jsonEvent[0].toString(), PositionUnityJson.class);
    		System.out.println(send);
    		
    		PositionUnityJson values = (PositionUnityJson) gson.fromJson(asyncEventPosition, PositionUnityJson.class);
   	
    		if (AstraApi.NORTH.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(X, values.getX());
        		assertEquals(Y, values.getY());		
        		assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), values.getZ());
    		} else if (AstraApi.SOUTH.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(X, values.getX());
        		assertEquals(Y, values.getY());		
        		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), values.getZ());
    		} else if (AstraApi.WEST.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), values.getX());
        		assertEquals(Y, values.getY());		
        		assertEquals(Z, values.getZ());
    		} else if (AstraApi.EAST.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), values.getX());
        		assertEquals(Y, values.getY());		
        		assertEquals(Z, values.getZ());
    		}
    		assertTrue(verifyAssert == 1);
    	}
    }
}
