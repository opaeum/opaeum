package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.AbstractJavaProcessVisitor;
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
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
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
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.IActiveObject;

public class ActivityProcessImplementor extends AbstractJavaProcessVisitor{
	@VisitBefore(matchSubclasses = true)
	public void activityEdge(INakedActivityEdge edge){
		if(edge.hasGuard() && BehaviorUtil.hasExecutionInstance(edge.getActivity())){
			INakedActivityNode node = edge.getEffectiveSource();
			OJAnnotatedClass c = findJavaClass(edge.getActivity());
			OJAnnotatedOperation oper = new OJAnnotatedOperation();
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
			oper.setName(Jbpm5Util.getGuardMethod(edge));
			oper.addParam("context", ActivityUtil.PROCESS_CONTEXT);
		}
	}
	private void addObjectFlowVariable(INakedActivityEdge edge,OJAnnotatedOperation oper,INakedObjectFlow objectFlow){
		INakedObjectNode origin = objectFlow.getOriginatingObjectNode();
		// TODO the originatingObjectNode may not have the correct type after
		// transformations and selections
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), origin, false);
		OJAnnotatedField sourceField = new OJAnnotatedField(map.umlName(), map.javaTypePath());
		oper.getBody().addToLocals(sourceField);
		Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getOclEngine());
		sourceField.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(oper.getBody(), objectFlow));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSendSignalAction(INakedSendSignalAction a){
		if(a.getTargetElement() != null){
			OJAnnotatedClass ojClass = findJavaClass(a.getTargetElement().getNakedBaseType());
			if(ojClass instanceof OJAnnotatedInterface){
				((OJAnnotatedInterface) ojClass).addToSuperInterfaces(new OJPathName(IActiveObject.class.getName()));
			}else if(ojClass != null){
				ojClass.addToImplementedInterfaces(new OJPathName(IActiveObject.class.getName()));
				OJBlock body = AbstractEventHandlerInserter.ensureProcessSignalPresent(ojClass).getBody();
				if(body.getStatements().size() == 0){
					body.addToStatements("return false");
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void implementActivity(INakedActivity activity){
		if(activity.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			OJPathName stateClass = OJUtil.packagePathname(activity.getNameSpace());
			stateClass.addToNames(activity.getMappingInfo().getJavaName() + "State");
			implementNodeMethods(activityClass, activity);
			doExecute(activity, activityClass);
			Jbpm5Util.implementRelationshipWithProcess(activityClass, activity.isPersistent(), "process");
			if(activity.getActivityKind() == ActivityKind.PROCESS){
				implementProcessInterfaceOperations(activityClass, stateClass, activity);
			}else{
				doIsStepActive(activityClass, activity);
				super.addGetNodeInstancesRecursively(activityClass);
			}
			if(activity.isProcess()){
				addInit(activityClass);
			}
		}
	}
	private void addInit(OJAnnotatedClass activityClass){
		OJAnnotatedOperation init = new OJAnnotatedOperation("init");
		activityClass.addToOperations(init);
		init.addParam("context", ActivityUtil.PROCESS_CONTEXT);
		copyDefaultConstructor(activityClass, init);
		init.getBody().addToStatements("this.setProcessInstanceId(context.getProcessInstance().getId())");
		init.getBody().addToStatements("((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true)");
	}
	private void copyDefaultConstructor(OJAnnotatedClass activityClass,OJAnnotatedOperation init){
		init.setBody(activityClass.getDefaultConstructor().getBody());
		activityClass.getDefaultConstructor().setBody(new OJBlock());
	}
	private void doExecute(INakedActivity activity,OJAnnotatedClass activityClass){
		OJOperation execute = implementExecute(activityClass, activity);
		if(activity.isProcess()){
			execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		}
	}
	private void implementNodeMethods(OJClass activityClass,INakedActivity activity){
		activityClass.addToImports(ActivityUtil.PROCESS_CONTEXT);
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAction || node instanceof INakedParameterNode || node instanceof INakedControlNode || node instanceof INakedExpansionRegion
					|| node instanceof INakedExpansionNode){
				this.implementNodeMethod(activityClass, node);
			}
		}
	}
	private void implementNodeMethod(OJClass activityClass,INakedActivityNode node){
		Jbpm5ActionBuilder<?> implementor = null;
		if(node instanceof INakedExpansionRegion){
			implementor = new ExpansionRegionBuilder(getOclEngine(), (INakedExpansionRegion) node);
		}else if(node instanceof INakedEmbeddedSingleScreenTask){
			implementor = new EmbeddedSingleScreenTaskBuilder(getOclEngine(), (INakedEmbeddedSingleScreenTask) node);
		}else if(node instanceof INakedEmbeddedScreenFlowTask){
			implementor = new EmbeddedScreenFlowTaskBuilder(getOclEngine(), (INakedEmbeddedScreenFlowTask) node);
		}else if(node instanceof INakedCallBehaviorAction){
			implementor = new CallBehaviorActionBuilder(getOclEngine(), (INakedCallBehaviorAction) node);
		}else if(node instanceof INakedCallOperationAction){
			implementor = new CallOperationBuilder(getOclEngine(), (INakedCallOperationAction) node);
		}else if(node instanceof INakedAcceptEventAction){
			implementor = new AcceptEventActionBuilder(getOclEngine(), (INakedAcceptEventAction) node);
		}else if(node instanceof INakedParameterNode){
			INakedParameterNode parameterNode = (INakedParameterNode) node;
			implementor = new ParameterNodeBuilder(getOclEngine(), parameterNode);
		}else{
			implementor = new SimpleActionBridge(getOclEngine(), node, SimpleActivityMethodImplementor.resolveBuilder(node, getOclEngine(), new Jbpm5ObjectNodeExpressor(
					getOclEngine())));
		}
		if(implementor.hasNodeMethod()){
			OJAnnotatedOperation operation = new OJAnnotatedOperation();
			OJUtil.addMetaInfo(operation, node);


			operation.setName(implementor.getMap().doActionMethod());
			activityClass.addToOperations(operation);
			operation.addParam("context", ActivityUtil.PROCESS_CONTEXT);
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
