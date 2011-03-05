package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJAnnonymousInnerClass;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
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

import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

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
					Jbpm5Util.implementTimeEvent(fire, (INakedTimeEvent) wfe.getEvent(), we, fromNode.getTransitions());
					String cancelOper = map.getCancelTimersMethod();
					OJOperation cancel = javaStateMachine.findOperation(cancelOper, Collections.EMPTY_LIST);
					if (cancel == null) {
						cancel = new OJAnnotatedOperation();
						cancel.setName(cancelOper);
						javaStateMachine.addToOperations(cancel);
					}
					Jbpm5Util.cancelTimer(cancel, (INakedTimeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventHandling(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}

	private Collection<WaitForEventElements> getWaitForEventElements(INakedStateMachine ns) {
		Map<INakedElement, WaitForEventElements> results = new HashMap<INakedElement, WaitForEventElements>();
		for (INakedTransition transition : ns.getTransitions()) {
			INakedElement event = transition.getTrigger()==null?null:transition.getTrigger().getEvent();
			INakedState state = transition.getSource();
			if (event == null) {
				if (transition.getSource().getKind() != StateKind.INITIAL) {
					// Naughty!!!
					event = state;
				} else {
					continue;
				}
			}
			WaitForEventElements eventActions = results.get(event);
			if (eventActions == null) {
				eventActions = new WaitForEventElements(event);
				results.put(event, eventActions);
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
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(UmlNodeInstance.class));
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(TransitionListener.class));
		OJAnnonymousInnerClass listener = new OJAnnonymousInnerClass(operationContext.getOwner().getPathName(), "listener", ReflectionUtil.getUtilInterface(TransitionListener.class));
		block.addToLocals(listener);
		OJAnnotatedOperation onTransition = new OJAnnotatedOperation("onTransition");
		listener.getClassDeclaration().addToOperations(onTransition);
		INakedTransition transition = (INakedTransition) flow;
		if (transition.getEffect() instanceof INakedActivity) {
			for (OJParameter p : operationContext.getParameters()) {
				p.setFinal(true);
			}
			SimpleActivityMethodImplementor ai = new SimpleActivityMethodImplementor();
			ai.initialize(javaModel, config, textWorkspace, this.transformationContext);
			ai.setWorkspace(workspace);
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
		block.addToStatements("waitingNode.takeTransition(\"" + calculateTargetNodeName(transition) + "\", listener)");
	}

	private String calculateTargetNodeName(INakedTransition flow) {
		if (flow.getTarget().getIncoming().size() > 1) {
			return Jbpm5Util.getArtificialJoinName(flow.getTarget());
		}
		return flow.getTarget().getMappingInfo().getPersistentName().toString();
	}
}
