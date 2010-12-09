package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.CallActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.actions.OpaqueActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.ParameterNodeBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.SimpleActionBridge;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;

/**
 * 
 * 
 */
public class ActivityProcessImplementor extends AbstractBehaviorVisitor {
	private IOclEngine oclEngine;

	@Override
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.oclEngine = workspace.getOclEngine();
	}

	@VisitBefore(matchSubclasses = true)
	public void visitDecisionNode(INakedControlNode node) {
		if (node.getControlNodeType().isDecisionNode() && OJUtil.hasOJClass(node.getActivity())) {
			OJClass c = findJavaClass(node.getActivity());
			Set<INakedActivityEdge> outgoing = node.getAllEffectiveOutgoing();
			for (INakedActivityEdge edge : outgoing) {
				if (edge.getGuard() != null) {
					OJAnnotatedOperation oper = new OJAnnotatedOperation();
					c.addToOperations(oper);
					oper.setReturnType(new OJPathName("boolean"));
					ActivityUtil.setupVariables(oper, node);
					IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
					oper.getBody().addToStatements("return " + ValueSpecificationUtil.expressValue(oper, edge.getGuard(), node.getActivity(),booleanType));
					oper.setName(BpmUtil.getGuardMethod(edge));
					oper.addParam("context", ActivityUtil.PROCESS_CONTEXT);
				}
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void implementActivity(INakedActivity activity) {
		if (activity.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
			OJAnnotatedClass activityClass = findJavaClass(activity);
			OJPathName stateClass = OJUtil.packagePathname(activity.getNameSpace());
			stateClass.addToNames(activity.getMappingInfo().getJavaName() + "State");
			implementNodeMethods(activityClass, activity);
			implementRelationshipsWithContextAndProcess(activity, activityClass, activity.isPersistent());
			doExecute(activity, activityClass);
			implementSpecificationOrStartClassifierBehaviour(activity);
			if (activity.getActivityKind() == ActivityKind.PROCESS) {
				implementProcessInterfaceOperations(activityClass, stateClass, activity);
			}
		}
	}

	private void doExecute(INakedActivity activity, OJAnnotatedClass activityClass) {
		OJOperation execute = implementExecute(activityClass, activity);
		if (activity.isProcess()) {
			execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		}
	}

	private void implementNodeMethods(OJClass activityClass, INakedActivity activity) {
		activityClass.addToImports(ActivityUtil.PROCESS_CONTEXT);
		for (INakedActivityNode node : activity.getActivityNodesRecursively()) {
			if (node instanceof INakedAction || node instanceof INakedParameterNode || node instanceof INakedControlNode
					|| node instanceof INakedExpansionRegion || node instanceof INakedExpansionNode) {
				this.implementNodeMethod(activityClass, node);
			}
		}
	}

	private void implementNodeMethod(OJClass activityClass, INakedActivityNode node) {
		Jbpm5ActionBuilder<?> implementor = null;
		if (node instanceof INakedExpansionRegion) {
			implementor = new ExpansionRegionBuilder(oclEngine, (INakedExpansionRegion) node);
		} else if (node instanceof INakedOpaqueAction) {
			implementor = new OpaqueActionBuilder(oclEngine, (INakedOpaqueAction) node);
		} else if (node instanceof INakedCallAction) {
			implementor = new CallActionBuilder(oclEngine, (INakedCallAction) node);
		} else if (node instanceof INakedAcceptEventAction) {
			implementor = new AcceptEventActionBuilder(oclEngine, (INakedAcceptEventAction) node);
		} else if (node instanceof INakedParameterNode) {
			INakedParameterNode parameterNode = (INakedParameterNode) node;
			implementor = new ParameterNodeBuilder(oclEngine, parameterNode);
		} else {
			implementor = new SimpleActionBridge(oclEngine, node, SimpleActivityMethodImplementor.resolveActionBuilder(node, oclEngine,
					new Jbpm5ObjectNodeExpressor(oclEngine)));
		}
		if (implementor.hasNodeMethod()) {
			OJAnnotatedOperation operation = new OJAnnotatedOperation();
			operation.setName(implementor.getMap().doActionMethod());
			activityClass.addToOperations(operation);
			operation.addParam("context", ActivityUtil.PROCESS_CONTEXT);
			implementor.setupVariables(operation);
			implementor.implementPreConditions(operation);
			implementor.implementActionOn(operation);
			if (implementor.isTask()) {
				implementor.implementSupportingTaskMethods(activityClass);
			} else if (!(implementor.waitsForEvent() || node instanceof INakedControlNode)) {
				implementor.implementPostConditions(operation);
//				implementor.implementConditionalFlows(operation, operation.getBody(), true);
			}
		}
	}
}
