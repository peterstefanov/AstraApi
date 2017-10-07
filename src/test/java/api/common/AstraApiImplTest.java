package api.common;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.AstraApi;
import api.AstraApiImpl;

public class AstraApiImplTest {

	public static final String AGENT_CLASS_NAME = "Player";
	public static final String AGENT_CLASS_NAME_DOESNT_EXISTS = "PlayerWorng";

	public static final String AGENT_ONE = "AgentOne";
	public static final String AGENT_TWO = "AgentTwo";

	private AstraApiImpl api;

	@Before
	public void setUp() {
		api = new AstraApiImpl();
	}

	@Test
	public void createAgentSuccess() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_ONE, response);
	}

	@Test
	public void createTwoAgentSuccess() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_ONE, response);

		// second Agent created with different name
		String responseTwo = api.createAgent(AGENT_TWO, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_TWO, responseTwo);
	}

	@Test
	public void createAgentFailureAlreadyExists() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_ONE, response);

		// attempt to create Agent with the same name again
		String responseSameName = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);
		// verify warning message returned
		assertEquals(AstraApi.AGENT_ALREADY_EXISTS, responseSameName);
	}

	@Test
	public void createAgentFailureWrongClassName() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME_DOESNT_EXISTS);

		// verify warning message returned
		assertEquals(AstraApi.AGENT_CREATION_NO_SUCH_CLASS, response);
	}

	@Test
	public void removeAgentSuccess() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_ONE, response);

		// remove Agent
		String responseRemove = api.removeAgent(AGENT_ONE);
		// verify agent removed successful
		assertEquals(AstraApi.AGENT_TERMINATED, responseRemove);
	}

	@Test
	public void removeAgentFailureNotExist() {
		// Agent created
		String response = api.createAgent(AGENT_ONE, AGENT_CLASS_NAME);

		// verify agent created successful
		assertEquals(AGENT_ONE, response);

		// attempt to remove Agent with different name
		String responseRemove = api.removeAgent(AGENT_TWO);
		// verify agent is not removed because doesn't exists
		assertEquals(AstraApi.AGENT_DOES_NOT_EXISTS, responseRemove);
	}

	@After
	public void tearsDown() {
		api.removeAgent(AGENT_ONE);
		api.removeAgent(AGENT_TWO);
	}
}
