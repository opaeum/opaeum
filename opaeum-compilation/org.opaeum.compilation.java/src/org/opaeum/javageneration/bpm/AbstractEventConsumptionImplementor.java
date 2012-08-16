package org.opaeum.javageneration.bpm;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SignalEvent;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.maps.SignalMap;

public abstract class AbstractEventConsumptionImplementor extends AbstractJavaProcessVisitor{
	protected void implementEventConsumption(OJAnnotatedClass ojBehavior,Behavior behavior,Collection<ElementsWaitingForEvent> ea){
		// FIrst implement the event consumers on the process class itself
		for(ElementsWaitingForEvent eventActions:ea){
			// Only one method will be generated per class
			implementEventConsumer(behavior, ojBehavior, eventActions);
		}
		BehavioredClassifier context = behavior.getContext();
		if(context != null){
			activateEventGenerationInContextAndDelegateConsumptionToOwnedBehavior(behavior, ea, context);
		}
		activitateEventGenerationInBehavior(ojBehavior, behavior, ea);
	}
	protected abstract void implementEventConsumer(Behavior behavior,OJAnnotatedClass ojBehavior,ElementsWaitingForEvent eventActions);
	private void activitateEventGenerationInBehavior(OJAnnotatedClass ojBehavior,Behavior behavior,Collection<ElementsWaitingForEvent> ea){
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof SignalEvent){
				SignalEvent signalEvent = (SignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = ojUtil.buildSignalMap(signalEvent.getSignal());
				if(EmfEventUtil.behaviorHasReceptionOrTriggerFor(behavior, signalEvent.getSignal())){
					activiateSignalEventGeneration(behavior, ojBehavior, map);
				}
			}else if(elementsWaitingForEvent.getEvent() instanceof CallEvent){
				CallEvent ce = (CallEvent) elementsWaitingForEvent.getEvent();
				Operation operation = ce.getOperation();
				if(behavior.conformsTo((Classifier) operation.getOwner())){
					activateCallEventGeneration(behavior, ojBehavior, operation);
				}
			}
		}
	}
	private void activateEventGenerationInContextAndDelegateConsumptionToOwnedBehavior(Behavior behavior,
			Collection<ElementsWaitingForEvent> ea,BehavioredClassifier context){
		OJAnnotatedClass ojContext = findJavaClass(context);
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof SignalEvent){
				SignalEvent signalEvent = (SignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = ojUtil.buildSignalMap(signalEvent.getSignal());
				if(EmfEventUtil.hasReceptionOrTriggerFor(context, signalEvent.getSignal())){
					// Could originate from the context
					// delegate to this owned behavior
					delegateMessageEventConsumtionFromContextToOwnedBehavior(behavior, context, ojContext, map);
					activiateSignalEventGeneration(context, ojContext, map);
				}
			}else if(elementsWaitingForEvent.getEvent() instanceof CallEvent){
				CallEvent ce = (CallEvent) elementsWaitingForEvent.getEvent();
				Operation operation = ce.getOperation();
				if(context.conformsTo((Classifier) operation.getOwner())){
					delegateMessageEventConsumtionFromContextToOwnedBehavior(behavior, context, ojContext, ojUtil.buildOperationMap(operation));
					activateCallEventGeneration(context, ojContext, operation);
				}
			}
		}
	}
	private void activiateSignalEventGeneration(BehavioredClassifier context,OJAnnotatedClass ojContext,SignalMap map){
		OJAnnotatedOperation eventGenerator = OperationAnnotator.findOrCreateEventGenerator(context, ojContext, map);
		if(eventGenerator.getBody().getStatements().isEmpty()){
			ojContext.addToImports(map.eventHandlerPath());
			eventGenerator.getBody().addToStatements(
					"this.getOutgoingEvents().add(new OutgoingEvent(this, new " + map.eventHandlerPath().getLast() + "(signal,true)))");
		}
	}
	private void activateCallEventGeneration(BehavioredClassifier context,OJAnnotatedClass ojContext,Operation operation){
		OperationMap map = ojUtil.buildOperationMap(operation);
		OJAnnotatedOperation eventGenerator = operationAnnotator.findOrCreateEventGenerator(context, ojContext, map);
		if(eventGenerator.getBody().getStatements().isEmpty()){
			ojContext.addToImports(map.eventHandlerPath());
			StringBuilder sb = new StringBuilder("this.getOutgoingEvents().add(new OutgoingEvent(this, new " + map.eventHandlerPath().getLast()
					+ "(");
			String args = OperationAnnotator.delegateParameters(eventGenerator);
			if(args.length() > 0){
				args = args + ",";
			}
			sb.append(args);
			sb.append("true)))");
			eventGenerator.getBody().addToStatements(sb.toString());
		}
	}
	private void delegateMessageEventConsumtionFromContextToOwnedBehavior(Behavior behavior,BehavioredClassifier context,
			OJAnnotatedClass ojContext,IMessageMap map1){
		OJOperation consumer = operationAnnotator.findOrCreateEventConsumer(context, ojContext, map1);
		OJStatement statement;
		PropertyMap mapToBehavior = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite(behavior).getOtherEnd());
		if(EmfBehaviorUtil.isClassifierBehavior(behavior)){
			statement = new OJSimpleStatement(mapToBehavior.getter() +"()."+ callToEventConsumer(map1, consumer));
		}else{
			OJIfStatement ifNotConsumed = new OJIfStatement("!result");
			OJForStatement forEach = new OJForStatement("behavior", mapToBehavior.javaBaseTypePath(), mapToBehavior.getter() + "()");
			ifNotConsumed.getThenPart().addToStatements(forEach);
			// TODO resolve correllation properties here
			forEach.getBody().addToStatements("result=behavior." + callToEventConsumer(map1, consumer));
			OJIfStatement ifConsumedNow = new OJIfStatement("result");
			forEach.getBody().addToStatements(ifConsumedNow);
			ifConsumedNow.getThenPart().addToStatements("break");
			statement = ifNotConsumed;
		}
		consumer.getBody().addToStatements(statement);
	}
	private String callToEventConsumer(IMessageMap map,OJOperation ojOperation){
		StringBuilder statement = new StringBuilder(map.eventConsumerMethodName());
		statement.append("(");
		statement.append(OperationAnnotator.delegateParameters(ojOperation));
		statement.append(")");
		return statement.toString();
	}

}
