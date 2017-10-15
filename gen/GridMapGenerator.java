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


public class GridMapGenerator extends ASTRAClass {
	public GridMapGenerator() {
		setParents(new Class[] {Player.class});
		addRule(new Rule(
			"GridMapGenerator", new int[] {4,9,4,54},
			new GoalEvent('+',
				new Goal(
					new Predicate("collision", new Term[] {
						new Variable(Type.STRING, "collision",false),
						new Variable(Type.STRING, "event",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {4,53,8,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "collisionEvent"),
						"GridMapGenerator", new int[] {6,8,8,5},
						new ModuleTerm("gridMap", Type.STRING,
							new Predicate("updateGridMap", new Term[] {
								new Variable(Type.STRING, "event")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {7,8,8,5},
						"Player",
						new Goal(
							new Predicate("collision", new Term[] {
								new Variable(Type.STRING, "collision"),
								new Variable(Type.STRING, "collisionEvent")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {10,9,10,64},
			new GoalEvent('+',
				new Goal(
					new Predicate("positionVector", new Term[] {
						new Variable(Type.STRING, "positionVector",false),
						new Variable(Type.STRING, "event",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {10,63,14,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "positionEvent"),
						"GridMapGenerator", new int[] {12,8,14,5},
						new ModuleTerm("gridMap", Type.STRING,
							new Predicate("updateGridMap", new Term[] {
								new Variable(Type.STRING, "event")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {13,8,14,5},
						"Player",
						new Goal(
							new Predicate("positionVector", new Term[] {
								new Variable(Type.STRING, "positionVector"),
								new Variable(Type.STRING, "positionEvent")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {16,9,16,52},
			new GoalEvent('+',
				new Goal(
					new Predicate("position", new Term[] {
						new Variable(Type.STRING, "position",false),
						new Variable(Type.STRING, "event",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {16,51,20,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "positionEvent"),
						"GridMapGenerator", new int[] {18,8,20,5},
						new ModuleTerm("gridMap", Type.STRING,
							new Predicate("updateGridMap", new Term[] {
								new Variable(Type.STRING, "event")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).updateGridMap(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {19,8,20,5},
						"Player",
						new Goal(
							new Predicate("position", new Term[] {
								new Variable(Type.STRING, "position"),
								new Variable(Type.STRING, "positionEvent")
							})
						)
					)
				}
			)
		));
	}

	public void initialize(astra.core.Agent agent) {
	}

	public Fragment createFragment(astra.core.Agent agent) throws ASTRAClassNotFoundException {
		Fragment fragment = new Fragment(this);
		fragment.addModule("console",astra.lang.Console.class,agent);
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
			astra.core.Agent agent = new GridMapGenerator().newInstance(name);
			agent.initialize(new Goal(new Predicate("main", new Term[] { argList })));
			Scheduler.schedule(agent);
		} catch (AgentCreationException e) {
			e.printStackTrace();
		} catch (ASTRAClassNotFoundException e) {
			e.printStackTrace();
		};
	}
}
