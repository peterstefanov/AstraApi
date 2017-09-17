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


public class Player extends ASTRAClass {
	public Player() {
		setParents(new Class[] {Unity.class});
		addRule(new Rule(
			"Player", new int[] {6,9,6,36},
			new GoalEvent('+',
				new Goal(
					new Predicate("unity", new Term[] {
						new Variable(new ObjectType(api.AstraApi.class), "api",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {6,35,8,5},
				new Statement[] {
					new ScopedSubgoal(
						"Player", new int[] {7,8,8,5},
						"Unity",
						new Goal(
							new Predicate("unity", new Term[] {
								new Variable(new ObjectType(api.AstraApi.class), "api")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"Player", new int[] {10,9,10,60},
			new ModuleEvent("unityModule",
				"$ue",
				new Predicate("event", new Term[] {
					Primitive.newPrimitive("position"),
					new ListTerm(new Term[] {
						new Variable(Type.STRING, "position",false)
					})
				}),
				new ModuleEventAdaptor() {
					public Event generate(astra.core.Agent agent, Predicate predicate) {
						return ((api.modules.Unity) agent.getModule("Player","unityModule")).event(
							predicate.getTerm(0),
							predicate.getTerm(1)
						);
					}
				}
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {10,59,13,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "direction"),
						"Player", new int[] {11,8,13,5},
						new ModuleTerm("positionModule", Type.STRING,
							new Predicate("getDirections", new Term[] {
								new Variable(Type.STRING, "position")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.Position) intention.getModule("Player","positionModule")).getDirections(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.Position) visitor.agent().getModule("Player","positionModule")).getDirections(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new Subgoal(
						"Player", new int[] {12,8,13,5},
						new Goal(
							new Predicate("sendCommand", new Term[] {
								Primitive.newPrimitive("position"),
								new Variable(Type.STRING, "direction")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"Player", new int[] {15,9,15,70},
			new ModuleEvent("unityModule",
				"$ue",
				new Predicate("event", new Term[] {
					Primitive.newPrimitive("collision"),
					new ListTerm(new Term[] {
						new Variable(Type.STRING, "cardinalDirection",false)
					})
				}),
				new ModuleEventAdaptor() {
					public Event generate(astra.core.Agent agent, Predicate predicate) {
						return ((api.modules.Unity) agent.getModule("Player","unityModule")).event(
							predicate.getTerm(0),
							predicate.getTerm(1)
						);
					}
				}
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {15,69,18,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "collision"),
						"Player", new int[] {16,8,18,5},
						new ModuleTerm("collisionModule", Type.STRING,
							new Predicate("getDirections", new Term[] {
								new Variable(Type.STRING, "cardinalDirection")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.Collision) intention.getModule("Player","collisionModule")).getDirections(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.Collision) visitor.agent().getModule("Player","collisionModule")).getDirections(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new Subgoal(
						"Player", new int[] {17,8,18,5},
						new Goal(
							new Predicate("sendCommand", new Term[] {
								Primitive.newPrimitive("collision"),
								new Variable(Type.STRING, "collision")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"Player", new int[] {20,9,20,76},
			new ModuleEvent("unityModule",
				"$ue",
				new Predicate("event", new Term[] {
					Primitive.newPrimitive("direction_vector"),
					new ListTerm(new Term[] {
						new Variable(Type.STRING, "directionsVector",false)
					})
				}),
				new ModuleEventAdaptor() {
					public Event generate(astra.core.Agent agent, Predicate predicate) {
						return ((api.modules.Unity) agent.getModule("Player","unityModule")).event(
							predicate.getTerm(0),
							predicate.getTerm(1)
						);
					}
				}
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {20,75,23,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "direction"),
						"Player", new int[] {21,8,23,5},
						new ModuleTerm("directionVector", Type.STRING,
							new Predicate("getDirections", new Term[] {
								new Variable(Type.STRING, "directionsVector")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.DirectionVector) intention.getModule("Player","directionVector")).getDirections(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.DirectionVector) visitor.agent().getModule("Player","directionVector")).getDirections(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new Subgoal(
						"Player", new int[] {22,8,23,5},
						new Goal(
							new Predicate("sendCommand", new Term[] {
								Primitive.newPrimitive("direction_vector"),
								new Variable(Type.STRING, "direction")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"Player", new int[] {25,9,25,58},
			new GoalEvent('+',
				new Goal(
					new Predicate("sendCommand", new Term[] {
						new Variable(Type.STRING, "EventType",false),
						new Variable(Type.STRING, "Command",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {25,57,27,5},
				new Statement[] {
					new ModuleCall("unityModule",
						"Player", new int[] {26,8,26,68},
						new Predicate("sendCommand", new Term[] {
							new ModuleTerm("system", Type.STRING,
								new Predicate("name", new Term[] {}),
								new ModuleTermAdaptor() {
									public Object invoke(Intention intention, Predicate predicate) {
										return ((astra.lang.System) intention.getModule("Player","system")).name(
										);
									}
									public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
										return ((astra.lang.System) visitor.agent().getModule("Player","system")).name(
										);
									}
								}
							),
							new Variable(Type.STRING, "EventType"),
							new ListTerm(new Term[] {
								new Variable(Type.STRING, "Command")
							})
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((api.modules.Unity) intention.getModule("Player","unityModule")).sendCommand(
									(java.lang.String) intention.evaluate(predicate.getTerm(0)),
									(java.lang.String) intention.evaluate(predicate.getTerm(1)),
									(astra.term.ListTerm) intention.evaluate(predicate.getTerm(2))
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
		fragment.addModule("console",astra.lang.Console.class,agent);
		fragment.addModule("system",astra.lang.System.class,agent);
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
			astra.core.Agent agent = new Player().newInstance(name);
			agent.initialize(new Goal(new Predicate("main", new Term[] { argList })));
			Scheduler.schedule(agent);
		} catch (AgentCreationException e) {
			e.printStackTrace();
		} catch (ASTRAClassNotFoundException e) {
			e.printStackTrace();
		};
	}
}
