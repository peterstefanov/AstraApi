package api.modules;

import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import api.AstraApi;
import api.EventType;
import api.command.AstraCommandFactory;
import api.command.CommandFactory;
import api.event.UnityEvent;
import api.event.UnityEventUnifier;
import astra.core.Module;
import astra.event.Event;
import astra.reasoner.Unifier;
import astra.term.ListTerm;
import astra.term.Primitive;
import astra.term.Term;

public class Unity extends Module {
	
	private AstraApi api;
	
	static Map<String, CommandFactory> commandFactory = new TreeMap<String, CommandFactory>();
	private Gson gson = new Gson();
	
	static {
		Unifier.eventFactory.put(UnityEvent.class, new UnityEventUnifier());
		commandFactory.put(EventType.POSITION,  new AstraCommandFactory(EventType.POSITION));
		commandFactory.put(EventType.COLLISION, new AstraCommandFactory(EventType.COLLISION));
		commandFactory.put(EventType.POSITION_VECTOR, new AstraCommandFactory(EventType.POSITION_VECTOR));
	}
	
	@ACTION
	public boolean linkToUnity(AstraApi api) {
		this.api = api;
		return true;
	}
	
	/**
	 * Ability to add dynamically and use a factory from Astra.
	 * @param id
	 * @param cls
	 * @return
	 */
	@ACTION
	public boolean addFactory(String id, String cls) {
		try {
			commandFactory.put(id, (CommandFactory) Class.forName(cls).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@ACTION
	public boolean sendCommand(String agentIdentifier, String eventIdentifier, ListTerm arguments) {
		Object[] args = new Object[arguments.size()];
		for (int i = 0; i < args.length; i ++) {
			if (arguments.get(i) instanceof Primitive) {
				args[i] = ((Primitive<?>) arguments.get(i)).value();
			}
		}
		
		CommandFactory cf = commandFactory.get(eventIdentifier);
		if (cf == null) return false;
		
		String command = getCommand(cf.create(args));
        System.out.println("command: " + command);
		api.submitCommand(agentIdentifier, eventIdentifier, command);
		return true;
	}
	
	@EVENT(symbols={}, types={"string", "list"}, signature="$ue")
	public Event event(Term identifier, Term arguments) {
		return new UnityEvent(identifier, (ListTerm) arguments);
	}
	
	private synchronized String getCommand(Object object) {
		System.out.println(object.toString());
		return gson.toJson(object);		
	}
}
