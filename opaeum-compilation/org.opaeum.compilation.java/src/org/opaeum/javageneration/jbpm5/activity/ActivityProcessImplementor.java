package org.opaeum.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.CallBehaviorActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.CallOperationActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.EmbeddedScreenFlowTaskBuilder;
import org.opaeum.javageneration.jbpm5.actions.EmbeddedSingleScreenTaskBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.actions.ParameterNodeBuilder;
import org.opaeum.javageneration.jbpm5.actions.ReplyActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.SimpleActionBridge;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.linkage.PinLinker;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedElement;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,PinLinker.class,ProcessIdentifier.class,CompositionEmulator.class,NakedParsedOclStringResolver.class,CodeCleanup.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class ActivityProcessImplementor extends AbstractJavaProcessVisitor{
	private void activityEdge(INakedActivityEdge edge){
		if(edge.hasGuard() && BehaviorUtil.hasExecutionInstance(edge.getActivity())){
			INakedActivityNode node = edge.getEffectiveSource();
			OJAnnotatedClass c = findJavaClass(edge.getActivity());
			OJAnnotatedOperation oper = new OJAnnotatedOperation(Jbpm5Util.getGuardMethod(edge));
			c.addToOperations(oper);
			oper.setReturnType(new OJPathName("boolean"));
			ActivityUtil.setupVariables(oper, node);
			INakedActivityNode source = edge.getEffectiveSource();
			if(edge instanceof INakedObjectFlow){
				addObjectFlowVariable(edge, oper, (INakedObjectFlow) edge);
			}else if((source instanceof INakedControlNode && ((INakedControlNode) source).getControlNodeType() == ControlNodeType.DECISION_NODE)){
				// NB!! we are doing it here for both controlflows and
				// objectflows which is not entirely to uml spec but what the
				// heck,
				// looks like a good idea
				if(source.getIncoming().size() == 1 && source.getIncoming().iterator().next() instanceof INakedObjectFlow){
					INakedObjectFlow objectFlow = (INakedObjectFlow) source.getIncoming().iterator().next();
					addObjectFlowVariable(edge, oper, objectFlow);
				}
			}
			IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			oper.getBody().addToStatements("return " + ValueSpecificationUtil.expressValue(oper, edge.getGuard(), node.getActivity(), booleanType));
			oper.addParam("context", Jbpm5Util.getProcessContext());
		}
	}
	private void addObjectFlowVariable(INakedActivityEdge edge,OJAnnotatedOperation oper,INakedObjectFlow objectFlow){
		INakedObjectNode origin = objectFlow.getOriginatingObjectNode();
		// TODO the originatingObjectNode may not have the correct type after
		// transformations and selections
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), origin, false);
		OJAnnotatedField sourceField = new OJAnnotatedField(map.umlName(), map.javaTypePath());
		oper.getBody().addToLocals(sourceField);
		AbstractObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getLibrary());
		sourceField.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(oper.getBody(), objectFlow));
	}
	@VisitBefore(matchSubclasses = true)
	public void implementActivity(INakedActivity activity){
		ensureEventHandlerImplementation(activity);
		if(activity.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			OJPathName stateClass = OJUtil.packagePathname(activity.getNameSpace());
			stateClass.addToNames(activity.getMappingInfo().getJavaName() + "State");
			implementNodeMethods(activity);
			doExecute(activity, activityClass);
			if(activity.getActivityKind() == ActivityKind.PROCESS){
				implementProcessInterfaceOperations(activityClass, stateClass, activity);
				OJOperation init = activityClass.findOperation("init", Arrays.asList(Jbpm5Util.getProcessContext()));
				EventUtil.requestEvents((OJAnnotatedOperation) init, activity.getActivityNodes(), getLibrary().getBusinessRole() != null);
			}else{
				Jbpm5Util.implementRelationshipWithProcess(activityClass, false, "process");
				doIsStepActive(activityClass, activity);
				super.addGetNodeInstancesRecursively(activityClass);
			}
		}
	}
	private void ensureEventHandlerImplementation(INakedActivity activity){
		List<INakedActivityNode> activityNodesRecursively = activity.getActivityNodesRecursively();
		for(INakedActivityNode node:activityNodesRecursively){
			if(node instanceof INakedSendSignalAction){
				// TODO this deviates from the UML spec. implement validation to ensure the reception is defined on the target
				INakedSendSignalAction ssa = (INakedSendSignalAction) node;
				SignalMap map = new SignalMap(ssa.getSignal());
				if(ssa.getTargetElement() != null && ssa.getTargetElement().getNakedBaseType() != null){
					OJAnnotatedClass ojTarget = findJavaClass(ssa.getTargetElement().getNakedBaseType());
					if(ojTarget != null){
						if(!ojTarget.getImplementedInterfaces().contains(map.receiverContractTypePath())){
							ojTarget.addToImplementedInterfaces(map.receiverContractTypePath());
							OperationAnnotator.findOrCreateJavaReception(ojTarget, map);
							OperationAnnotator.findOrCreateEventGenerator((INakedBehavioredClassifier) ssa.getTargetElement().getNakedBaseType(), ojTarget, map);
							OperationAnnotator.findOrCreateEventConsumer((INakedBehavioredClassifier) ssa.getTargetElement().getNakedBaseType(), ojTarget, map);
						}
					}
				}
			}
		}
	}
	private void doExecute(INakedActivity activity,OJAnnotatedClass activityClass){
		OJOperation execute = implementExecute(activityClass, activity);
		if(activity.isProcess()){
			execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		}
	}
	private void implementNodeMethods(ActivityNodeContainer activity){
		OJAnnotatedClass activityClass;
		if(activity instanceof INakedStructuredActivityNode){
			activityClass = findJavaClass(((INakedStructuredActivityNode) activity).getMessageStructure());
		}else{
			activityClass = findJavaClass((INakedActivity) activity);
		}
		activityClass.addToImports(Jbpm5Util.getProcessContext());
		for(INakedActivityNode node:activity.getActivityNodes()){
			if(node instanceof INakedAction || node instanceof INakedParameterNode || node instanceof INakedControlNode || node instanceof INakedExpansionRegion
					|| node instanceof INakedExpansionNode){
				this.implementNodeMethod(activityClass, node);
				if(node instanceof ActivityNodeContainer){
					visitEdges(((ActivityNodeContainer) node).getActivityEdges());
				}
			}else if(node instanceof INakedOutputPin){
				if(BehaviorUtil.hasMessageStructure((INakedAction) node.getOwnerElement())){
					implementDerivedGetter(activityClass, (INakedObjectNode) node);
				}
			}
		}
		visitEdges(activity.getActivityEdges());
	}
	private void implementDerivedGetter(OJAnnotatedClass activityClass,INakedObjectNode node2){
		NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap((INakedAction) node2.getOwnerElement(), getLibrary());
		NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(node2.getNearestStructuredElementAsClassifier(), node2, true);
		NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(node2.getNearestStructuredElementAsClassifier(), node2,
				false);
		List<OJPathName> emptyList = Collections.emptyList();
		OJAnnotatedOperation oper = (OJAnnotatedOperation) activityClass.findOperation(pinMap.getter(), emptyList);
		oper.setBody(new OJBlock());
		if(actionMap.isMany()){
			if(pinMap.isMany()){
				oper.initializeResultVariable(actionMap.javaDefaultValue());
				OJForStatement forEach = new OJForStatement("tmp", actionMap.javaBaseTypePath(), actionMap.getter() + "()");
				oper.getBody().addToStatements(forEach);
				forEach.getBody().addToStatements("result.add(tmp." + propertyMap.getter() + "())");
			}else{
				// TODO validate against this
			}
		}else{
			if(pinMap.isMany()){
				oper.initializeResultVariable(actionMap.javaDefaultValue());
				oper.getBody().addToStatements("result.add(tmp." + propertyMap.getter() + "())");
			}else{
				oper.initializeResultVariable(actionMap.getter() + "()." + propertyMap.getter() + "()");
			}
		}
	}
	protected void visitEdges(Collection<INakedActivityEdge> activityEdges){
		for(INakedActivityEdge edge:activityEdges){
			activityEdge(edge);
		}
	}
	private void implementNodeMethod(OJClass activityClass,INakedActivityNode node){
		Jbpm5ActionBuilder<?> implementor = null;
		if(node instanceof INakedExpansionRegion){
			implementor = new ExpansionRegionBuilder(getLibrary(), (INakedExpansionRegion) node);
		}else if(node instanceof INakedEmbeddedSingleScreenTask){
			implementor = new EmbeddedSingleScreenTaskBuilder(getLibrary(), (INakedEmbeddedSingleScreenTask) node);
		}else if(node instanceof INakedEmbeddedScreenFlowTask){
			implementor = new EmbeddedScreenFlowTaskBuilder(getLibrary(), (INakedEmbeddedScreenFlowTask) node);
		}else if(node instanceof INakedCallBehaviorAction){
			implementor = new CallBehaviorActionBuilder(getLibrary(), (INakedCallBehaviorAction) node);
		}else if(node instanceof INakedCallOperationAction){
			implementor = new CallOperationActionBuilder(getLibrary(), (INakedCallOperationAction) node);
		}else if(node instanceof INakedAcceptEventAction){
			implementor = new AcceptEventActionBuilder(getLibrary(), (INakedAcceptEventAction) node);
		}else if(node instanceof INakedParameterNode){
			INakedParameterNode parameterNode = (INakedParameterNode) node;
			implementor = new ParameterNodeBuilder(getLibrary(), parameterNode);
		}else if(node instanceof INakedReplyAction){
			INakedReplyAction action = (INakedReplyAction) node;
			implementor = new ReplyActionBuilder(getLibrary(), action);
		}else{
			implementor = new SimpleActionBridge(getLibrary(), node, SimpleActivityMethodImplementor.resolveBuilder(node, getLibrary(), new Jbpm5ObjectNodeExpressor(
					getLibrary())));
		}
		if(implementor.hasNodeMethod()){
			OJAnnotatedOperation operation = new OJAnnotatedOperation(implementor.getMap().doActionMethod());
			OJUtil.addMetaInfo(operation, node);
			activityClass.addToOperations(operation);
			operation.addParam("context", Jbpm5Util.getProcessContext());
			if(implementor.isEffectiveFinalNode()){
				implementor.implementFinalStep(operation.getBody());
			}
			implementor.setupVariablesAndArgumentPins(operation);
			implementor.implementPreConditions(operation);
			implementor.implementActionOn(operation);
			if(implementor.isLongRunning()){
				implementor.implementCallbackMethods(activityClass);
			}else if(!(implementor.waitsForEvent() || node instanceof INakedControlNode)){
				implementor.implementPostConditions(operation);
				// implementor.implementConditionalFlows(operation,
				// operation.getBody(), true);
			}
		}
	}
	@Override
	protected Collection<? extends INakedElement> getTopLevelFlows(INakedBehavior umlBehavior){
		return Arrays.asList(umlBehavior);
	}
}
