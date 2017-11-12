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


public class SmartyPans extends ASTRAClass {
	public SmartyPans() {
		setParents(new Class[] {Player.class});
		addRule(new Rule(
			"SmartyPans", new int[] {12,9,12,69},
			new MessageEvent(
				new Performative("request"),
				new Variable(Type.STRING, "From",false),
				new Predicate("taskPathReady", new Term[] {
					new Variable(Type.STRING, "Ready",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"SmartyPans", new int[] {12,68,24,5},
				new Statement[] {
					new If(
						"SmartyPans", new int[] {13,8,24,5},
						new Comparison("==",
							new Variable(Type.STRING, "Ready"),
							Primitive.newPrimitive("Ready")
						),
						new Block(
							"SmartyPans", new int[] {13,29,21,9},
							new Statement[] {
								new Send("SmartyPans", new int[] {14,12,14,45},
									new Performative("agree"),
									new Variable(Type.STRING, "From"),
									new Predicate("state", new Term[] {
										Primitive.newPrimitive("Agree")
									})
								),
								new TryRecover(
									"SmartyPans", new int[] {15,12,21,9},
									new Block(
										"SmartyPans", new int[] {15,16,17,13},
										new Statement[] {
											new Subgoal(
												"SmartyPans", new int[] {16,16,17,13},
												new Goal(
													new Predicate("satrtMovingNow", new Term[] {
														Primitive.newPrimitive("Ready")
													})
												)
											)
										}
									),
									new Block(
										"SmartyPans", new int[] {17,22,21,9},
										new Statement[] {
											new Send("SmartyPans", new int[] {18,16,18,53},
												new Performative("failure"),
												new Variable(Type.STRING, "From"),
												new Predicate("state", new Term[] {
													Primitive.newPrimitive("Failure")
												})
											)
										}
									)
								)
							}
						),
						new Block(
							"SmartyPans", new int[] {21,15,24,5},
							new Statement[] {
								new Send("SmartyPans", new int[] {22,12,22,47},
									new Performative("refuse"),
									new Variable(Type.STRING, "From"),
									new Predicate("state", new Term[] {
										Primitive.newPrimitive("Refuse")
									})
								)
							}
						)
					)
				}
			)
		));
		addRule(new Rule(
			"SmartyPans", new int[] {26,9,26,40},
			new GoalEvent('+',
				new Goal(
					new Predicate("satrtMovingNow", new Term[] {
						new Variable(Type.STRING, "Text",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"SmartyPans", new int[] {26,39,28,5},
				new Statement[] {
					new Subgoal(
						"SmartyPans", new int[] {27,8,28,5},
						new Goal(
							new Predicate("messaging", new Term[] {
								Primitive.newPrimitive("messaging"),
								Primitive.newPrimitive("{'message': 'Ready'}")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"SmartyPans", new int[] {30,9,30,54},
			new GoalEvent('+',
				new Goal(
					new Predicate("messaging", new Term[] {
						new Variable(Type.STRING, "messaging",false),
						new Variable(Type.STRING, "event",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"SmartyPans", new int[] {30,53,33,5},
				new Statement[] {
					new Assignment(
						new Variable(Type.STRING, "messaging"),
						"SmartyPans", new int[] {31,8,33,5},
						new ModuleTerm("messagingModule", Type.STRING,
							new Predicate("sendMessage", new Term[] {
								new Variable(Type.STRING, "event")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.Messaging) intention.getModule("SmartyPans","messagingModule")).sendMessage(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.Messaging) visitor.agent().getModule("SmartyPans","messagingModule")).sendMessage(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new Subgoal(
						"SmartyPans", new int[] {32,8,33,5},
						new Goal(
							new Predicate("sendCommand", new Term[] {
								Primitive.newPrimitive("messaging"),
								new Variable(Type.STRING, "messaging")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"SmartyPans", new int[] {35,9,35,64},
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
				"SmartyPans", new int[] {35,63,42,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"SmartyPans", new int[] {37,8,42,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getMapSize", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("SmartyPans","gridMap")).getMapSize(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("SmartyPans","gridMap")).getMapSize(
									);
								}
							}
						)
					),
					new ModuleCall("console",
						"SmartyPans", new int[] {38,8,38,54},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								Primitive.newPrimitive("Knowledge map size: "),
								new Variable(Type.INTEGER, "size")
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("SmartyPans","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					),
					new Assignment(
						new Variable(Type.STRING, "positionVector"),
						"SmartyPans", new int[] {40,8,42,5},
						new ModuleTerm("gridMap", Type.STRING,
							new Predicate("getValidatedDirectionsVector", new Term[] {
								new Variable(Type.STRING, "event")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("SmartyPans","gridMap")).getValidatedDirectionsVector(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("SmartyPans","gridMap")).getValidatedDirectionsVector(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new Subgoal(
						"SmartyPans", new int[] {41,8,42,5},
						new Goal(
							new Predicate("sendCommand", new Term[] {
								Primitive.newPrimitive("position_vector"),
								new Variable(Type.STRING, "positionVector")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"SmartyPans", new int[] {44,9,44,58},
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
				"SmartyPans", new int[] {44,57,46,5},
				new Statement[] {
					new ModuleCall("unityModule",
						"SmartyPans", new int[] {45,8,45,68},
						new Predicate("sendCommand", new Term[] {
							new ModuleTerm("system", Type.STRING,
								new Predicate("name", new Term[] {}),
								new ModuleTermAdaptor() {
									public Object invoke(Intention intention, Predicate predicate) {
										return ((astra.lang.System) intention.getModule("SmartyPans","system")).name(
										);
									}
									public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
										return ((astra.lang.System) visitor.agent().getModule("SmartyPans","system")).name(
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
								return ((api.modules.Unity) intention.getModule("SmartyPans","unityModule")).sendCommand(
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
			astra.core.Agent agent = new SmartyPans().newInstance(name);
			agent.initialize(new Goal(new Predicate("main", new Term[] { argList })));
			Scheduler.schedule(agent);
		} catch (AgentCreationException e) {
			e.printStackTrace();
		} catch (ASTRAClassNotFoundException e) {
			e.printStackTrace();
		};
	}
}
