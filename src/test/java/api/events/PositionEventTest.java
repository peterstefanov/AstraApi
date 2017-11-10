package api.events;

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

import api.AstraApi;
import api.EventType;
import api.modules.utils.Position;
import api.modules.utils.UnityJson;

public class PositionEventTest extends EventTypeTest{	 

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
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});							
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(X, position.getX());
		assertEquals(Y, position.getY());	
		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventSouthNegativeTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});						
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(X, position.getX());
		assertEquals(Y, position.getY());
		assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventNorthPositiveTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});					
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(X, position.getX());
		assertEquals(Y, position.getY());	
		assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventNorthNegativeTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.NORTH + "}";			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});		
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(X, position.getX());
		assertEquals(Y, position.getY());	
		assertEquals(new Double(Z_N + AstraApi.API_CHANGE_RATE), position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventWestPositiveTest() {
		//Agent created
		String agent = createAgent();			
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.WEST + "}";
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});		
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventWestNegativeTest() {
		//Agent created
		String agent = createAgent();					
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.WEST + "}";
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});		
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
		
		assertEquals(new Double(X_N - AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventEastPositiveTest() {
		//Agent created
		String agent = createAgent();					
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});				
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();
	
		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());
	}
	
	@Test
	public void initialSinglePositionSyncEventEastNegativeTest() {
		//Agent created
		String agent = createAgent();					
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});				
		UnityJson json = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = json.getPosition();		
		
		assertEquals(new Double(X_N + AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());
	}
	
	@Test
	public void multiplePositionSyncEventTest() {
		//Agent created
		String agent = createAgent();			
		UnityJson values= null;
		String syncEventPosition = null;
		
		String event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}";
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});		
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		Position position = values.getPosition();	
		
		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());

		event = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_1 + ",\"y\":" + Y + ",\"z\":" + Z + "}}";
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {event});
		values = (UnityJson) gson.fromJson(syncEventPosition, UnityJson.class);
		position = values.getPosition();
		assertEquals(new Double(X_1 + AstraApi.API_CHANGE_RATE), position.getX());
		assertEquals(Y, position.getY());		
		assertEquals(Z, position.getZ());
	}
	
	@Test
	public void multipleAsyncPositionUpdateNorthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();	
			assertEquals(X, position.getX());
			assertEquals(Y, position.getY());		
			if (countItems == 0) {
				assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1 + AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2 + AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(Z_3 + AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(Z_4 + AstraApi.API_CHANGE_RATE), position.getZ());
			}
						
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateSouthChageDirectionsTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();	
			assertEquals(X, position.getX());
			assertEquals(Y, position.getY());	
			if (countItems == 0) {
				assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 3) {
				//verify direction changed to North - increased
				assertEquals(new Double(Z_1_N + AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 4) {
				//verify direction changed to North - increased
				assertEquals(new Double(Z_N + AstraApi.API_CHANGE_RATE), position.getZ());
			}					
			countItems ++;
		}
	}
	
	@Test
	public void multipleAsyncPositionUpdateClearEventMapTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "}}"});		
		
		String asyncEventPosition = null;
		int count = 0;
		while(count < 20) {
			api.clear(agent, EventType.POSITION);
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
				if (listEvents.size() == 5) {
					break;
				}
			}
			count ++;
		}
		
		assertTrue(listEvents.size() == 0);		
	}
	
	@Test
	public void multipleAsyncPositionUpdateSouthTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		LinkedList<String> listEvents = new LinkedList<String>();
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_N + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4_N + "}}"});
		getEventResponse(agent, listEvents, EventType.POSITION);
		
		assertTrue(listEvents.size() == 5);
		
		int countItems = 0;
		
		for (int i = 0; i < listEvents.size(); i ++) {
			UnityJson values = (UnityJson) gson.fromJson(listEvents.get(i), UnityJson.class);
			Position position = values.getPosition();	
			assertEquals(X, position.getX());
			assertEquals(Y, position.getY());	
			if (countItems == 0) {
				assertEquals(new Double(Z_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 1) {
				assertEquals(new Double(Z_1_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 2) {
				assertEquals(new Double(Z_2_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 3) {
				assertEquals(new Double(Z_3_N - AstraApi.API_CHANGE_RATE), position.getZ());
			} else if (countItems == 4) {
				assertEquals(new Double(Z_4_N - AstraApi.API_CHANGE_RATE), position.getZ());
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
	
    @RunWith(Parameterized.class)
    public static class ParameterizedAsyncEventEachCardinalDirection extends PositionEventTest {

        @Parameters(name = "{index}: jsonEvent=\"{0}\";")
        public static Collection<Object[]> values() {
            return Arrays.asList(new Object[][] { 
                {new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.NORTH + "}"}}, 
                {new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.SOUTH + "}"}}, 
                {new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.WEST + "}"}},  
                {new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"cardinalDirection\":" + AstraApi.EAST + "}"}}
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
    		
    		UnityJson send = (UnityJson) gson.fromJson(jsonEvent[0].toString(), UnityJson.class);
    		System.out.println(send);
    		
    		UnityJson values = (UnityJson) gson.fromJson(asyncEventPosition, UnityJson.class);
    		Position position = values.getPosition();	
    		
    		if (AstraApi.NORTH.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(X, position.getX());
        		assertEquals(Y, position.getY());		
        		assertEquals(new Double(Z + AstraApi.API_CHANGE_RATE), position.getZ());
    		} else if (AstraApi.SOUTH.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(X, position.getX());
        		assertEquals(Y, position.getY());		
        		assertEquals(new Double(Z - AstraApi.API_CHANGE_RATE), position.getZ());
    		} else if (AstraApi.WEST.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(new Double(X - AstraApi.API_CHANGE_RATE), position.getX());
        		assertEquals(Y, position.getY());		
        		assertEquals(Z, position.getZ());
    		} else if (AstraApi.EAST.equals(send.getCardinalDirection())) {
    			verifyAssert ++;
        		assertEquals(new Double(X + AstraApi.API_CHANGE_RATE), position.getX());
        		assertEquals(Y, position.getY());		
        		assertEquals(Z, position.getZ());
    		}
    		assertTrue(verifyAssert == 1);
    	}
    }
}
