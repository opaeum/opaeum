package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Date;

import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.OJWhileStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;
import org.nakeduml.runtime.domain.AbstractProcess;
import org.nakeduml.runtime.domain.AbstractProcessStep;
import org.nakeduml.runtime.domain.ActiveEntity;

public abstract class AbstractJavaProcessVisitor extends AbstractBehaviorVisitor{
	public static final OJPathName ABSTRACT_PROCESS = new OJPathName(AbstractProcess.class.getName());
	public static final OJPathName ABSTRACT_PROCESS_STEP = new OJPathName(AbstractProcessStep.class.getName());
	public static final OJPathName ACTIVE_ENTITY = new OJPathName(ActiveEntity.class.getName());
	static final OJPathName STATEFUL_KNOWLEDGE_SESSION = new OJPathName("org.drools.runtime.StatefulKnowledgeSession");
	public AbstractJavaProcessVisitor(){
		super();
	}
	protected abstract Collection<? extends INakedElement> getTopLevelFlows(INakedBehavior umlBehavior);
	
	/*
	 * TODO 
	 * 1. Set returnInfo for each receive operation action
	 * 2. Call call source on each reply action
	 * 3. For processes specified by operations
	 * 3.1 override the Request field to be a normal request rather than a TaskRequest
	 * 3.2 Call the complete method on completion
	 * 4. 
	 * 
	 *   
	 */
	protected void implementRelationshipsWithContextAndProcess(INakedBehavior behavior, OJAnnotatedClass ojBehavior, boolean persistent) {
		if (behavior instanceof INakedStateMachine || behavior instanceof INakedActivity) {
			if (behavior.getContext() != null) {
				OJAnnotatedClass context = findJavaClass(behavior.getContext());
				if (behavior.isClassifierBehavior()) {
					OJAnnotatedField cb = OJUtil.addProperty(context, "classifierBehavior", ojBehavior.getPathName(), true);
					if (persistent) {
						OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName("javax.persistence.OneToOne"));
						cb.addAnnotationIfNew(toOne);
						JpaUtil.fetchLazy(toOne);
						JpaUtil.cascadeAll(toOne);
						toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", "contextObject"));
					}
				} else {
					implementRelationshipFromContextToProcess(behavior, context, persistent);
				}
				addContextFieldAndConstructor(ojBehavior, behavior, behavior.getContext());
			} else {
				// its own context
				OJUtil.addMethod(ojBehavior, "getContextObject", ojBehavior.getPathName().toJavaString(), "this");
			}
			implementRelationshipWithProcess(ojBehavior, persistent);
		}
	}

	protected void implementRelationshipWithProcess(OJAnnotatedClass ojBehavior, boolean persistent) {
		OJAnnotatedField processInstanceField = OJUtil.addProperty(ojBehavior, "processInstance",
				new OJPathName("WorkflowProcessInstance"), true);
		processInstanceField.setTransient(true);
		if (persistent) {
			OJAnnotatedField processInstanceIdField = OJUtil.addProperty(ojBehavior, "processInstanceId", new OJPathName("Long"), true);
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "process_instance_id"));
			processInstanceIdField.putAnnotation(column);
			processInstanceField.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJOperation getter = OJUtil.findOperation(ojBehavior, "getProcessInstance");
			ojBehavior.addToImports(Jbpm5Util.getWorkflowProcesInstance());
			ojBehavior.addToImports(Jbpm5Util.getWorkflowProcessImpl());
			getter.setBody(new OJBlock());
			OJIfStatement ifNull = new OJIfStatement(
					"this.processInstance==null || true",
					"this.processInstance=(WorkflowProcessInstance)org.nakeduml.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId())");
			OJIfStatement ifNotNull = new OJIfStatement("this.processInstance!=null", "((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true)");
			ifNull.getThenPart().addToStatements(ifNotNull);
			ojBehavior.addToImports(STATEFUL_KNOWLEDGE_SESSION);
			getter.getBody().addToStatements(ifNull);
			getter.getBody().addToStatements("return this.processInstance");
		}
		OJOperation getProcessDefinition = new OJAnnotatedOperation();
		OJPathName processDefinition = new OJPathName("org.jbpm.workflow.core.WorkflowProcess");
		getProcessDefinition.setName("getProcessDefinition");
		getProcessDefinition.setReturnType(processDefinition);
		ojBehavior.addToOperations(getProcessDefinition);
		getProcessDefinition.getBody().addToStatements("return (WorkflowProcess) getProcessInstance().getProcess()");
	}

	protected OJOperation implementExecute(OJAnnotatedClass ojClass, INakedBehavior oc) {
		OJOperation execute = new OJAnnotatedOperation();
		execute.setName("execute");
		ojClass.addToOperations(execute);
		// add executedOn property for sorting purposes
		OJAnnotatedField f = OJUtil.addProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		if (oc.isPersistent()) {
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
			f.putAnnotation(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(
					new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
			f.putAnnotation(temporal);
		}
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		createJbpmProcess(oc, execute);
		execute.getBody().addToStatements("this.processInstance=processInstance");
		if (!oc.getPreConditions().isEmpty()) {
			execute.getBody().addToStatements("evaluatePreConditions()");
			OJUtil.addFailedConstraints(execute);
		}
		// TODO with complex synchronous methods and transient statemachines,
		// set the processInstance in the first step
		return execute;
	}


	protected void createJbpmProcess(IParameterOwner parameterOwner,OJOperation javaMethod){
		OJClass owner = (OJClass) javaMethod.getOwner();
		owner.addToImports(Jbpm5Util.getWorkflowProcesInstance());
		owner.addToImports(Jbpm5Util.getWorkflowProcessImpl());
		OJPathName mapPath = new OJPathName("java.util.HashMap");
		mapPath.addToElementTypes(new OJPathName("String"));
		mapPath.addToElementTypes(new OJPathName("Object"));
		OJAnnotatedField params = new OJAnnotatedField("params", mapPath);
		javaMethod.getOwner().addToImports(mapPath);
		params.setInitExp("new HashMap<String, Object>()");
		javaMethod.getBody().addToLocals(params);
		javaMethod.getBody().addToLocals(new OJAnnotatedField("processInstance", Jbpm5Util.getWorkflowProcesInstance()));
		javaMethod.getBody().addToStatements("params.put(\"processObject\", this)");
		javaMethod.getBody().addToStatements(
				"processInstance = (WorkflowProcessInstance)org.nakeduml.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess(\""
						+ Jbpm5Util.generateProcessName(parameterOwner) + "\",params)");
		javaMethod.getBody().addToStatements("((WorkflowProcessImpl)processInstance.getProcess()).setAutoComplete(true)");
		javaMethod.getOwner().addToImports(STATEFUL_KNOWLEDGE_SESSION);
	}

	protected void implementProcessInterfaceOperations(OJClass ojBehavior,OJPathName stepEnumeration,INakedBehavior umlBehavior){
		ojBehavior.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(AbstractProcess.class));
		doGetInnermostNonParallelStep(ojBehavior, umlBehavior);
		doIsStepActive(ojBehavior, umlBehavior);
		doGetActiveLeafSteps(ojBehavior, umlBehavior);
		doForceToStep(ojBehavior, stepEnumeration, umlBehavior);
		OJAnnotatedOperation findNodeInstaceByUniqueId = new OJAnnotatedOperation("findNodeInstanceByUniqueId", Jbpm5Util.getNodeInstance());
		findNodeInstaceByUniqueId.addParam("uniqueId", new OJPathName("String"));
		OJForStatement forEachNodeInstance = new OJForStatement("nodeInstance", Jbpm5Util.getNodeInstance(), "getNodeInstancesRecursively()");
		findNodeInstaceByUniqueId.getBody().addToStatements(forEachNodeInstance);
		forEachNodeInstance.getBody().addToStatements(new OJIfStatement("nodeInstance.getUniqueId().equals(uniqueId)", "return nodeInstance"));
		findNodeInstaceByUniqueId.getBody().addToStatements("return null");
		ojBehavior.addToOperations(findNodeInstaceByUniqueId);
		addGetNodeInstancesRecursively(ojBehavior);
	}

	private void doForceToStep(OJClass ojBehavior,OJPathName stepEnumeration,INakedBehavior umlBehavior){
		OJOperation forceStateChange = new OJAnnotatedOperation();
		forceStateChange.setName("forceToStep");
		forceStateChange.addParam("step", ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		OJAnnotatedField nextStep = new OJAnnotatedField();
		nextStep.setName("nextStep");
		nextStep.setType(stepEnumeration);
		nextStep.setInitExp("(" + stepEnumeration.getLast() + ")step");
		OJBlock body = forceStateChange.getBody();
		body.addToLocals(nextStep);
		OJAnnotatedField node = new OJAnnotatedField("newNode", Jbpm5Util.getNode());
		node.setInitExp("getNodeForStep(nextStep)");
		body.addToLocals(node);
		addGetNodeForStep(ojBehavior, stepEnumeration);
		OJForStatement forNodeInstances = new OJForStatement();
		body.addToStatements(forNodeInstances);
		forNodeInstances.setBody(new OJBlock());
		ojBehavior.addToImports("java.util.Collection");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		forNodeInstances.setElemName("curNodeInstance");
		forNodeInstances.setElemType(Jbpm5Util.getNodeInstance());
		OJIfStatement ifSameParent = new OJIfStatement("curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer()",
				"transition(curNodeInstance,newNode)");
		forNodeInstances.getBody().addToStatements(ifSameParent);
		ifSameParent.getThenPart().addToStatements("return");
		ojBehavior.addToOperations(forceStateChange);
		addTransition(ojBehavior);
	}

	private void addTransition(OJClass ojBehavior){
		OJAnnotatedOperation transition = new OJAnnotatedOperation("transition");
		ojBehavior.addToOperations(transition);
		transition.addParam("curNodeInstance", Jbpm5Util.getNodeInstance());
		transition.addParam("newNode", Jbpm5Util.getNode());
		OJAnnotatedField container = new OJAnnotatedField("container", new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer"));
		container.setInitExp("(NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer()");
		transition.getBody().addToLocals(container);
		ojBehavior.addToImports(container.getType());
		OJAnnotatedField nodeInstance = new OJAnnotatedField("newNodeInstance", Jbpm5Util.getNodeInstance());
		nodeInstance.setInitExp("("+Jbpm5Util.getNodeInstance().getLast()+")container.getNodeInstance(newNode)");
		transition.getBody().addToLocals(nodeInstance);
		transition.getBody().addToStatements("container.removeNodeInstance(curNodeInstance)");
		transition.getBody().addToStatements("curNodeInstance.trigger(newNodeInstance, \"asdf\")");
		ojBehavior.addToImports(nodeInstance.getType());
	}

	private void addGetNodeForStep(OJClass ojBehavior,OJPathName stepEnumeration){
		OJAnnotatedOperation getNodeForState = new OJAnnotatedOperation("getNodeForStep", Jbpm5Util.getNode());
		ojBehavior.addToOperations(getNodeForState);
		getNodeForState.setVisibility(OJVisibilityKind.PRIVATE);
		getNodeForState.addParam("step", stepEnumeration);
		getNodeForState.getBody().addToStatements(
				"return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()))");
		OJAnnotatedOperation recursivelyFindNode = new OJAnnotatedOperation("recursivelyFindNode", Jbpm5Util.getNode());
		OJPathName collectionOfNode = new OJPathName("Collection");
		collectionOfNode.addToElementTypes(Jbpm5Util.getNode());
		recursivelyFindNode.addParam("step", stepEnumeration);
		recursivelyFindNode.addParam("collection", collectionOfNode);
		ojBehavior.addToOperations(recursivelyFindNode);
		OJForStatement forNodes = new OJForStatement();
		recursivelyFindNode.getBody().addToStatements(forNodes);
		forNodes.setElemName("curNode");
		forNodes.setBody(new OJBlock());
		forNodes.setElemType(Jbpm5Util.getNode());
		ojBehavior.addToImports("java.util.Arrays");
		forNodes.setCollection("collection");
		OJIfStatement ifNodeFound = new OJIfStatement("curNode.getId()==step.getId()", "return curNode");
		forNodes.getBody().addToStatements(ifNodeFound);
		ifNodeFound.setElsePart(new OJBlock());
		ojBehavior.addToImports("org.jbpm.workflow.core.NodeContainer");
		OJIfStatement ifNodeContainer = new OJIfStatement();
		ifNodeFound.getElsePart().addToStatements(ifNodeContainer);
		ifNodeContainer.setCondition("curNode instanceof NodeContainer");
		OJAnnotatedField childNode = new OJAnnotatedField("childNode", Jbpm5Util.getNode());
		childNode.setInitExp("recursivelyFindNode(step, (Collection)Arrays.asList(((NodeContainer)curNode).getNodes()))");
		ifNodeContainer.getThenPart().addToLocals(childNode);
		OJIfStatement ifNotNull = new OJIfStatement("childNode!=null", "return childNode");
		ifNodeContainer.getThenPart().addToStatements(ifNotNull);
		recursivelyFindNode.getBody().addToStatements("return null");
	}

	private void doGetActiveLeafSteps(OJClass ojBehavior,INakedBehavior umlBehavior){
		OJAnnotatedOperation getActiveLeafStates = new OJAnnotatedOperation();
		OJPathName ps = ReflectionUtil.getUtilInterface(AbstractProcessStep.class);
		OJPathName set = new OJPathName("java.util.Set");
		set.addToElementTypes(ps);
		getActiveLeafStates.setReturnType(set);
		getActiveLeafStates.setName("getActiveLeafSteps");
		OJAnnotatedField results = new OJAnnotatedField();
		results.setName("results");
		results.setType(new OJPathName("Set"));
		ojBehavior.addToImports("java.util.Set");
		ojBehavior.addToImports("java.util.HashSet");
		results.setInitExp("new HashSet<" + ps.getLast() + ">()");
		getActiveLeafStates.getBody().addToLocals(results);
		// TODO find the active leaf tokens and map them to the EnumLiteral
		// representing that state.
		getActiveLeafStates.getBody().addToStatements("return results");
		ojBehavior.addToOperations(getActiveLeafStates);
	}

	protected void doIsStepActive(OJClass ojBehavior,INakedBehavior umlBehavior){
		OJOperation isStepActive = new OJAnnotatedOperation();
		isStepActive.setName("isStepActive");
		isStepActive.addParam("step", ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		Collection<? extends INakedElement> topLevelFlows = getTopLevelFlows(umlBehavior);
		for (INakedElement flow : topLevelFlows) {
			String endNodeFieldName = Jbpm5Util.endNodeFieldNameFor(flow);
			OJAnnotatedField field = new OJAnnotatedField(endNodeFieldName, new OJPathName(ojBehavior.getName() + "State"));
			ojBehavior.addToFields(field);
			if (umlBehavior.isPersistent()) {
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Enumerated")));
			}
		}
		for (INakedElement flow : topLevelFlows) {
			String endNodeFieldName = Jbpm5Util.endNodeFieldNameFor(flow);
			OJIfStatement ifEquals = new OJIfStatement("step==this." + endNodeFieldName, "return true");
			isStepActive.getBody().addToStatements(ifEquals);
		}
		OJIfStatement ifProcessNull = new OJIfStatement("getProcessInstance()==null", "return false");
		isStepActive.getBody().addToStatements(ifProcessNull);
		ifProcessNull.setElsePart(new OJBlock());
		OJForStatement forNodeInstances = new OJForStatement();
		ifProcessNull.getElsePart().addToStatements(forNodeInstances);
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(Jbpm5Util.getNodeInstance());
		forNodeInstances.setElemName("nodeInstance");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		OJIfStatement ifNameEquals = new OJIfStatement("step.getQualifiedName().equals(nodeInstance.getNode().getName())", "return true");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		isStepActive.getBody().addToStatements("return false");
		isStepActive.setReturnType(new OJPathName("java.lang.boolean"));
		ojBehavior.addToOperations(isStepActive);
	}

	protected void addGetNodeInstancesRecursively(OJClass ojBehavior){
		OJPathName collectionOfNodeInstances = new OJPathName("java.util.Collection");
		collectionOfNodeInstances.addToElementTypes(Jbpm5Util.getNodeInstance());
		ojBehavior.addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
		OJAnnotatedOperation getNodeInstancesRecursively = new OJAnnotatedOperation("getNodeInstancesRecursively",
				collectionOfNodeInstances);
		getNodeInstancesRecursively.getBody().addToStatements(
				"return (Collection<" + Jbpm5Util.getNodeInstance().getLast()
						+ ">)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true)");
		ojBehavior.addToOperations(getNodeInstancesRecursively);
	}

	private void doGetInnermostNonParallelStep(OJClass ojBehavior,INakedBehavior umlBehavior){
		OJOperation getInnermostNonParallelStep = new OJAnnotatedOperation();
		getInnermostNonParallelStep.setName("getInnermostNonParallelStep");
		getInnermostNonParallelStep.setReturnType(ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		Collection<? extends INakedElement> topLevelFlows = getTopLevelFlows(umlBehavior);
		ojBehavior.addToOperations(getInnermostNonParallelStep);
		if (topLevelFlows.size() > 1) {
			getInnermostNonParallelStep.getBody().addToStatements("return null");
		} else {
			String fieldName = Jbpm5Util.endNodeFieldNameFor(topLevelFlows.iterator().next());
			OJIfStatement ifEnded = new OJIfStatement("this." + fieldName + "!=null", "return this." + fieldName);
			getInnermostNonParallelStep.getBody().addToStatements(ifEnded);
			ifEnded.setElsePart(new OJBlock());
			OJIfStatement ifMany = new OJIfStatement("getProcessInstance().getNodeInstances().size()>1", "return null");
			ifEnded.getElsePart().addToStatements(ifMany);
			OJAnnotatedField token = new OJAnnotatedField();
			token.setName("nodeInstance");
			token.setType(Jbpm5Util.getNodeInstance());
			token.setInitExp("("+Jbpm5Util.getNodeInstance().getLast()+")getProcessInstance().getNodeInstances().iterator().next()");
			ifEnded.getElsePart().addToLocals(token);
			OJWhileStatement whileOneChild = new OJWhileStatement();
			whileOneChild
					.setCondition("nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1");
			whileOneChild.getBody().addToStatements(
					"nodeInstance=("+Jbpm5Util.getNodeInstance().getLast()+")((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next()");
			ifEnded.getElsePart().addToStatements(whileOneChild);
			ifEnded.getElsePart().addToStatements(
					"return " + ojBehavior.getName() + "State.resolveByQualifiedName(((" +Jbpm5Util.getNode().getLast() + ")nodeInstance.getNode()).getName())");
		}
	}
}