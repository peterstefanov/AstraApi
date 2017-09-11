package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import api.modules.Position;
import api.modules.utils.Coordinates;

public class PositionEventTest extends EventTypeTest{

	  
	private static final Double X = new Double("1.649999976158142");
	private static final Double Y = new Double("1.0");
	private static final Double Z = new Double("2.700000047683716");
	private static final Double Z_1 = new Double("2.800000047683716");
	private static final Double Z_2 = new Double("2.900000047683716");
	private static final Double Z_3 = new Double("3.000000047683716");
	private static final Double Z_4 = new Double("3.100000047683716");
	
	
	@Before
	public void setUp() {		
		super.setUp();
	}
	
	@Test
	public void singlePositionSyncEventTest() {
		//Agent created
		String agent = createAgent();			
		
		String syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "}"});		
		Coordinates values = (Coordinates) gson.fromJson(syncEventPosition, Coordinates.class);
		assertEquals(Position.ZERO_CHANGE, values.getX());
		assertEquals(Position.ZERO_CHANGE, values.getY());		
		assertEquals(Position.API_MARGIN, values.getZ());
	}
	
	@Test
	public void multiplePositionSyncEventTest() {
		//Agent created
		String agent = createAgent();			
		Coordinates values= null;
		String syncEventPosition = null;
		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "}"});
		values = (Coordinates) gson.fromJson(syncEventPosition, Coordinates.class);
		assertEquals(Position.ZERO_CHANGE, values.getX());
		assertEquals(Position.ZERO_CHANGE, values.getY());		
		assertEquals(Position.API_MARGIN, values.getZ());

		
		syncEventPosition = api.syncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}"});
		values = (Coordinates) gson.fromJson(syncEventPosition, Coordinates.class);
		assertEquals(Position.ZERO_CHANGE, values.getX());
		assertEquals(Position.ZERO_CHANGE, values.getY());		
		assertEquals(Position.API_MARGIN, values.getZ());	
	}
	
	@Test
	public void multipleAsyncPositionUpdateTest() {
			
		//Agent created	
		String agent = createAgent();			
		
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_1 + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_2 + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_3 + "}"});
		api.asyncEvent(agent, EventType.POSITION, new Object[] {"{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z_4 + "}"});
		
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
				if (listEvents.size() == 5) {
					break;
				}
			}
			count ++;
		}
		
		assertTrue(listEvents.size() == 5);
		
		for (int i = 0; i < listEvents.size(); i ++) {
			Coordinates values = (Coordinates) gson.fromJson(listEvents.get(i), Coordinates.class);
			assertEquals(Position.ZERO_CHANGE, values.getX());
			assertEquals(Position.ZERO_CHANGE, values.getY());		
			assertEquals(Position.API_MARGIN, values.getZ());							
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
