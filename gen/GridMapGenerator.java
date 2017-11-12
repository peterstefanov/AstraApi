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
			"GridMapGenerator", new int[] {15,9,15,19},
			new GoalEvent('+',
				new Goal(
					new Predicate("init", new Term[] {})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {15,18,18,5},
				new Statement[] {
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {20,9,20,54},
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
				"GridMapGenerator", new int[] {20,53,32,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "count"),
						"GridMapGenerator", new int[] {22,8,32,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getFinishCount", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {24,8,32,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "count"),
							Primitive.newPrimitive(1)
						),
						new Block(
							"GridMapGenerator", new int[] {24,22,26,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {25,11,26,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("finishCount", new Term[] {
												new Variable(Type.INTEGER, "count")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {26,15,32,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {28,11,28,50},
									new Predicate("updateGridMap", new Term[] {
										new Variable(Type.STRING, "collision"),
										new Variable(Type.STRING, "event")
									}),
									new DefaultModuleCallAdaptor() {
										public boolean inline() {
											return false;
										}

										public boolean invoke(Intention intention, Predicate predicate) {
											return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
												(java.lang.String) intention.evaluate(predicate.getTerm(0)),
												(java.lang.String) intention.evaluate(predicate.getTerm(1))
											);
										}
									}
								)
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {31,8,32,5},
						"Player",
						new Goal(
							new Predicate("collision", new Term[] {
								new Variable(Type.STRING, "collision"),
								new Variable(Type.STRING, "event")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {34,9,34,64},
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
				"GridMapGenerator", new int[] {34,63,46,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "count"),
						"GridMapGenerator", new int[] {36,8,46,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getFinishCount", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {38,8,46,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "count"),
							Primitive.newPrimitive(1)
						),
						new Block(
							"GridMapGenerator", new int[] {38,22,40,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {39,11,40,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("finishCount", new Term[] {
												new Variable(Type.INTEGER, "count")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {40,15,46,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {42,11,42,55},
									new Predicate("updateGridMap", new Term[] {
										new Variable(Type.STRING, "positionVector"),
										new Variable(Type.STRING, "event")
									}),
									new DefaultModuleCallAdaptor() {
										public boolean inline() {
											return false;
										}

										public boolean invoke(Intention intention, Predicate predicate) {
											return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
												(java.lang.String) intention.evaluate(predicate.getTerm(0)),
												(java.lang.String) intention.evaluate(predicate.getTerm(1))
											);
										}
									}
								)
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {45,8,46,5},
						"Player",
						new Goal(
							new Predicate("positionVector", new Term[] {
								new Variable(Type.STRING, "positionVector"),
								new Variable(Type.STRING, "event")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {48,9,48,52},
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
				"GridMapGenerator", new int[] {48,51,60,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "count"),
						"GridMapGenerator", new int[] {50,8,60,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getFinishCount", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getFinishCount(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {52,8,60,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "count"),
							Primitive.newPrimitive(1)
						),
						new Block(
							"GridMapGenerator", new int[] {52,22,54,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {53,11,54,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("finishCount", new Term[] {
												new Variable(Type.INTEGER, "count")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {54,15,60,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {56,11,56,49},
									new Predicate("updateGridMap", new Term[] {
										new Variable(Type.STRING, "position"),
										new Variable(Type.STRING, "event")
									}),
									new DefaultModuleCallAdaptor() {
										public boolean inline() {
											return false;
										}

										public boolean invoke(Intention intention, Predicate predicate) {
											return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).updateGridMap(
												(java.lang.String) intention.evaluate(predicate.getTerm(0)),
												(java.lang.String) intention.evaluate(predicate.getTerm(1))
											);
										}
									}
								)
							}
						)
					),
					new ScopedSubgoal(
						"GridMapGenerator", new int[] {59,8,60,5},
						"Player",
						new Goal(
							new Predicate("position", new Term[] {
								new Variable(Type.STRING, "position"),
								new Variable(Type.STRING, "event")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {62,9,62,67},
			new GoalEvent('+',
				new Goal(
					new Predicate("doNotify", new Term[] {
						new Funct("finishCount", new Term[] {
							new Variable(Type.INTEGER, "X",false)
						})
					})
				)
			),
			new AND(
				new Comparison("==",
					new Variable(Type.INTEGER, "X"),
					Primitive.newPrimitive(2)
				),
				new NOT(
					new Predicate("state", new Term[] {
						Primitive.newPrimitive("Agree")
					})
				)
			),
			new Block(
				"GridMapGenerator", new int[] {62,66,64,5},
				new Statement[] {
					new Send("GridMapGenerator", new int[] {63,8,63,59},
						new Performative("request"),
						Primitive.newPrimitive("smartyPans"),
						new Predicate("taskPathReady", new Term[] {
							Primitive.newPrimitive("Ready")
						})
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {66,9,66,41},
			new GoalEvent('+',
				new Goal(
					new Predicate("doNotify", new Term[] {
						new Funct("finishCount", new Term[] {
							new Variable(Type.INTEGER, "X",false)
						})
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {66,40,68,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {67,8,67,43},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								Primitive.newPrimitive("DO NOTHING: "),
								new Variable(Type.INTEGER, "X")
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("GridMapGenerator","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {70,9,70,60},
			new MessageEvent(
				new Performative("agree"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Agree",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {70,59,73,5},
				new Statement[] {
					new BeliefUpdate('+',
						"GridMapGenerator", new int[] {71,8,73,5},
						new Predicate("state", new Term[] {
							new Variable(Type.STRING, "Agree")
						})
					),
					new ModuleCall("console",
						"GridMapGenerator", new int[] {72,8,72,45},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Agree")
								)
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("GridMapGenerator","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {75,9,75,62},
			new MessageEvent(
				new Performative("refuse"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Refuse",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {75,61,78,5},
				new Statement[] {
					new BeliefUpdate('+',
						"GridMapGenerator", new int[] {76,8,78,5},
						new Predicate("state", new Term[] {
							new Variable(Type.STRING, "Refuse")
						})
					),
					new ModuleCall("console",
						"GridMapGenerator", new int[] {77,8,77,46},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Refuse")
								)
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("GridMapGenerator","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {80,9,80,64},
			new MessageEvent(
				new Performative("failure"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Failure",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {80,63,83,5},
				new Statement[] {
					new BeliefUpdate('+',
						"GridMapGenerator", new int[] {81,8,83,5},
						new Predicate("state", new Term[] {
							new Variable(Type.STRING, "Failure")
						})
					),
					new ModuleCall("console",
						"GridMapGenerator", new int[] {82,8,82,47},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Failure")
								)
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("GridMapGenerator","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					)
				}
			)
		));
	}

	public void initialize(astra.core.Agent agent) {
		agent.initialize(
			new Predicate("state", new Term[] {
				Primitive.newPrimitive("Idle")
			})
		);
	}

	public Fragment createFragment(astra.core.Agent agent) throws ASTRAClassNotFoundException {
		Fragment fragment = new Fragment(this);
		fragment.addModule("console",astra.lang.Console.class,agent);
		fragment.addModule("messaging",astra.lang.Messaging.class,agent);
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
