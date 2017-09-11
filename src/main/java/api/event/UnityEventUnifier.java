package api.event;

import java.util.HashMap;
import java.util.Map;

import astra.core.Agent;
import astra.reasoner.EventUnifier;
import astra.reasoner.Unifier;
import astra.term.Term;

public class UnityEventUnifier implements EventUnifier<UnityEvent> {

    public Map unify(UnityEvent source, UnityEvent target, Agent agent) {
       return Unifier.unify(
          new Term[] {source.identifier, source.arguments}, 
          new Term[] {target.identifier, target.arguments}, 
          new HashMap<Integer, Term>(), agent);
    }
}
