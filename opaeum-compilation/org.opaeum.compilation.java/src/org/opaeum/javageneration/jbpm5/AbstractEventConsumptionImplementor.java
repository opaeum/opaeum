package org.opaeum.javageneration.jbpm5;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class AbstractEventConsumptionImplementor extends StereotypeAnnotator{
	protected static final OJPathName NODE_INSTANCE_CONTAINER = new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer");
	protected static final OJPathName NODE_CONTAINER = new OJPathName("org.jbpm.workflow.core.NodeContainer");
	protected static final OJPathName NODE = new OJPathName("org.jbpm.workflow.core.Node");
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.opaeum.runtime.domain.UmlNodeInstance");
	protected abstract void consumeEvent(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound);
	abstract protected void implementEventConsumerBody(ElementsWaitingForEvent eventActions,OJAnnotatedOperation listener,OJIfStatement ifProcessActive);
	protected OperationAnnotator operationAnnotator=new OperationAnnotator();
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		operationAnnotator.initialize(pac, config, textWorkspace, workspace, ojUtil);
	}
	protected void implementEventConsumption(OJAnnotatedClass ojBehavior,Behavior behavior,Collection<ElementsWaitingForEvent> ea){
		addFindWaitingNode(ojBehavior);
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
	private void activitateEventGenerationInBehavior(OJAnnotatedClass ojBehavior,Behavior behavior,Collection<ElementsWaitingForEvent> ea){
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof SignalEvent){
				SignalEvent signalEvent = (SignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = ojUtil.buildSignalMap(signalEvent.getSignal());
				if(EmfEventUtil.behaviorHasReceptionOrTriggerFor( behavior, signalEvent.getSignal())){
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
	private void activateEventGenerationInContextAndDelegateConsumptionToOwnedBehavior(Behavior behavior,Collection<ElementsWaitingForEvent> ea,
			BehavioredClassifier context){
		OJAnnotatedClass ojContext = findJavaClass(context);
		for(ElementsWaitingForEvent elementsWaitingForEvent:ea){
			if(elementsWaitingForEvent.getEvent() instanceof SignalEvent){
				SignalEvent signalEvent = (SignalEvent) elementsWaitingForEvent.getEvent();
				SignalMap map = ojUtil.buildSignalMap(signalEvent.getSignal());
				if(EmfEventUtil.hasReceptionOrTriggerFor(context,signalEvent.getSignal())){
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
			eventGenerator.getBody().addToStatements("this.getOutgoingEvents().add(new OutgoingEvent(this, new " + map.eventHandlerPath().getLast() + "(signal,true)))");
		}
	}
	private void activateCallEventGeneration(BehavioredClassifier context,OJAnnotatedClass ojContext,Operation operation){
		OperationMap map = ojUtil.buildOperationMap(operation);
		OJAnnotatedOperation eventGenerator = operationAnnotator.findOrCreateEventGenerator(context, ojContext, map);
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
	private void delegateMessageEventConsumtionFromContextToOwnedBehavior(Behavior behavior,BehavioredClassifier context,OJAnnotatedClass ojContext,
			IMessageMap map1){
		OJOperation consumer = operationAnnotator.findOrCreateEventConsumer(context, ojContext, map1);
		OJStatement statement;
		StructuralFeatureMap mapToBehavior = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite( behavior).getOtherEnd());
		if(EmfBehaviorUtil.isClassifierBehavior( behavior)){
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
	private void implementEventConsumer(Behavior behavior,OJAnnotatedClass activityClass,ElementsWaitingForEvent eventActions){
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
		NamedElement waitingElement = node.getWaitingElement();
		OJIfStatement ifTokenFound = addIfTokenFound(listener, ifProcessActive, waitingElement);
		consumeEvent(listener, node, ifTokenFound);
	}
	protected OJIfStatement addIfTokenFound(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,NamedElement waitingElement){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		Element ownerElement = (Element) EmfElementFinder.getContainer(waitingElement);
		while(!(ownerElement instanceof Classifier)){
			//State could be inherited
			ownerElement=(Element) EmfElementFinder.getContainer(((Element) ownerElement));
		}
		String literalExpression = ojUtil.classifierPathname((Classifier) ownerElement) + "State." + Jbpm5Util.stepLiteralName(waitingElement);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(" + literalExpression + ".getId()))" + "!=null");
		return ifTokenFound;
	}
	protected void consumeEventWithSourceNodeInstanceUniqueId(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,FromNode node){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
		consumeEvent(listener, node, ifTokenFound);
	}
	private OJAnnotatedOperation createEventConsumerSignature(Behavior behavior,OJAnnotatedClass activityClass,
			ElementsWaitingForEvent elementsWaitingForEvent){
		NamedElement event = elementsWaitingForEvent.getEvent();
		if(event instanceof MessageEvent){
			IMessageMap map1;
			if(event instanceof CallEvent){
				map1 = ojUtil.buildOperationMap(((CallEvent) event).getOperation());
			}else{
				map1 = ojUtil.buildSignalMap(((SignalEvent) event).getSignal());
			}
			OJAnnotatedOperation listener = operationAnnotator.findOrCreateEventConsumer(behavior, activityClass, map1);
			OJUtil.removeReturnStatement(listener);
			return listener;
		}else{
			// TODO if the behavior's superBehavior reacts to this event to, initialise consumed:boolean to super.consumeEventxyz
			String methodName = eventUtil.getEventConsumerName(event);
			OJAnnotatedOperation listener = (OJAnnotatedOperation) activityClass.getUniqueOperation(methodName);
			if(listener == null){
				listener = new OJAnnotatedOperation(methodName);
				listener.setReturnType(new OJPathName("boolean"));
				activityClass.addToOperations(listener);
				OJAnnotatedField processed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
				processed.setInitExp("false");
				listener.getBody().addToLocals(processed);
				if(event instanceof TimeEvent && EmfTimeUtil.isDeadline((Event) event)){
					Element origin = EmfEventUtil.getDeadlineOrigin((TimeEvent) event);
					OJPathName pn = null;
					if(origin instanceof Operation){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure( ((Operation) origin)));
					}else if(origin instanceof OpaqueAction &&  EmfActionUtil.isSingleScreenTask((ActivityNode) origin)){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure(((OpaqueAction) origin)));
					}else{
						pn = ojUtil.classifierPathname((CallBehaviorAction) origin);
					}
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType(),(CollectionKind)null).javaTypePath());
					listener.addParam("source", pn);
				}else if(event instanceof ChangeEvent){
					listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
				}else if(event instanceof TimeEvent){
					listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType(),(CollectionKind)null).javaTypePath());
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
