package api;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import api.event.UnityEvent;
import astra.core.ASTRAClass;
import astra.core.ASTRAClassNotFoundException;
import astra.core.Agent;
import astra.core.AgentCreationException;
import astra.core.ModuleException;
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
		Scheduler.setSleepTime(50);
	}
	
	private Queue<String> events = new LinkedList<String>();
	
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
	 * 	/**
	 * Add event from Unity based on the type. The type used in Unity should match
	 * the one supported by the API. 
	 * @throws AstraApiException 
	 */

	public void asyncEvent(String agentIdentifier, String eventIdentifier, Object[] args) {
		
		ListTerm list = new ListTerm();
		for (Object object : args) {
			list.add(Primitive.newPrimitive(object));
		}
		final Set<String> agentsName = Agent.agentNames();
		
		boolean exist = agentsName.stream().anyMatch(s -> s.equals(agentIdentifier));
		
		Agent currentAgent = null;
		if (exist) {
			currentAgent = Agent.getAgent(agentIdentifier);
		} else {
			currentAgent = agent;
		}

	    if (eventIdentifier.equals(EVENT_TYPE_POSITION)) {
	    	currentAgent.addEvent(new UnityEvent(Primitive.newPrimitive(EVENT_TYPE_POSITION), list));
		} else {
			//Event type is not supported yet
			submitCommand("Event type: " + eventIdentifier + " is not supported yet!");
		}
	}


	public synchronized String receive() {
		return events.isEmpty() ? null : events.poll();
	}

	public synchronized void submitCommand(String event) {
		events.add(event);
	}

	public String syncEvent(String agentIdentifier, String eventIdentifier, Object[] args) {
		asyncEvent(agentIdentifier, eventIdentifier, args);
		String json = null;
		while ((json = receive()) == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
}
