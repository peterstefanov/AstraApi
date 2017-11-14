package api.modules.ai;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import api.AstraApi;
import api.EventType;
import api.events.EventTypeTest;
import api.modules.utils.UnityJson;
import astra.core.Agent;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmartyPansMovingTest extends EventTypeTest {
	
	private String agentSmartyPans;
	

	public static final Double N_10 = new Double("-9.649999976158142");
	public static final Double N_9 = new Double("-8.649999976158142");
	public static final Double N_8 = new Double("-7.649999976158142");
	public static final Double N_7 = new Double("-6.649999976158142");
	public static final Double N_6 = new Double("-5.649999976158142");
	public static final Double N_5 = new Double("-4.649999976158142");
	public static final Double N_4 = new Double("-3.649999976158142");
	public static final Double N_3 = new Double("-2.649999976158142");
	public static final Double N_2 = new Double("-1.649999976158142");
	public static final Double N_1 = new Double("-0.649999976158142");
	
	public static final Double P_10 = new Double("9.649999976158142");
	public static final Double P_9 = new Double("8.649999976158142");
	public static final Double P_8 = new Double("7.649999976158142");
	public static final Double P_7 = new Double("6.649999976158142");
	public static final Double P_6 = new Double("5.649999976158142");
	public static final Double P_5 = new Double("4.649999976158142");
	public static final Double P_4 = new Double("3.649999976158142");
	public static final Double P_3 = new Double("2.649999976158142");
	public static final Double P_2 = new Double("1.649999976158142");
	public static final Double P_1 = new Double("0.649999976158142");
	
	public static final Double _0 = new Double("0");
	
	public static final Double X_INIT = N_10;
	public static final Double Z_INIT = P_10;
	
	public static final Double X = new Double("5.649999976158142");
	public static final Double X_N = new Double("-5.649999976158142");
	
	public static final int INSTANCE_ID_END = 7777777;
	
	public static final String WEST_COLLISION_N10x10 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_10 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";
	public static final String WEST_COLLISION_N10x9 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_9 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";
	public static final String WEST_COLLISION_N10x8 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";

	public static final String EAST_COLLISION_10x10 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_10 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String EAST_COLLISION_10x9 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_9 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String EAST_COLLISION_10x8 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String EAST_COLLISION_10x7 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";

	
	public static final String NORTH_COLLISION_10x10 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_10 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String NORTH_COLLISION_N10x10 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_10 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String NORTH_COLLISION_N10x9 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_9 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String NORTH_COLLISION_10x8 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	
	public static final String SOUTH_COLLISION_N10x10 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_10 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String SOUTH_COLLISION_10x9 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_9 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String SOUTH_COLLISION_10x8 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";

	public static final String SOUTH_COLLISION_N10x8 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String SOUTH_COLLISION_10x7 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";

	public static final String COLLISION_END_MAZE_ONE = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_8 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String COLLISION_END_MAZE_TWO = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_6 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String COLLISION_END_MAZE_ONE_N2 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_2 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String COLLISION_END_MAZE_TWO_N3 = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_3 + ",\"y\":" + Y + ",\"z\":" + P_7 + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";

	public static final String SOUTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NORTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String EAST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String WEST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X + ",\"y\":" + Y + ",\"z\":" + Z + "},\"instanceId\":" + INSTANCE_ID + ",\"cardinalDirection\":" + AstraApi.WEST +"}";

	public static final String NEGATIVE_SOUTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.SOUTH +"}";
	public static final String NEGATIVE_NORTH_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.NORTH +"}";
	public static final String NEGATIVE_EAST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.EAST +"}";
	public static final String NEGATIVE_WEST_COLLISION = "{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_N + ",\"y\":" + Y_N + ",\"z\":" + Z_N + "},\"instanceId\":" + INSTANCE_ID_END + ",\"cardinalDirection\":" + AstraApi.WEST +"}";
		
	@Before
	public void setUp() {	
		super.setUp();
		if (!Agent.hasAgent("smartyPans")) {
			agentSmartyPans = api.createAgent("smartyPans", "SmartyPans");
		}	
	}
	
	@Ignore("Run this class standalone")
	@Test
	public void findPathAWithNoCollisionsOccurredTest() {
			
		//Agent for generating the knowledge map	
		String agent = createAgent();
		
		generateGridData(agent);		

		LinkedList<String> smartyListEvents = new LinkedList<String>();
		
		//start the smartyPans from the initial direction - x=-10 z=10
		api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "},\"cardinalDirection\":" + AstraApi.EAST + "}"});
		getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);
		for (int i = 0; i <= 10; i ++) {
			api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "}}"});
			getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);		
		}		
		
		assertEquals(12, smartyListEvents.size());	
		
		int numberSteps = 0;
		
		for (String event : smartyListEvents) {
			UnityJson values = (UnityJson) gson.fromJson(event, UnityJson.class);
			if (values.position.x != 0 && values.position.z != 0) {
				numberSteps ++;
			}
		}
		
		//assert the length of the path is 0
		assertEquals(0, numberSteps);	
	}
	
	@Ignore("Run this class standalone")
	@Test
	public void findPathBWithOneCollisionsOccurredTest() {
			
		//Agent for generating the knowledge map	
		String agent = createAgent();
		
		generateGridData(agent);
		
		/** Add collision events to knowledge map, that record the end of the maze 
		 * This will trigger the smartyPans to start moving only if two ends are present*/
		LinkedList<String> collisionsEistEvents = new LinkedList<String>();
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {COLLISION_END_MAZE_TWO_N3});
		getEventResponse(agent, collisionsEistEvents , EventType.COLLISION);

		
		assertEquals(1, collisionsEistEvents.size());

		LinkedList<String> smartyListEvents = new LinkedList<String>();
		
		//start the smartyPans from the initial direction - x=-10 z=10
		api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "},\"cardinalDirection\":" + AstraApi.EAST + "}"});
		getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);
		for (int i = 0; i <= 10; i ++) {
			api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "}}"});
			getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);		
		}		
		
		assertEquals(12, smartyListEvents.size());	
		
		int numberSteps = 0;
		
		for (String event : smartyListEvents) {
			UnityJson values = (UnityJson) gson.fromJson(event, UnityJson.class);
			if (values.position.x != 0 && values.position.z != 0) {
				numberSteps ++;
			}
		}
		
		//assert the length of the path is 0
		assertEquals(0, numberSteps);	
	}
	
	@Ignore("Run this class standalone")
	@Test
	public void findPathCWithAllCollisionsOccurredSteps80Test() {
			
		//Agent for generating the knowledge map	
		String agent = createAgent();
		
		generateGridData(agent);
		
		/** Add collision events to knowledge map, that record the end of the maze 
		 * This will trigger the smartyPans to start moving*/
		LinkedList<String> collisionsEistEvents = new LinkedList<String>();
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {COLLISION_END_MAZE_ONE_N2});
		getEventResponse(agent, collisionsEistEvents , EventType.COLLISION);
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {COLLISION_END_MAZE_TWO_N3});	
		getEventResponse(agent, collisionsEistEvents , EventType.COLLISION);
		
		assertEquals(2, collisionsEistEvents.size());

		LinkedList<String> smartyListEvents = new LinkedList<String>();
		
		//start the smartyPans from the initial direction - x=-10 z=10
		//should take him 80 moves to reached the end of he maze, after that position with (0,0,0) should be returned
		api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "},\"cardinalDirection\":" + AstraApi.EAST + "}"});
		getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);
		for (int i = 0; i <= 100; i ++) {
			api.asyncEvent(agentSmartyPans, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "}}"});
			getEventResponse(agentSmartyPans, smartyListEvents, EventType.POSITION_VECTOR);		
		}		
		
		assertEquals(102, smartyListEvents.size());	
		
		int numberSteps = 0;
		
		for (String event : smartyListEvents) {
			UnityJson values = (UnityJson) gson.fromJson(event, UnityJson.class);
			if (values.position.x != 0 && values.position.z != 0) {
				numberSteps ++;
			}
		}
		
		//assert the length of the path is 80
		assertEquals(80, numberSteps);	
	}
	
	private void generateGridData(String agent) {
		LinkedList<String> collisionsEvents = new LinkedList<String>();
		LinkedList<String> movesEvents = new LinkedList<String>();
		
		//collide at x=-10 z=10 to the North
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION_N10x10});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=-10 z=10 to the West
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {WEST_COLLISION_N10x10});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=-10 z=10 to the South
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION_N10x10});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//start at x=-10 and z=10 end on x=10 y=10
		buildKnowledgeMapAlongFromInitialPosition(agent);
		
		//collide at x=10 z=10 to the East
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {EAST_COLLISION_10x10});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=10 z=10 to the North
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION_10x10});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//go one step to the South at x=10 z=9
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_9 + "}}"});
		getEventResponse(agent, movesEvents, EventType.POSITION_VECTOR);	
		
		//collide at x=10 z=9 to the South
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION_10x9});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=10 z=9 to the East
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {EAST_COLLISION_10x9});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);

		//go along Z9 to the West starting from x=10 z=9 should end up at x=-10 z=9
		buildKnowledgeMapAlongZAxisWestDirection(agent, P_10, P_9);
		
		//collide at x=-10 z=9 to the West
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {WEST_COLLISION_N10x9});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=-10 z=9 to the North
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION_N10x9});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//go one step down the grid to the South at x=-10 z=8
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + N_10 + ",\"y\":" + Y + ",\"z\":" + P_8 + "}}"});
		getEventResponse(agent, movesEvents, EventType.POSITION_VECTOR);
		
		//collide at x=-10 z=8 to the South
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION_N10x8});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=-10 z=8 to the West
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {WEST_COLLISION_N10x8});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//go along Z8 to the East starting from x=-10 z=8 should end up at x=10 z=8
		buildKnowledgeMapAlongZAxisEastDirection(agent, N_10, P_8);

		//collide at x=10 z=8 to the East
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {EAST_COLLISION_10x8});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//collide at x=10 z=8 to the North
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {NORTH_COLLISION_10x8});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//go one step down the grid to the South at x=10 z=7
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + P_10 + ",\"y\":" + Y + ",\"z\":" + P_7 + "}}"});
		getEventResponse(agent, movesEvents, EventType.POSITION_VECTOR);
		
		//collide at x=10 z=7 to the South
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {SOUTH_COLLISION_10x7});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);

		//collide at x=10 z=7 to the East
		api.asyncEvent(agent, EventType.COLLISION, new Object[] {EAST_COLLISION_10x7});
		getEventResponse(agent, collisionsEvents , EventType.COLLISION);
		
		//go along Z9 to the West starting from x=10 z=7 should end up at x=-10 z=7
		buildKnowledgeMapAlongZAxisWestDirection(agent, P_10, P_7);
		
		assertEquals(3, movesEvents.size());
		assertEquals(15, collisionsEvents.size());
	}	
	
	private void buildKnowledgeMapAlongFromInitialPosition(String agent) {
		LinkedList<String> listEvents = new LinkedList<String>();
				
		api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + X_INIT + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "},\"cardinalDirection\":" + AstraApi.EAST + "}"});
		getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);
		
		for (int i = 0; i <= 19; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + (X_INIT + i) + ",\"y\":" + Y + ",\"z\":" + Z_INIT + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);	

		}
		
		assertEquals(21, listEvents.size());
	}
	
	private void buildKnowledgeMapAlongZAxisEastDirection(String agent, Double x, Double z) {
		LinkedList<String> listEvents = new LinkedList<String>();
		
		for (int i = 0; i <= 20; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + (x + i) + ",\"y\":" + Y + ",\"z\":" + z + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);	

		}
		
		assertEquals(21, listEvents.size());
	}
	
	private void buildKnowledgeMapAlongZAxisWestDirection(String agent, Double x, Double z) {
		LinkedList<String> listEvents = new LinkedList<String>();
		
		for (int i = 0; i <= 20; i ++) {
			api.asyncEvent(agent, EventType.POSITION_VECTOR, new Object[] {"{\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},\"scale\":{\"x\":0.5,\"y\":1.0,\"z\":0.5},\"position\":{\"x\":" + (x - i) + ",\"y\":" + Y + ",\"z\":" + z + "}}"});
			getEventResponse(agent, listEvents, EventType.POSITION_VECTOR);	

		}
		
		assertEquals(21, listEvents.size());
	}
	
	public String createAgent() {
		return api.createAgent(getUniqueString(), "GridMapGenerator");
	}
		
	@After
	public void tearsDown() {
		api.removeAgent("smartyPans");
	}
}
