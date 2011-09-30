package org.opeum.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opeum.javageneration.jbpm5.AbstractJavaProcessVisitor;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import org.opeum.javageneration.jbpm5.actions.CallBehaviorActionBuilder;
import org.opeum.javageneration.jbpm5.actions.CallOperationActionBuilder;
import org.opeum.javageneration.jbpm5.actions.EmbeddedScreenFlowTaskBuilder;
import org.opeum.javageneration.jbpm5.actions.EmbeddedSingleScreenTaskBuilder;
import org.opeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opeum.javageneration.jbpm5.actions.ParameterNodeBuilder;
import org.opeum.javageneration.jbpm5.actions.ReplyActionBuilder;
import org.opeum.javageneration.jbpm5.actions.SimpleActionBridge;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.maps.SignalMap;
import org.opeum.javageneration.oclexpressions.CodeCleanup;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.linkage.PinLinker;
import org.opeum.linkage.ProcessIdentifier;
import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.actions.INakedCallBehaviorAction;
import org.opeum.metamodel.actions.INakedCallOperationAction;
import org.opeum.metamodel.actions.INakedReplyAction;
import org.opeum.metamodel.actions.INakedSendSignalAction;
import org.opeum.metamodel.activities.ActivityKind;
import org.opeum.metamodel.activities.ActivityNodeContainer;
import org.opeum.metamodel.activities.ControlNodeType;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityEdge;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedControlNode;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedExpansionRegion;
import org.opeum.metamodel.activities.INakedObjectFlow;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.activities.INakedParameterNode;
import org.opeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.core.INakedElement;

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
			implementNodeMethods(activityClass, activity);
			doExecute(activity, activityClass);
			if(activity.getActivityKind() == ActivityKind.PROCESS){
				implementProcessInterfaceOperations(activityClass, stateClass, activity);
				OJOperation init = activityClass.findOperation("init", Arrays.asList(Jbpm5Util.getProcessContext()));
				EventUtil.requestEvents((OJAnnotatedOperation) init, activity.getActivityNodes(),getLibrary().getBusinessRole()!=null);
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
							OperationAnnotator.findOrCreateEventGenerator( (INakedBehavioredClassifier) ssa.getTargetElement().getNakedBaseType(),ojTarget, map);
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
	private void implementNodeMethods(OJClass activityClass,INakedActivity activity){
		activityClass.addToImports(Jbpm5Util.getProcessContext());
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAction || node instanceof INakedParameterNode || node instanceof INakedControlNode || node instanceof INakedExpansionRegion
					|| node instanceof INakedExpansionNode){
				this.implementNodeMethod(activityClass, node);
				if(node instanceof ActivityNodeContainer){
					visitEdges(((ActivityNodeContainer) node).getActivityEdges());
				}
			}
		}
		visitEdges(activity.getActivityEdges());
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
			implementor = new ReplyActionBuilder(getLibrary(),action);
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
