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
			"GridMapGenerator", new int[] {14,9,14,19},
			new GoalEvent('+',
				new Goal(
					new Predicate("init", new Term[] {})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {14,18,17,5},
				new Statement[] {
				}
			)
		));
		addRule(new Rule(
			"GridMapGenerator", new int[] {19,9,19,54},
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
				"GridMapGenerator", new int[] {19,53,31,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {21,8,31,5},
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
						"GridMapGenerator", new int[] {23,8,31,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {23,22,25,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {24,11,25,9},
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
							"GridMapGenerator", new int[] {25,15,31,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {27,11,27,50},
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
						"GridMapGenerator", new int[] {30,8,31,5},
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
			"GridMapGenerator", new int[] {33,9,33,64},
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
				"GridMapGenerator", new int[] {33,63,45,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {35,8,45,5},
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
						"GridMapGenerator", new int[] {37,8,45,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {37,22,39,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {38,11,39,9},
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
							"GridMapGenerator", new int[] {39,15,45,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {41,11,41,55},
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
						"GridMapGenerator", new int[] {44,8,45,5},
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
			"GridMapGenerator", new int[] {47,9,47,52},
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
				"GridMapGenerator", new int[] {47,51,59,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.INTEGER, "size"),
						"GridMapGenerator", new int[] {49,8,59,5},
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
						"GridMapGenerator", new int[] {51,8,59,5},
						new Comparison(">",
							new Variable(Type.INTEGER, "size"),
							Primitive.newPrimitive(99)
						),
						new Block(
							"GridMapGenerator", new int[] {51,22,53,9},
							new Statement[] {
								new Subgoal(
									"GridMapGenerator", new int[] {52,11,53,9},
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
							"GridMapGenerator", new int[] {53,15,59,5},
							new Statement[] {
								new ModuleCall("gridMap",
									"GridMapGenerator", new int[] {55,11,55,49},
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
						"GridMapGenerator", new int[] {58,8,59,5},
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
			"GridMapGenerator", new int[] {61,9,61,47},
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
				"GridMapGenerator", new int[] {61,46,63,5},
				new Statement[] {
					new Send("GridMapGenerator", new int[] {62,8,62,65},
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
			"GridMapGenerator", new int[] {65,9,65,37},
			new GoalEvent('+',
				new Goal(
					new Predicate("doNotify", new Term[] {
						new Funct("mapSize", new Term[] {
							new Variable(Type.INTEGER, "X",false)
						})
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {65,36,67,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {66,8,66,43},
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
			"GridMapGenerator", new int[] {69,9,69,63},
			new MessageEvent(
				new Performative("agree"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {69,62,71,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {70,8,70,48},
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
			"GridMapGenerator", new int[] {73,9,73,64},
			new MessageEvent(
				new Performative("refuse"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {73,63,75,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {74,8,74,48},
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
			"GridMapGenerator", new int[] {77,9,77,65},
			new MessageEvent(
				new Performative("failure"),
				new Variable(Type.STRING, "From",false),
				new Predicate("state", new Term[] {
					new Variable(Type.STRING, "Response",false)
				})
			),
			Predicate.TRUE,
			new Block(
				"GridMapGenerator", new int[] {77,64,79,5},
				new Statement[] {
					new ModuleCall("console",
						"GridMapGenerator", new int[] {78,8,78,48},
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
