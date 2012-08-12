package org.opaeum.javageneration.bpm.statemachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.bpm.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.bpm.ElementsWaitingForEvent;

@StepDependency(phase = JavaTransformationPhase.class,requires = StateMachineImplementor.class,after = StateMachineImplementor.class)
public class StateMachineEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine umlStateMachine){
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		super.implementEventConsumption(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}
	private Collection<ElementsWaitingForEvent> getWaitForEventElements(StateMachine ns){
		Map<Element,ElementsWaitingForEvent> results = new HashMap<Element,ElementsWaitingForEvent>();
		for(Transition transition:EmfStateMachineUtil.getTransitions(ns)){
			Collection<Trigger> triggers = transition.getTriggers();
			for(Trigger trigger:triggers){
				Event event = trigger.getEvent();
				addEvent(results, transition, event);
			}
		}
		return results.values();
	}
	private void addEvent(Map<Element,ElementsWaitingForEvent> results,Transition transition,Event event){
		Vertex state = transition.getSource();
		ElementsWaitingForEvent eventActions = results.get(event);
		if(eventActions == null){
			eventActions = new ElementsWaitingForEvent(event);
			results.put(event, eventActions);
		}
		eventActions.addWaitingNode(state);
	}
	protected void implementEventConsumer(Behavior behavior,OJAnnotatedClass ojBehavior,ElementsWaitingForEvent eventActions){
		OJAnnotatedOperation eventConsumer = super.createEventConsumerSignature(behavior, ojBehavior, eventActions.getEvent());
		OJForStatement forEachToken = new OJForStatement("token", StateMachineImplementor.STATE_MACHINE_TOKEN, "getTokens()");
		eventConsumer.getBody().addToStatements(forEachToken);
		for(NamedElement waitingNode:eventActions.getWaitingNodes()){
			OJPathName waitingClass = ojUtil.classifierPathname(waitingNode);
			ojBehavior.addToImports(waitingClass);
			String condition = "result==false && token.isActive() && token.getCurrentExecutionElement() instanceof " + waitingClass.getLast();
			OJIfStatement ifMatchFound = new OJIfStatement(condition);
			forEachToken.getBody().addToStatements(ifMatchFound);
			OJAnnotatedField stateActivation = new OJAnnotatedField("state", waitingClass);
			stateActivation.setInitExp("(" + waitingClass.getLast() + ")token.getCurrentExecutionElement()");
			State state = (State) waitingNode;
			ifMatchFound.getThenPart().addToLocals(stateActivation);
			for(Transition transition:state.getOutgoings()){
				for(Trigger trigger:transition.getTriggers()){
					if(trigger.getEvent() == eventActions.getEvent()){
						OJPathName tpn = ojUtil.classifierPathname(transition);
						OJIfStatement ifAccept = new OJIfStatement("result==false &&  state.get" + tpn.getLast() + "()."  + eventConsumer.getName() + "("
								+ OperationAnnotator.delegateParameters(eventConsumer) + ")");
						ifMatchFound.getThenPart().addToStatements(ifAccept);
						ifAccept.getThenPart().addToStatements("result=true");
						ifAccept.getThenPart().addToStatements("break");
						break;
					}
				}
			}
		}
	}
}
