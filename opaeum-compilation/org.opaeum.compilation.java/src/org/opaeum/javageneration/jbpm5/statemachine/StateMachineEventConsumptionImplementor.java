package org.opaeum.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
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
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.ElementsWaitingForEvent;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.FromNode;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.util.ReflectionUtil;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OclBehaviorContext;
import org.opaeum.runtime.domain.TransitionListener;

@StepDependency(phase = JavaTransformationPhase.class,requires = StateMachineImplementor.class,after = StateMachineImplementor.class)
public class StateMachineEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine umlStateMachine){
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		Collection<ElementsWaitingForEvent> waitForEventElements = getWaitForEventElements(umlStateMachine);
		// TODO fire default transition after doActivity if it is a simple state
		for(ElementsWaitingForEvent wfe:waitForEventElements){
			if( wfe.getEvent() instanceof TimeEvent && EmfEventUtil.isDeadline((Event) wfe.getEvent())){
				// fired and cancelled from task
			}else if(wfe.getEvent() instanceof TimeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					StateMap map = ojUtil.buildStateMap((State) fromNode.getWaitingElement());
					OJOperation fire = javaStateMachine.getUniqueOperation(map.getOnEntryMethod());
					eventUtil.implementTimeEventRequest(fire, fire.getBody(), (TimeEvent) wfe.getEvent(), getLibrary().getBusinessRole() != null);
					OJOperation cancel = javaStateMachine.getUniqueOperation(map.getOnExitMethod());
					cancel.addParam("context", Jbpm5Util.getProcessContext());
					EventUtil.cancelTimer(cancel.getBody(), (TimeEvent) wfe.getEvent(), "this");
				}
			}else if(wfe.getEvent() instanceof ChangeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					StateMap map = ojUtil.buildStateMap((State) fromNode.getWaitingElement());
					OJOperation fire = javaStateMachine.getUniqueOperation(map.getOnEntryMethod());
					eventUtil.implementChangeEventRequest(fire, (ChangeEvent) wfe.getEvent());
					OJOperation cancel = javaStateMachine.getUniqueOperation(map.getOnExitMethod());
					cancel.addParam("context", Jbpm5Util.getProcessContext());
					EventUtil.cancelChangeEvent(cancel.getBody(), (ChangeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventConsumption(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}
	private Collection<ElementsWaitingForEvent> getWaitForEventElements(StateMachine ns){
		Map<Element,ElementsWaitingForEvent> results = new HashMap<Element,ElementsWaitingForEvent>();
		for(Transition transition:EmfStateMachineUtil.getTransitions( ns)){
			Collection<Trigger> triggers = transition.getTriggers();
			if(triggers.isEmpty()){
				addEvent(results, transition, transition.getSource());
			}else{
				for(Trigger trigger:triggers){
					Event event = trigger.getEvent();
					addEvent(results, transition, event);
				}
			}
		}
		return results.values();
	}
	private void addEvent(Map<Element,ElementsWaitingForEvent> results,Transition transition,NamedElement event){
		Vertex state = transition.getSource();
		ElementsWaitingForEvent eventActions = results.get(event);
		if(eventActions == null){
			eventActions = new ElementsWaitingForEvent(event);
			results.put(event, eventActions);
		}
		eventActions.addWaitingNode(state, transition, state instanceof State);
	}
	@Override
	protected void consumeEvent(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound){
		OJIfStatement ifGuard = null;
		Classifier booleanType = getLibrary().getBooleanType();
		for(Transition t:EmfStateMachineUtil.getConditionalTransitions( (Vertex) node.getWaitingElement())){
			OJIfStatement newIf = new OJIfStatement();
			if(EmfStateMachineUtil.hasGuard(t)){
				AbstractOclContext value=getLibrary().getOclExpressionContext((OpaqueExpression) t.getGuard().getSpecification());
				newIf.setCondition(valueSpecificationUtil.expressOcl(value, operationContext, booleanType));
			}
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
		Transition flow = (Transition) node.getDefaultTransition();
		if(flow != null){
			// default flow/transition
			block.addToStatements("processDirty=consumed=true");
			maybeContinueFlow(operationContext, block, flow);
		}
	}
	private void maybeContinueFlow(OJOperation operationContext,OJBlock block,Transition flow){
		operationContext.getOwner().addToImports(UML_NODE_INSTANCE);
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(TransitionListener.class));
		OJAnnonymousInnerClass listener = new OJAnnonymousInnerClass(operationContext.getOwner().getPathName(), "listener",
				ReflectionUtil.getUtilInterface(TransitionListener.class));
		block.addToLocals(listener);
		OJAnnotatedOperation onTransition = new OJAnnotatedOperation("onTransition");
		listener.getClassDeclaration().addToOperations(onTransition);
		if(flow.getEffect() instanceof Activity){
			for(OJParameter p:operationContext.getParameters()){
				p.setFinal(true);
			}
			SimpleActivityMethodImplementor ai = new SimpleActivityMethodImplementor();
			ai.initialize(javaModel, config, textWorkspace, workspace, ojUtil);
			ai.setWorkspace(workspace);
			ai.implementActivityOn((Activity) flow.getEffect(), onTransition);
			operationContext.getOwner().addToImports(listener.getClassDeclaration().getImports());
		}else if(flow.getEffect() instanceof OpaqueBehavior){
			OpaqueBehavior b = (OpaqueBehavior) flow.getEffect();
			OclBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext(b);
			if(!oclBehaviorContext.hasErrors()){
				Classifier voidType = getLibrary().getOclLibrary().getOclVoid();
				String expression = valueSpecificationUtil.expressOcl(oclBehaviorContext, onTransition, voidType);
				onTransition.getBody().addToStatements(expression);
			}
		}
		block.addToStatements("waitingNode.transitionToNode(" + operationContext.getOwner().getName() + "State."
				+ Jbpm5Util.stepLiteralName(flow.getTarget()) + ".getId(), listener)");
	}
	@Override
	protected void implementEventConsumerBody(ElementsWaitingForEvent eventActions,OJAnnotatedOperation listener,OJIfStatement ifProcessActive){
		if(eventActions.getEvent() instanceof MessageEvent || eventActions.getEvent() instanceof State){
			// Message event or Complecion Event
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
