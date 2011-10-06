package org.opaeum.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJAnnonymousInnerClass;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.NakedStateMap;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.ElementsWaitingForEvent;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.FromNode;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.ReflectionUtil;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.commonbehaviors.GuardedFlow;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.statemachines.INakedCompletionEvent;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.runtime.domain.TransitionListener;

@StepDependency(phase = JavaTransformationPhase.class,requires = StateMachineImplementor.class,after = StateMachineImplementor.class)
public class StateMachineEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	private OJAnnotatedClass javaStateMachine;
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine){
		javaStateMachine = findJavaClass(umlStateMachine);
		Collection<ElementsWaitingForEvent> waitForEventElements = getWaitForEventElements(umlStateMachine);
		// TODO fire default transition after doActivity if it is a simple state
		for(ElementsWaitingForEvent wfe:waitForEventElements){
			if(wfe.getEvent() instanceof INakedDeadline){
				// fired and cancelled from task
			}else if(wfe.getEvent() instanceof INakedTimeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					NakedStateMap map = new NakedStateMap((INakedState) fromNode.getWaitingElement());
					OJOperation fire = OJUtil.findOperation(javaStateMachine, map.getOnEntryMethod());
					EventUtil.implementTimeEventRequest(fire, fire.getBody(), (INakedTimeEvent) wfe.getEvent(),getLibrary().getBusinessRole()!=null);
					OJOperation cancel = OJUtil.findOperation(javaStateMachine, map.getOnExitMethod());
					cancel.addParam("context", Jbpm5Util.getProcessContext());
					EventUtil.cancelTimer(cancel.getBody(), (INakedTimeEvent) wfe.getEvent(), "this");
				}
			}else if(wfe.getEvent() instanceof INakedChangeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					NakedStateMap map = new NakedStateMap((INakedState) fromNode.getWaitingElement());
					OJOperation fire = OJUtil.findOperation(javaStateMachine, map.getOnEntryMethod());
					EventUtil.implementChangeEventRequest(fire, (INakedChangeEvent) wfe.getEvent());
					OJOperation cancel = OJUtil.findOperation(javaStateMachine, map.getOnExitMethod());
					cancel.addParam("context", Jbpm5Util.getProcessContext());
					EventUtil.cancelChangeEvent(cancel.getBody(), (INakedChangeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventConsumption(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}
	private Collection<ElementsWaitingForEvent> getWaitForEventElements(INakedStateMachine ns){
		Map<INakedElement,ElementsWaitingForEvent> results = new HashMap<INakedElement,ElementsWaitingForEvent>();
		for(INakedTransition transition:ns.getTransitions()){
			Collection<INakedTrigger> triggers = transition.getTriggers();
			if(triggers.isEmpty()){
				addEvent(results, transition, transition.getSource().getCompletionEvent());
			}else{
				for(INakedTrigger trigger:triggers){
					INakedEvent event = trigger.getEvent();
					addEvent(results, transition, event);
				}
			}
		}
		return results.values();
	}
	private void addEvent(Map<INakedElement,ElementsWaitingForEvent> results,INakedTransition transition,INakedEvent event){
		INakedState state = transition.getSource();
		ElementsWaitingForEvent eventActions = results.get(event);
		if(eventActions == null){
			eventActions = new ElementsWaitingForEvent(event);
			results.put(event, eventActions);
		}
		eventActions.addWaitingNode(state, transition, state.getKind().isRestingState());
	}
	@Override
	protected void consumeEvent(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound){
		OJIfStatement ifGuard = null;
		IClassifier booleanType = workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
		for(GuardedFlow t:node.getConditionalTransitions()){
			OJIfStatement newIf = new OJIfStatement();
			newIf.setCondition(ValueSpecificationUtil.expressValue(operationContext, t.getGuard(), t.getContext(), booleanType));
			newIf.getThenPart().addToStatements("processDirty=consumed=true");
			maybeContinueFlow(operationContext, newIf.getThenPart(), t);
			OJBlock block1 = null;
			if(ifGuard == null){
				block1 = ifTokenFound.getThenPart();
			}else{
				block1 = new OJBlock();
				ifGuard.setElsePart(block1);
			}
			block1.addToStatements(newIf);
			ifGuard = newIf;
		}
		OJBlock block = null;
		if(ifGuard == null){
			block = ifTokenFound.getThenPart();
		}else{
			block = new OJBlock();
			ifGuard.setElsePart(block);
		}
		GuardedFlow flow = node.getDefaultTransition();
		if(flow != null){
			// default flow/transition
			block.addToStatements("processDirty=consumed=true");
			maybeContinueFlow(operationContext, block, flow);
		}
	}
	private void maybeContinueFlow(OJOperation operationContext,OJBlock block,GuardedFlow flow){
		operationContext.getOwner().addToImports(UML_NODE_INSTANCE);
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(TransitionListener.class));
		OJAnnonymousInnerClass listener = new OJAnnonymousInnerClass(operationContext.getOwner().getPathName(), "listener",
				ReflectionUtil.getUtilInterface(TransitionListener.class));
		block.addToLocals(listener);
		OJAnnotatedOperation onTransition = new OJAnnotatedOperation("onTransition");
		listener.getClassDeclaration().addToOperations(onTransition);
		INakedTransition transition = (INakedTransition) flow;
		if(transition.getEffect() instanceof INakedActivity){
			for(OJParameter p:operationContext.getParameters()){
				p.setFinal(true);
			}
			SimpleActivityMethodImplementor ai = new SimpleActivityMethodImplementor();
			ai.initialize(javaModel, config, textWorkspace, workspace);
			ai.setWorkspace(workspace);
			ai.implementActivityOn((INakedActivity) transition.getEffect(), onTransition);
			operationContext.getOwner().addToImports(listener.getClassDeclaration().getImports());
		}else if(transition.getEffect() instanceof INakedOpaqueBehavior){
			INakedOpaqueBehavior b = (INakedOpaqueBehavior) transition.getEffect();
			if(b.getBody() != null){
				IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				String expression = ValueSpecificationUtil.expressValue(onTransition, b.getBody(), transition.getStateMachine(), voidType);
				onTransition.getBody().addToStatements(expression);
			}
		}
		block.addToStatements("waitingNode.transitionToNode(" + javaStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(transition.getTarget())
				+ ".getId(), listener)");
	}
	@Override
	protected void implementEventConsumerBody(ElementsWaitingForEvent eventActions,OJAnnotatedOperation listener,OJIfStatement ifProcessActive){
		if(eventActions.getEvent() instanceof INakedMessageEvent || eventActions.getEvent() instanceof INakedCompletionEvent){
			// Message event
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeEventWithoutSourceNodeInstanceUniqueId(listener, ifProcessActive, node);
			}
		}else{
			// previously triggered from a known node
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeEventWithSourceNodeInstanceUniqueId(listener, ifProcessActive, node);
			}
		}
	}
}
