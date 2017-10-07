package api;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import api.event.UnityEvent;
import astra.core.ASTRAClass;
import astra.core.ASTRAClassNotFoundException;
import astra.core.Agent;
import astra.core.AgentCreationException;
import astra.core.Scheduler;
import astra.execution.AdaptiveSchedulerStrategy;
import astra.formula.Goal;
import astra.formula.Predicate;
import astra.term.ListTerm;
import astra.term.Primitive;
import astra.term.Term;

public class AstraApiImpl implements AstraApi {

	private Agent agent;

	static {
		Scheduler.setStrategy(new AdaptiveSchedulerStrategy());
	}

	/**
	 * Map holds the events for each active agent. The map key is constructed by
	 * agent name and event type.
	 */
	private ConcurrentMap<String, Queue<String>> agentsEvent = new ConcurrentHashMap<>();

	public String createAgent(String name, String className) {

		if (Agent.hasAgent(name)) {
			return AGENT_ALREADY_EXISTS;
		}

		try {
			ASTRAClass astraClass = (astra.core.ASTRAClass) Class.forName(className).newInstance();
			agent = astraClass.newInstance(name);
			agent.initialize(new Goal(new Predicate("unity", new Term[] { Primitive.newPrimitive(this) })));
			Scheduler.schedule(agent);
		} catch (AgentCreationException ace) {
			ace.printStackTrace();
			return AGENT_CREATION_FAILS;
		} catch (ASTRAClassNotFoundException acnfe) {
			acnfe.printStackTrace();
			return AGENT_CREATION_FAILS;
		} catch (ClassNotFoundException cnfe) {
			return AGENT_CREATION_NO_SUCH_CLASS;
		} catch (InstantiationException ie) {
			return AGENT_CREATION_NO_SUCH_CLASS;
		} catch (IllegalAccessException iae) {
			return AGENT_CREATION_NO_SUCH_CLASS;
		}

		return agent.name();
	}

	public String removeAgent(String name) {

		if (!Agent.hasAgent(name)) {
			return AGENT_DOES_NOT_EXISTS;
		} else {
			Agent agentForRemoval = Agent.getAgent(name);

			if (agentForRemoval != null) {
				agentForRemoval.terminate();
				return AGENT_TERMINATED;
			}
			return AGENT_NOT_TERMINATED;
		}
	}

	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] args) {

		ListTerm list = new ListTerm();
		for (Object object : args) {
			list.add(Primitive.newPrimitive(object));
		}

		// check if agent exist
		boolean exist = Agent.hasAgent(agentIdentifier);

		Agent currentAgent = null;
		if (exist) {
			currentAgent = Agent.getAgent(agentIdentifier);
		} else {
			currentAgent = agent;
		}

		if (EventType.isEventTypeSupported(eventIdentifier)) {
			currentAgent.addEvent(new UnityEvent(Primitive.newPrimitive(eventIdentifier), list));
		} else {
			// Event type is not supported yet
			submitCommand(agentIdentifier, "Event type: " + eventIdentifier + " is not supported yet!", "");
		}
	}

	public synchronized String receive(String agentIdentifier, String eventIdentifier) {
		String key = agentIdentifier.concat(eventIdentifier);
		if (agentsEvent.containsKey(key)) {
			Queue<String> agentEvents = agentsEvent.get(key);
			return agentEvents.isEmpty() ? null : agentEvents.poll();
		}
		return null;
	}

	public synchronized void submitCommand(String agentIdentifier, String eventIdentifier, String event) {

		String key = agentIdentifier.concat(eventIdentifier);

		if (agentsEvent.containsKey(agentIdentifier)) {
			agentsEvent.get(agentIdentifier).add(event);
		} else {
			Queue<String> agentEvents = new LinkedList<String>();
			agentEvents.add(event);
			agentsEvent.put(key, agentEvents);
		}
	}

	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] args) {

		asyncEvent(agentIdentifier, eventIdentifier, args);
		String json = null;

		while ((json = receive(agentIdentifier, eventIdentifier)) == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	public synchronized void clear(String agentIdentifier, String eventIdentifier) {
		String key = agentIdentifier.concat(eventIdentifier);
		if (agentsEvent.containsKey(key)) {
			Queue<String> agentEvents = agentsEvent.get(key);
			agentEvents.clear();
		}
	}
}
