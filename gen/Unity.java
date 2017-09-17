/**
 * GENERATED CODE - DO NOT CHANGE
 */

import astra.core.*;
import astra.execution.*;
import astra.event.*;
import astra.messaging.*;
import astra.formula.*;
import astra.lang.*;
import astra.statement.*;
import astra.term.*;
import astra.type.*;
import astra.tr.*;
import astra.reasoner.util.*;


public class Unity extends ASTRAClass {
	public Unity() {
		setParents(new Class[] {astra.lang.Agent.class});
		addRule(new Rule(
			"Unity", new int[] {7,9,7,36},
			new GoalEvent('+',
				new Goal(
					new Predicate("unity", new Term[] {
						new Variable(new ObjectType(api.AstraApi.class), "api",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"Unity", new int[] {7,35,9,5},
				new Statement[] {
					new ModuleCall("unityModule",
						"Unity", new int[] {8,8,8,36},
						new Predicate("linkToUnity", new Term[] {
							new Variable(new ObjectType(api.AstraApi.class), "api")
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((api.modules.Unity) intention.getModule("Unity","unityModule")).linkToUnity(
									(api.AstraApi) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					)
				}
			)
		));
	}

	public void initialize(astra.core.Agent agent) {
	}

	public Fragment createFragment(astra.core.Agent agent) throws ASTRAClassNotFoundException {
		Fragment fragment = new Fragment(this);
		fragment.addModule("unityModule",api.modules.Unity.class,agent);
		fragment.addModule("positionModule",api.modules.Position.class,agent);
		fragment.addModule("collisionModule",api.modules.Collision.class,agent);
		fragment.addModule("directionVector",api.modules.DirectionVector.class,agent);
		return fragment;
	}

	public static void main(String[] args) {
		Scheduler.setStrategy(new AdaptiveSchedulerStrategy());
		ListTerm argList = new ListTerm();
		for (String arg: args) {
			argList.add(Primitive.newPrimitive(arg));
		}

		String name = java.lang.System.getProperty("astra.name", "main");
		try {
			astra.core.Agent agent = new Unity().newInstance(name);
			agent.initialize(new Goal(new Predicate("main", new Term[] { argList })));
			Scheduler.schedule(agent);
		} catch (AgentCreationException e) {
			e.printStackTrace();
		} catch (ASTRAClassNotFoundException e) {
			e.printStackTrace();
		};
	}
}
