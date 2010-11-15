package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javametamodel.OJAnnonymousInnerClass;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.StateKind;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class StateMachineEventHandlerInserter extends AbstractEventHandlerInserter {
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine) {
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		Collection<WaitForEventElements> waitForEventElements = getWaitForEventElements(umlStateMachine);
		// TODO fire default transition after doActivity if it is a simple state
		for (WaitForEventElements wfe : waitForEventElements) {
			if (wfe.getEvent() instanceof INakedTimeEvent) {
				for (FromNode fromNode : wfe.getWaitingNodes()) {
					NakedStateMap map = new NakedStateMap((INakedState) fromNode.getWaitingElement());
					INakedElement we = fromNode.getWaitingElement();
					String fireOper = map.getFireTimersMethod();
					OJOperation fire = javaStateMachine.findOperation(fireOper, Collections.EMPTY_LIST);
					if (fire == null) {
						fire = new OJAnnotatedOperation();
						fire.setName(fireOper);
						javaStateMachine.addToOperations(fire);
					}
					BpmUtil.implementTimeEvent(fire, (INakedTimeEvent) wfe.getEvent(), we, fromNode.getTransitions());
					String cancelOper = map.getCancelTimersMethod();
					OJOperation cancel = javaStateMachine.findOperation(cancelOper, Collections.EMPTY_LIST);
					if (cancel == null) {
						cancel = new OJAnnotatedOperation();
						cancel.setName(cancelOper);
						javaStateMachine.addToOperations(cancel);
					}
					BpmUtil.cancelTimer(cancel, (INakedTimeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventHandling(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}

	private Collection<WaitForEventElements> getWaitForEventElements(INakedStateMachine ns) {
		Map<INakedElement, WaitForEventElements> results = new HashMap<INakedElement, WaitForEventElements>();
		for (INakedTransition transition : ns.getTransitions()) {
			INakedElement trigger = transition.getTrigger();
			INakedState state = transition.getSource();
			if (trigger == null) {
				if (transition.getSource().getKind() != StateKind.INITIAL) {
					// Naughty!!!
					trigger = state;
				} else {
					continue;
				}
			}
			WaitForEventElements eventActions = results.get(trigger);
			if (eventActions == null) {
				eventActions = new WaitForEventElements(trigger);
				results.put(trigger, eventActions);
			}
			eventActions.addWaitingNode(state, transition, state.getKind().isRestingState());
		}
		return results.values();
	}

	@Override
	protected void implementEventConsumption(FromNode node, OJIfStatement ifNotNull) {
		// TODO might want to cache the event parameters somewhere, or not
	}

	@Override
	protected void maybeContinueFlow(OJOperation operationContext, OJBlock block, GuardedFlow flow) {
		OJIfStatement ifUmlNode = new OJIfStatement("waitingToken instanceof UmlNode", "UmlNode umlNode=(UmlNode)waitingToken");
		block.addToStatements(ifUmlNode);
		operationContext.getOwner().addToImports("net.sf.nakeduml.util.UmlNode");
		operationContext.getOwner().addToImports("net.sf.nakeduml.util.TransitionListener");
		OJAnnonymousInnerClass listener = new OJAnnonymousInnerClass(operationContext.getOwner().getPathName(), "listener", new OJPathName("net.sf.nakeduml.util.TransitionListener"));
		ifUmlNode.getThenPart().addToLocals(listener);
		OJAnnotatedOperation onTransition = new OJAnnotatedOperation("onTransition");
		listener.getClassDeclaration().addToOperations(onTransition);
		INakedTransition transition = (INakedTransition) flow;
		if (transition.getEffect() instanceof INakedActivity) {
			for (OJParameter p : operationContext.getParameters()) {
				p.setFinal(true);
			}
			SimpleActivityMethodImplementor ai = new SimpleActivityMethodImplementor();
			ai.initialize(workspace, javaModel, config, textWorkspace);
			ai.implementActivityOn((INakedActivity) transition.getEffect(), onTransition);
			operationContext.getOwner().addToImports(listener.getClassDeclaration().getImports());
		} else if (transition.getEffect() instanceof INakedOpaqueBehavior) {
			INakedOpaqueBehavior b = (INakedOpaqueBehavior) transition.getEffect();
			if (b.getBody() != null) {
				IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				String expression = ValueSpecificationUtil
						.expressValue(onTransition, b.getBody(), transition.getStateMachine(), voidType);
				onTransition.getBody().addToStatements(expression);
			}
		}
		ifUmlNode.getThenPart().addToStatements("umlNode.takeTransition(\"" + calculateTargetNodeName(transition) + "\", listener)");
	}

	private String calculateTargetNodeName(INakedTransition flow) {
		if (flow.getTarget().getIncoming().size() > 1) {
			return BpmUtil.getArtificialJoinName(flow.getTarget());
		}
		return flow.getTarget().getMappingInfo().getPersistentName().toString();
	}
}
