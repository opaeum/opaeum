package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractJavaProcessVisitor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.CallBehaviorActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.CallOperationBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.EmbeddedScreenFlowTaskBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.EmbeddedSingleScreenTaskBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.actions.ParameterNodeBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.SimpleActionBridge;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.maps.SignalMap;
import net.sf.nakeduml.javageneration.oclexpressions.CodeCleanup;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.ActivityNodeContainer;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

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
			implementor = new CallOperationBuilder(getLibrary(), (INakedCallOperationAction) node);
		}else if(node instanceof INakedAcceptEventAction){
			implementor = new AcceptEventActionBuilder(getLibrary(), (INakedAcceptEventAction) node);
		}else if(node instanceof INakedParameterNode){
			INakedParameterNode parameterNode = (INakedParameterNode) node;
			implementor = new ParameterNodeBuilder(getLibrary(), parameterNode);
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
			if(implementor.isTask()){
				implementor.implementSupportingTaskMethods(activityClass);
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
