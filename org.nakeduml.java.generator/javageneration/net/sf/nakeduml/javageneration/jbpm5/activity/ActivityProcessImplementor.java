package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.CallActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.actions.OpaqueActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.ParameterNodeBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.SimpleActionBridge;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.ActiveObject;

/**
 * 
 * 
 */
public class ActivityProcessImplementor extends AbstractBehaviorVisitor{
	@VisitBefore(matchSubclasses = true)
	public void activityEdge(INakedActivityEdge edge){
		if(edge.hasGuard() && BehaviorUtil.hasExecutionInstance(edge.getActivity())){
			INakedActivityNode node = edge.getEffectiveSource();
			OJAnnotatedClass c = findJavaClass(edge.getActivity());
			OJAnnotatedOperation oper = new OJAnnotatedOperation();
			c.addToOperations(oper);
			oper.setReturnType(new OJPathName("boolean"));
			ActivityUtil.setupVariables(oper, node);
			IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			oper.getBody().addToStatements("return " + ValueSpecificationUtil.expressValue(oper, edge.getGuard(), node.getActivity(), booleanType));
			oper.setName(Jbpm5Util.getGuardMethod(edge));
			oper.addParam("context", ActivityUtil.PROCESS_CONTEXT);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSendSignalAction(INakedSendSignalAction a){
		if(a.getTargetElement() != null){
			OJAnnotatedClass ojClass = findJavaClass(a.getTargetElement().getNakedBaseType());
			if(ojClass != null){
				ojClass.addToImplementedInterfaces(new OJPathName(ActiveObject.class.getName()));
			}else{
				System.out.println();
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
			implementRelationshipsWithContextAndProcess(activity, activityClass, activity.isPersistent());
			doExecute(activity, activityClass);
			implementSpecificationOrStartClassifierBehaviour(activity);
			if(activity.getActivityKind() == ActivityKind.PROCESS){
				implementProcessInterfaceOperations(activityClass, stateClass, activity);
			}else{
				doIsStepActive(activityClass, activity);
				super.addGetNodeInstancesRecursively(activityClass);
			}
			if(activity.isProcess()){
				OJAnnotatedOperation init = new OJAnnotatedOperation("init");
				activityClass.addToOperations(init);
				init.addParam("context", ActivityUtil.PROCESS_CONTEXT);
				init.getBody().addToStatements("this.setProcessInstanceId(context.getProcessInstance().getId())");
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
		}else if(node instanceof INakedOpaqueAction){
			implementor = new OpaqueActionBuilder(getOclEngine(), (INakedOpaqueAction) node);
		}else if(node instanceof INakedCallAction){
			implementor = new CallActionBuilder(getOclEngine(), (INakedCallAction) node);
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
			operation.setName(implementor.getMap().doActionMethod());
			activityClass.addToOperations(operation);
			operation.addParam("context", ActivityUtil.PROCESS_CONTEXT);
			if(implementor.isEffectiveFinalNode()){
				implementor.implementFinalStep(operation.getBody());
			}
			implementor.setupVariables(operation);
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