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
						return ((api.astrasupport.Unity) agent.getModule("Player","unityModule")).event(
							predicate.getTerm(0),
							predicate.getTerm(1)
						);
					}
				}
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {10,59,15,5},
				new Statement[] {
					new Declaration(
						new Variable(Type.STRING, "direction"),
						"Player", new int[] {12,8,15,5},
						new ModuleTerm("positionModule", Type.STRING,
							new Predicate("getDirections", new Term[] {
								new Variable(Type.STRING, "position")
							}),
							new ModuleTermAdaptor() {
								public Object invoke(Intention intention, Predicate predicate) {
									return ((api.astrasupport.Position) intention.getModule("Player","positionModule")).getDirections(
										(java.lang.String) intention.evaluate(predicate.getTerm(0))
									);
								}
								public Object invoke(BindingsEvaluateVisitor visitor, Predicate predicate) {
									return ((api.astrasupport.Position) visitor.agent().getModule("Player","positionModule")).getDirections(
										(java.lang.String) visitor.evaluate(predicate.getTerm(0))
									);
								}
							}
						)
					),
					new ModuleCall("console",
						"Player", new int[] {13,8,13,81},
						new Predicate("println", new Term[] {
							Operator.newOperator('+',
								Primitive.newPrimitive("direction: "),
								Operator.newOperator('+',
									new Variable(Type.STRING, "direction"),
									Operator.newOperator('+',
										Primitive.newPrimitive(" for position: "),
										new Variable(Type.STRING, "position")
									)
								)
							)
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((astra.lang.Console) intention.getModule("Player","console")).println(
									(java.lang.String) intention.evaluate(predicate.getTerm(0))
								);
							}
						}
					),
					new Subgoal(
						"Player", new int[] {14,8,15,5},
						new Goal(
							new Predicate("sendDirection", new Term[] {
								new Variable(Type.STRING, "direction")
							})
						)
					)
				}
			)
		));
		addRule(new Rule(
			"Player", new int[] {17,9,17,45},
			new GoalEvent('+',
				new Goal(
					new Predicate("sendDirection", new Term[] {
						new Variable(Type.STRING, "Directions",false)
					})
				)
			),
			Predicate.TRUE,
			new Block(
				"Player", new int[] {17,44,19,5},
				new Statement[] {
					new ModuleCall("unityModule",
						"Player", new int[] {18,8,18,57},
						new Predicate("sendCommand", new Term[] {
							Primitive.newPrimitive("position"),
							new ListTerm(new Term[] {
								new Variable(Type.STRING, "Directions")
							})
						}),
						new DefaultModuleCallAdaptor() {
							public boolean inline() {
								return false;
							}

							public boolean invoke(Intention intention, Predicate predicate) {
								return ((api.astrasupport.Unity) intention.getModule("Player","unityModule")).sendCommand(
									(java.lang.String) intention.evaluate(predicate.getTerm(0)),
									(astra.term.ListTerm) intention.evaluate(predicate.getTerm(1))
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
