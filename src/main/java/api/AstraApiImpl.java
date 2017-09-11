package api;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import api.event.UnityEvent;
import astra.core.ASTRAClass;
import astra.core.ASTRAClassNotFoundException;
import astra.core.Agent;
import astra.core.AgentCreationException;
import astra.core.ModuleException;
import astra.core.Scheduler;
import astra.execution.BasicSchedulerStrategy;
import astra.formula.Goal;
import astra.formula.Predicate;
import astra.term.ListTerm;
import astra.term.Primitive;
import astra.term.Term;

public class AstraApiImpl implements AstraApi {
	
	private Agent agent;
	
	static {
		Scheduler.setStrategy(new BasicSchedulerStrategy());
		Scheduler.setSleepTime(50);
	}
	
	private ConcurrentMap<String, Queue<String>> concurrentMap = new ConcurrentHashMap<>();
	
	public String createAgent(String name, String className) {
		try {
			
			ASTRAClass astraClass = (astra.core.ASTRAClass) Class.forName(className).newInstance();
			agent = astraClass.newInstance(name);
			agent.initialize(new Goal(new Predicate("unity", new Term[] { Primitive.newPrimitive(this)})));
			Scheduler.schedule(agent);
		
		} catch (AgentCreationException ace) {
			ace.printStackTrace();
		} catch (ASTRAClassNotFoundException acnfe) {
			acnfe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			throw new ModuleException(cnfe);
		} catch (InstantiationException ie) {
			throw new ModuleException(ie);
		} catch (IllegalAccessException iae) {
			throw new ModuleException(iae);
		}

		return agent.name();
	}
    
	/**
	 * Add an event from Unity to an agent based on the type. The type used in Unity should match
	 * the one supported by the API. If the agent doesn't exists bind the event to the current agent.
	 * @throws AstraApiException 
	 */

	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] args) {
		
		ListTerm list = new ListTerm();
		for (Object object : args) {
			list.add(Primitive.newPrimitive(object));
		}
		final Set<String> agentsName = Agent.agentNames();
		
		//check if agent exist 
		boolean exist = agentsName.stream().anyMatch(s -> s.equals(agentIdentifier));
		
		Agent currentAgent = null;
		if (exist) {
			currentAgent = Agent.getAgent(agentIdentifier);
		} else {
			currentAgent = agent;
		}

	    if (EventType.isEventTypeSupported(eventIdentifier)) {
	    	currentAgent.addEvent(new UnityEvent(Primitive.newPrimitive(eventIdentifier), list));
		} else {
			//Event type is not supported yet
			submitCommand(agentIdentifier, "Event type: " + eventIdentifier + " is not supported yet!", "");
		}
	}

	public synchronized String receive(String agentIdentifier, String eventIdentifier) {
		String key = agentIdentifier.concat(eventIdentifier);
		if (concurrentMap.containsKey(key)) {
			Queue<String> agentEvents = concurrentMap.get(key);
			return agentEvents.isEmpty() ? null : agentEvents.poll();
		}
		return null;
	}

	public synchronized void submitCommand(String agentIdentifier, String eventIdentifier, String event) {

		String key = agentIdentifier.concat(eventIdentifier);
		
		if (concurrentMap.containsKey(agentIdentifier)) {
			concurrentMap.get(agentIdentifier).add(event);
		} else {
			Queue<String> agentEvents = new LinkedList<String>();
			agentEvents.add(event);
			concurrentMap.put(key, agentEvents);
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
}
