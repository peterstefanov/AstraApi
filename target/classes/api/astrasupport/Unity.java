package api.astrasupport;

import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import api.AstraApi;
import api.CommandFactory;
import api.PositionCommandFactory;
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
	static Gson gson = new Gson();
	
	static {
		Unifier.eventFactory.put(UnityEvent.class, new UnityEventUnifier());
		commandFactory.put("position",  new PositionCommandFactory());
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
	public boolean sendCommand(String identifier, ListTerm arguments) {
		Object[] args = new Object[arguments.size()];
		for (int i = 0; i < args.length; i ++) {
			if (arguments.get(i) instanceof Primitive) {
				args[i] = ((Primitive<?>) arguments.get(i)).value();
			}
		}
		
		CommandFactory cf = commandFactory.get(identifier);
		if (cf == null) return false;
		
		api.submitCommand(gson.toJson(cf.create(args)));
		return true;
	}
	
	@EVENT(symbols={}, types={"string", "list"}, signature="$ue")
	public Event event(Term identifier, Term arguments) {
		return new UnityEvent(identifier, (ListTerm) arguments);
	}
}
