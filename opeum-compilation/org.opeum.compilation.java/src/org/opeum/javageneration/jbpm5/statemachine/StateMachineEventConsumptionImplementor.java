package org.opeum.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJAnnonymousInnerClass;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.NakedStateMap;
import org.opeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opeum.javageneration.jbpm5.ElementsWaitingForEvent;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.jbpm5.FromNode;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.javageneration.util.ReflectionUtil;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.bpm.INakedDeadline;
import org.opeum.metamodel.commonbehaviors.GuardedFlow;
import org.opeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opeum.metamodel.commonbehaviors.INakedEvent;
import org.opeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.statemachines.INakedCompletionEvent;
import org.opeum.metamodel.statemachines.INakedState;
import org.opeum.metamodel.statemachines.INakedStateMachine;
import org.opeum.metamodel.statemachines.INakedTransition;
import org.opeum.runtime.domain.TransitionListener;

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
