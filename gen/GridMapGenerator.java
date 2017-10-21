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
			"GridMapGenerator", new int[] {13,9,13,19},
			new GoalEvent('+',
				new Goal(
					new Predicate("init", new Term[] {})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {13,18,16,5},
				new Statement[] {
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {18,9,18,54},
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
				"GridMapGenerator", new int[] {18,53,30,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {20,8,30,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getMapSize", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {22,8,30,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {22,22,24,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {23,11,24,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("mapSize", new Term[] {
												new Variable(Type.INTEGER, "size")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {24,15,30,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {26,11,26,50},
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
						"GridMapGenerator", new int[] {29,8,30,5},
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
			"GridMapGenerator", new int[] {32,9,32,64},
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
				"GridMapGenerator", new int[] {32,63,44,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {34,8,44,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getMapSize", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {36,8,44,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {36,22,38,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {37,11,38,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("mapSize", new Term[] {
												new Variable(Type.INTEGER, "size")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {38,15,44,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {40,11,40,55},
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
						"GridMapGenerator", new int[] {43,8,44,5},
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
			"GridMapGenerator", new int[] {46,9,46,52},
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
				"GridMapGenerator", new int[] {46,51,58,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {48,8,58,5},
						new ModuleTerm("gridMap", Type.INTEGER,
							new Predicate("getMapSize", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("GridMapGenerator","gridMap")).getMapSize(
									);
								}
							}
						)
					),
					new If(
						"GridMapGenerator", new int[] {50,8,58,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {50,22,52,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {51,11,52,9},
									new Goal(
										new Predicate("doNotify", new Term[] {
											new Funct("mapSize", new Term[] {
												new Variable(Type.INTEGER, "size")
											})
										})
									)
								)
							}
						),
						new Block(
							"GridMapGenerator", new int[] {52,15,58,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {54,11,54,49},
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
						"GridMapGenerator", new int[] {57,8,58,5},
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
			"GridMapGenerator", new int[] {60,9,60,47},
			new GoalEvent('+',
				new Goal(
					new Predicate("doNotify", new Term[] {
						new Funct("mapSize", new Term[] {
							new Variable(Type.INTEGER, "X",false)
						})
					})
				)
			),
			new Comparison("==",
				new Variable(Type.INTEGER, "X"),
				Primitive.newPrimitive(100)
			),
			new Block(
				"GridMapGenerator", new int[] {60,46,62,5},
				new Statement[] {
					new Send("GridMapGenerator", new int[] {61,8,61,65},
						new Performative("request"),
						Primitive.newPrimitive("smartyPans"),
						new Predicate("taskPathReady", new Term[] {
							Primitive.newPrimitive("Ready to go")
						})
					)
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {64,9,64,46},
			new GoalEvent('+',
				new Goal(
					new Predicate("doNotify", new Term[] {
						new Funct("mapSize", new Term[] {
							new Variable(Type.INTEGER, "X",false)
						})
					})
				)
			),
			new Comparison(">",
				new Variable(Type.INTEGER, "X"),
				Primitive.newPrimitive(100)
			),
			new Block(
				"GridMapGenerator", new int[] {64,45,66,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {65,8,65,43},
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
			"GridMapGenerator", new int[] {68,9,68,63},
			new MessageEvent(
				new Performative("agree"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {68,62,70,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {69,8,69,48},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Response")
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
			"GridMapGenerator", new int[] {72,9,72,64},
			new MessageEvent(
				new Performative("refuse"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {72,63,74,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {73,8,73,48},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Response")
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
			"GridMapGenerator", new int[] {76,9,76,65},
			new MessageEvent(
				new Performative("failure"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {76,64,78,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {77,8,77,48},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								new Variable(Type.STRING, "From"),
								Operator.newOperator('+',
									Primitive.newPrimitive(" : "),
									new Variable(Type.STRING, "Response")
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
			new Goal(
				new Predicate("init", new Term[] {})
			)
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
