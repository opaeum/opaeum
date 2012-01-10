package org.opaeum.javageneration.jbpm5;

import java.util.Collection;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.bpm.INakedDefinedResponsibility;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedOperation;

public abstract class AbstractEventConsumptionImplementor extends StereotypeAnnotator{
	protected static final OJPathName NODE_INSTANCE_CONTAINER = new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer");
	protected static final OJPathName NODE_CONTAINER = new OJPathName("org.jbpm.workflow.core.NodeContainer");
	protected static final OJPathName NODE = new OJPathName("org.jbpm.workflow.core.Node");
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.opaeum.runtime.domain.UmlNodeInstance");
	protected abstract void consumeEvent(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound);
	abstract protected void implementEventConsumerBody(ElementsWaitingForEvent eventActions,OJAnnotatedOperation listener,OJIfStatement ifProcessActive);
	protected void implementEventConsumption(OJAnnotatedClass ojBehavior,INakedTriggerContainer behavior,Collection<ElementsWaitingForEvent> ea){
		addFindWaitingNode(ojBehavior);
		// FIrst implement the event consumers on the process class itself
		for(ElementsWaitingForEvent eventActions:ea){
			// Only one method will be generated per class
			implementEventConsumer(behavior, ojBehavior, eventActions);
		}
		INakedBehavioredClassifier context = behavior.getContext();
		if(context != null){
			activateEventGenerationInContextAndDelegateConsumptionToOwnedBehavior(behavior, ea, context);
		}
		activitateEventGenerationInBehavior(ojBehavior, behavior, ea);
	}
	private void activitateEventGenerationInBehavior(OJAnnotatedClass ojBehavior,INakedTriggerContainer behavior,Collection<ElementsWaitingForEvent> ea){
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof INakedSignalEvent){
				INakedSignalEvent signalEvent = (INakedSignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = OJUtil.buildSignalMap(signalEvent.getSignal());
				if(behavior.hasReceptionOrTriggerFor(signalEvent.getSignal())){
					activiateSignalEventGeneration(behavior, ojBehavior, map);
				}
			}else if(elementsWaitingForEvent.getEvent() instanceof INakedCallEvent){
				INakedCallEvent ce = (INakedCallEvent) elementsWaitingForEvent.getEvent();
				INakedOperation operation = ce.getOperation();
				if(behavior.conformsTo(operation.getOwner())){
					activateCallEventGeneration(behavior, ojBehavior, operation);
				}
			}
		}
	}
	private void activateEventGenerationInContextAndDelegateConsumptionToOwnedBehavior(INakedTriggerContainer behavior,Collection<ElementsWaitingForEvent> ea,
			INakedBehavioredClassifier context){
		OJAnnotatedClass ojContext = findJavaClass(context);
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof INakedSignalEvent){
				INakedSignalEvent signalEvent = (INakedSignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = OJUtil.buildSignalMap(signalEvent.getSignal());
				if(context.hasReceptionOrTriggerFor(signalEvent.getSignal())){
					// Could originate from the context
					// delegate to this owned behavior
					delegateMessageEventConsumtionFromContextToOwnedBehavior(behavior, context, ojContext, map);
					activiateSignalEventGeneration(context, ojContext, map);
				}
			}else if(elementsWaitingForEvent.getEvent() instanceof INakedCallEvent){
				INakedCallEvent ce = (INakedCallEvent) elementsWaitingForEvent.getEvent();
				INakedOperation operation = ce.getOperation();
				if(context.conformsTo(operation.getOwner())){
					delegateMessageEventConsumtionFromContextToOwnedBehavior(behavior, context, ojContext, OJUtil.buildOperationMap(operation));
					activateCallEventGeneration(context, ojContext, operation);
				}
			}
		}
	}
	private void activiateSignalEventGeneration(INakedBehavioredClassifier context,OJAnnotatedClass ojContext,SignalMap map){
		OJAnnotatedOperation eventGenerator = OperationAnnotator.findOrCreateEventGenerator(context, ojContext, map);
		if(eventGenerator.getBody().getStatements().isEmpty()){
			ojContext.addToImports(map.eventHandlerPath());
			eventGenerator.getBody().addToStatements("this.getOutgoingEvents().add(new OutgoingEvent(this, new " + map.eventHandlerPath().getLast() + "(signal,true)))");
		}
	}
	private void activateCallEventGeneration(INakedBehavioredClassifier context,OJAnnotatedClass ojContext,INakedOperation operation){
		NakedOperationMap map = OJUtil.buildOperationMap(operation);
		OJAnnotatedOperation eventGenerator = OperationAnnotator.findOrCreateEventGenerator(context, ojContext, map);
		if(eventGenerator.getBody().getStatements().isEmpty()){
			ojContext.addToImports(map.eventHandlerPath());
			StringBuilder sb = new StringBuilder("this.getOutgoingEvents().add(new OutgoingEvent(this, new " + map.eventHandlerPath().getLast() + "(");
			String args = OperationAnnotator.delegateParameters(eventGenerator);
			if(args.length() > 0){
				args = args + ",";
			}
			sb.append(args);
			sb.append("true)))");
			eventGenerator.getBody().addToStatements(sb.toString());
		}
	}
	private void delegateMessageEventConsumtionFromContextToOwnedBehavior(INakedTriggerContainer behavior,INakedBehavioredClassifier context,OJAnnotatedClass ojContext,
			IMessageMap map1){
		OJOperation consumer = OperationAnnotator.findOrCreateEventConsumer(context, ojContext, map1);
		OJStatement statement;
		NakedStructuralFeatureMap mapToBehavior = OJUtil.buildStructuralFeatureMap(behavior.getEndToComposite().getOtherEnd());
		if(behavior.isClassifierBehavior()){
			statement = new OJSimpleStatement("getClassifierBehavior()." + callToEventConsumer(map1, consumer));
		}else{
			OJIfStatement ifNotConsumed = new OJIfStatement("!consumed");
			OJForStatement forEach = new OJForStatement("behavior", mapToBehavior.javaBaseTypePath(), mapToBehavior.getter() + "()");
			ifNotConsumed.getThenPart().addToStatements(forEach);
			// TODO resolve correllation properties here
			forEach.getBody().addToStatements("consumed=behavior." + callToEventConsumer(map1, consumer));
			OJIfStatement ifConsumedNow = new OJIfStatement("consumed");
			forEach.getBody().addToStatements(ifConsumedNow);
			ifConsumedNow.getThenPart().addToStatements("break");
			statement = ifNotConsumed;
		}
		consumer.getBody().addToStatements(consumer.getBody().getStatements().size() - 1, statement);
	}
	private String callToEventConsumer(IMessageMap map,OJOperation ojOperation){
		StringBuilder statement = new StringBuilder(map.eventConsumerMethodName());
		statement.append("(");
		statement.append(OperationAnnotator.delegateParameters(ojOperation));
		statement.append(")");
		return statement.toString();
	}
	/**
	 * Implements a method that is to be called when significant events occur. By convention it starts with the word "on" and returns a
	 * boolean indicating whether the event was consumed or not
	 */
	private void implementEventConsumer(INakedTriggerContainer behavior,OJAnnotatedClass activityClass,ElementsWaitingForEvent eventActions){
		OJAnnotatedOperation consumer = createEventConsumerSignature(behavior, activityClass, eventActions);
		OJIfStatement ifProcessActive = addIfProcessActiveAndRemoveReturnStatementIfNecessary(activityClass, consumer);
		implementEventConsumerBody(eventActions, consumer, ifProcessActive);
		consumer.getBody().addToStatements("return consumed");
	}
	protected OJIfStatement addIfProcessActiveAndRemoveReturnStatementIfNecessary(OJAnnotatedClass activityClass,OJAnnotatedOperation consumer){
		OJIfStatement ifProcessActive = (OJIfStatement) consumer.getBody().findStatementRecursive("ifProcessActive");
		if(ifProcessActive == null){
			ifProcessActive = addIfProcessActive(activityClass, consumer);
		}else{
			OJUtil.removeReturnStatement(consumer);
		}
		return ifProcessActive;
	}
	private OJIfStatement addIfProcessActive(OJAnnotatedClass activityClass,OJAnnotatedOperation consumer){
		OJIfStatement ifProcessActive;
		ifProcessActive = new OJIfStatement("getProcessInstance()!=null");
		ifProcessActive.setName("ifProcessActive");
		consumer.getBody().addToStatements(ifProcessActive);
		OJAnnotatedField nodes = new OJAnnotatedField("waitingNode", UML_NODE_INSTANCE);
		activityClass.addToImports(UML_NODE_INSTANCE);
		ifProcessActive.getThenPart().addToLocals(nodes);
		return ifProcessActive;
	}
	protected void consumeEventWithoutSourceNodeInstanceUniqueId(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,FromNode node){
		INakedStep waitingElement = node.getWaitingElement();
		OJIfStatement ifTokenFound = addIfTokenFound(listener, ifProcessActive, waitingElement);
		consumeEvent(listener, node, ifTokenFound);
	}
	protected OJIfStatement addIfTokenFound(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,INakedStep waitingElement){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		INakedElementOwner ownerElement = waitingElement.getOwnerElement();
		while(!(ownerElement instanceof INakedClassifier)){
			//State could be inherited
			ownerElement=((INakedElement) ownerElement).getOwnerElement();
		}
		String literalExpression = OJUtil.classifierPathname((INakedClassifier) ownerElement) + "State." + Jbpm5Util.stepLiteralName(waitingElement);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(" + literalExpression + ".getId()))" + "!=null");
		return ifTokenFound;
	}
	protected void consumeEventWithSourceNodeInstanceUniqueId(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,FromNode node){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
		consumeEvent(listener, node, ifTokenFound);
	}
	private OJAnnotatedOperation createEventConsumerSignature(INakedTriggerContainer behavior,OJAnnotatedClass activityClass,
			ElementsWaitingForEvent elementsWaitingForEvent){
		INakedEvent event = elementsWaitingForEvent.getEvent();
		if(event instanceof INakedMessageEvent){
			IMessageMap map1;
			if(event instanceof INakedCallEvent){
				map1 = OJUtil.buildOperationMap(((INakedCallEvent) event).getOperation());
			}else{
				map1 = OJUtil.buildSignalMap(((INakedSignalEvent) event).getSignal());
			}
			OJAnnotatedOperation listener = OperationAnnotator.findOrCreateEventConsumer(behavior, activityClass, map1);
			OJUtil.removeReturnStatement(listener);
			return listener;
		}else{
			// TODO if the behavior's superBehavior reacts to this event to, initialise consumed:boolean to super.consumeEventxyz
			String methodName = EventUtil.getEventConsumerName(event);
			OJAnnotatedOperation listener = (OJAnnotatedOperation) activityClass.getUniqueOperation(methodName);
			if(listener == null){
				listener = new OJAnnotatedOperation(methodName);
				listener.setReturnType(new OJPathName("boolean"));
				activityClass.addToOperations(listener);
				OJAnnotatedField processed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
				processed.setInitExp("false");
				listener.getBody().addToLocals(processed);
				if(event instanceof INakedDeadline){
					INakedDefinedResponsibility origin = ((INakedDeadline) event).getOrigin();
					OJPathName pn = null;
					if(origin instanceof INakedOperation){
						pn = OJUtil.classifierPathname(((INakedOperation) origin).getMessageStructure());
					}else if(origin instanceof INakedEmbeddedSingleScreenTask){
						pn = OJUtil.classifierPathname(((INakedEmbeddedSingleScreenTask) origin).getMessageStructure());
					}else{
						pn = OJUtil.classifierPathname((INakedEmbeddedScreenFlowTask) origin);
					}
					listener.addParam("triggerDate", OJUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType()).javaTypePath());
					listener.addParam("source", pn);
				}else if(event instanceof INakedChangeEvent){
					listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
				}else if(event instanceof INakedTimeEvent){
					listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
					listener.addParam("triggerDate", OJUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType()).javaTypePath());
				}
			}
			return listener;
		}
	}
	private void addFindWaitingNode(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		OJOperation findWaitingNodeByNodeId = new OJAnnotatedOperation("findWaitingNodeByNodeId");
		activityClass.addToOperations(findWaitingNodeByNodeId);
		findWaitingNodeByNodeId.addParam("step", new OJPathName("long"));
		findWaitingNodeByNodeId.setReturnType(Jbpm5Util.getNodeInstance());
		OJForStatement forNodeInstances = new OJForStatement();
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(Jbpm5Util.getNodeInstance());
		forNodeInstances.setElemName("nodeInstance");
		activityClass.addToImports("java.util.Collection");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		findWaitingNodeByNodeId.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("((" + Jbpm5Util.getNode().getLast() + ")nodeInstance.getNode()).getId()==step", "return nodeInstance");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		findWaitingNodeByNodeId.getBody().addToStatements("return null");
	}
}
