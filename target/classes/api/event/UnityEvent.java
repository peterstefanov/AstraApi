package api.event;

import astra.term.ListTerm;
import astra.event.Event;
import astra.term.Term;

public class UnityEvent implements Event {
	public final Term identifier;
	public final ListTerm arguments;
	
	public UnityEvent(Term identifier, ListTerm arguments) {
		this.identifier = identifier;
		this.arguments = arguments;
	}
	
	public Object getSource() {
		return null;
	}

	public String signature() {
		return "$ue";
	}

}
