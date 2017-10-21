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
				"SmartyPans", new int[] {12,68,25,5},
				new Statement[] {
					new If(
						"SmartyPans", new int[] {14,8,25,5},
						new Comparison("==",
							new Variable(Type.STRING, "Ready"),
							Primitive.newPrimitive("Ready to go")
						),
						new Block(
							"SmartyPans", new int[] {14,35,22,9},
							new Statement[] {
								new Send("SmartyPans", new int[] {15,12,15,72},
									new Performative("agree"),
									new Variable(Type.STRING, "From"),
									new Predicate("state", new Term[] {
										Operator.newOperator('+',
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
											Primitive.newPrimitive(" agreed to move!")
										)
									})
								),
								new TryRecover(
									"SmartyPans", new int[] {16,12,22,9},
									new Block(
										"SmartyPans", new int[] {16,16,18,13},
										new Statement[] {
											new Subgoal(
												"SmartyPans", new int[] {17,16,18,13},
												new Goal(
													new Predicate("satrtMovingNow", new Term[] {
														Primitive.newPrimitive(" received a message from Map generator")
													})
												)
											)
										}
									),
									new Block(
										"SmartyPans", new int[] {18,22,22,9},
										new Statement[] {
											new Send("SmartyPans", new int[] {19,16,19,76},
												new Performative("failure"),
												new Variable(Type.STRING, "From"),
												new Predicate("state", new Term[] {
													Operator.newOperator('+',
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
														Primitive.newPrimitive(" fails to move")
													)
												})
											)
										}
									)
								)
							}
						),
						new Block(
							"SmartyPans", new int[] {22,15,25,5},
							new Statement[] {
								new Send("SmartyPans", new int[] {23,12,23,97},
									new Performative("refuse"),
									new Variable(Type.STRING, "From"),
									new Predicate("state", new Term[] {
										Operator.newOperator('+',
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
											Primitive.newPrimitive(" refused to move, path is not ready yet!")
										)
									})
								)
							}
						)
					)
				}
			)
		));
		addRule(new Rule(
			"SmartyPans", new int[] {27,9,27,40},
			new GoalEvent('+',
				new Goal(
					new Predicate("satrtMovingNow", new Term[] {
						new Variable(Type.STRING, "Text",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"SmartyPans", new int[] {27,39,32,5},
				new Statement[] {
					new ModuleCall("console",
						"SmartyPans", new int[] {29,8,29,47},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								Primitive.newPrimitive(" SmartyPans: "),
								new Variable(Type.STRING, "Text")
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
					new Declaration(
						new Variable(Type.STRING, "map"),
						"SmartyPans", new int[] {30,8,32,5},
						new ModuleTerm("gridMap", Type.STRING,
							new Predicate("getBreadCrumbs", new Term[] {}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.modules.ai.GridMap) intention.getModule("SmartyPans","gridMap")).getBreadCrumbs(
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.modules.ai.GridMap) visitor.agent().getModule("SmartyPans","gridMap")).getBreadCrumbs(
									);
								}
							}
						)
					),
					new ModuleCall("console",
						"SmartyPans", new int[] {31,8,31,59},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								Primitive.newPrimitive("SmartyPans - get the map: "),
								new Variable(Type.STRING, "map")
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
