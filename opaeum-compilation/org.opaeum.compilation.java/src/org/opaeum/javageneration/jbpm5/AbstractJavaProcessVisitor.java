package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.Date;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.hibernate.HibernateUtil;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.core.PreAndPostConstrained;
import org.opaeum.runtime.domain.IActiveEntity;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.environment.Environment;

public abstract class AbstractJavaProcessVisitor extends AbstractJavaProducingVisitor{
	public static final OJPathName ABSTRACT_PROCESS_STEP = new OJPathName(IProcessStep.class.getName());
	public static final OJPathName ACTIVE_ENTITY = new OJPathName(IActiveEntity.class.getName());
	static final OJPathName STATEFUL_KNOWLEDGE_SESSION = new OJPathName("org.drools.runtime.StatefulKnowledgeSession");
	public AbstractJavaProcessVisitor(){
		super();
	}
	protected abstract Collection<? extends INakedElement> getTopLevelFlows(INakedClassifier umlBehavior);
	protected OJOperation implementExecute(OJAnnotatedClass ojClass,INakedClassifier oc){
		OJOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		// add executedOn property for sorting purposes
		OJAnnotatedField f = OJUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		if(isPersistent(oc)){
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
			f.putAnnotation(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
					"javax.persistence.TemporalType"), "TIMESTAMP"));
			f.putAnnotation(temporal);
		}
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		createJbpmProcess(oc, execute);
		execute.getBody().addToStatements("this.processInstance=processInstance");
		if(oc instanceof IParameterOwner && ((PreAndPostConstrained) oc).getPreConditions().size() > 0){
			execute.getBody().addToStatements("evaluatePreConditions()");
			OJUtil.addFailedConstraints(execute);
		}
		return execute;
	}
	protected void doIsComplete(OJAnnotatedClass activityClass,INakedClassifier container){
		OJAnnotatedOperation isComplete = new OJAnnotatedOperation("isComplete", new OJPathName("boolean"));
		activityClass.addToOperations(isComplete);
		Collection<? extends INakedElement> topLevelFlows = getTopLevelFlows(container);
		StringBuilder sb = new StringBuilder();
		for(INakedElement flow:topLevelFlows){
			sb.append(Jbpm5Util.endNodeFieldNameFor(flow) + "!=null ||");
		}
		sb.append(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "!=null");
		isComplete.initializeResultVariable(sb.toString());
	}
	protected void createJbpmProcess(INakedElement element,OJOperation javaMethod){
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
				"processInstance = (WorkflowProcessInstance)" + Environment.class.getName()
						+ ".getInstance().getComponent(StatefulKnowledgeSession.class).startProcess(\"" + Jbpm5Util.generateProcessName(element)
						+ "\",params)");
		javaMethod.getBody().addToStatements("((WorkflowProcessImpl)processInstance.getProcess()).setAutoComplete(true)");
		javaMethod.getOwner().addToImports(STATEFUL_KNOWLEDGE_SESSION);
	}
	protected void implementProcessInterfaceOperations(OJAnnotatedClass ojBehavior,OJPathName stepEnumeration,INakedClassifier umlBehavior){
		Jbpm5Util.implementRelationshipWithProcess(ojBehavior, true, "process");
		addDirtyLogic(ojBehavior);
		if(!umlBehavior.conformsTo(getLibrary().getAbstractRequest())){
			ojBehavior.addToImplementedInterfaces(new OJPathName(IProcessObject.class.getName()));
		}
		doGetInnermostNonParallelStep(ojBehavior, umlBehavior);
		doIsStepActive(ojBehavior, umlBehavior);
		doGetActiveLeafSteps(ojBehavior, umlBehavior);
		doForceToStep(ojBehavior, stepEnumeration, umlBehavior);
		doFindNodeInstanceByUniqueId(ojBehavior);
		addGetNodeInstancesRecursively(ojBehavior);
		addInit(ojBehavior);
		doIsComplete(ojBehavior, umlBehavior);
		OJAnnotatedOperation cancel = new OJAnnotatedOperation("cancel");
		ojBehavior.addToOperations(cancel);
		cancel.getBody().addToStatements("getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED)");
	}
	protected void addDirtyLogic(OJAnnotatedClass ojBehavior){
		OJAnnotatedField dirty = new OJAnnotatedField("processDirty", new OJPathName("boolean"));
		dirty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		ojBehavior.addToFields(dirty);
		OJAnnotatedOperation isDirty = new OJAnnotatedOperation("isProcessDirty", new OJPathName("boolean"));
		ojBehavior.addToOperations(isDirty);
		isDirty.getBody().addToStatements("return this.processDirty");
	}
	protected void doFindNodeInstanceByUniqueId(OJAnnotatedClass ojBehavior){
		OJAnnotatedOperation findNodeInstaceByUniqueId = new OJAnnotatedOperation("findNodeInstanceByUniqueId", Jbpm5Util.getNodeInstance());
		findNodeInstaceByUniqueId.addParam("uniqueId", new OJPathName("String"));
		OJForStatement forEachNodeInstance = new OJForStatement("nodeInstance", Jbpm5Util.getNodeInstance(), "getNodeInstancesRecursively()");
		findNodeInstaceByUniqueId.getBody().addToStatements(forEachNodeInstance);
		forEachNodeInstance.getBody().addToStatements(new OJIfStatement("nodeInstance.getUniqueId().equals(uniqueId)", "return nodeInstance"));
		findNodeInstaceByUniqueId.getBody().addToStatements("return null");
		ojBehavior.addToOperations(findNodeInstaceByUniqueId);
	}
	private void doForceToStep(OJClass ojBehavior,OJPathName stepEnumeration,INakedClassifier umlBehavior){
		OJOperation forceStateChange = new OJAnnotatedOperation("forceToStep");
		forceStateChange.addParam("step", ABSTRACT_PROCESS_STEP);
		OJAnnotatedField nextStep = new OJAnnotatedField("nextStep", stepEnumeration);
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
		nodeInstance.setInitExp("(" + Jbpm5Util.getNodeInstance().getLast() + ")container.getNodeInstance(newNode)");
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
	private void doGetActiveLeafSteps(OJClass ojBehavior,INakedClassifier umlBehavior){
		OJAnnotatedOperation getActiveLeafStates = new OJAnnotatedOperation("getActiveLeafSteps");
		OJPathName set = new OJPathName("java.util.Set");
		set.addToElementTypes(ABSTRACT_PROCESS_STEP);
		getActiveLeafStates.setReturnType(set);
		OJAnnotatedField results = new OJAnnotatedField("results", new OJPathName("Set"));
		ojBehavior.addToImports("java.util.Set");
		ojBehavior.addToImports("java.util.HashSet");
		results.setInitExp("new HashSet<" + ABSTRACT_PROCESS_STEP.getLast() + ">()");
		getActiveLeafStates.getBody().addToLocals(results);
		// TODO find the active leaf tokens and map them to the EnumLiteral
		// representing that state.
		getActiveLeafStates.getBody().addToStatements("return results");
		ojBehavior.addToOperations(getActiveLeafStates);
	}
	protected void doIsStepActive(OJClass ojBehavior,INakedClassifier umlBehavior){
		OJOperation isStepActive = new OJAnnotatedOperation("isStepActive");
		isStepActive.addParam("step", ABSTRACT_PROCESS_STEP);
		Collection<? extends INakedElement> topLevelFlows = getTopLevelFlows(umlBehavior);
		for(INakedElement flow:topLevelFlows){
			String endNodeFieldName = Jbpm5Util.endNodeFieldNameFor(flow);
			OJPathName stateClass = OJUtil.statePathname(umlBehavior);
			OJAnnotatedField field = new OJAnnotatedField(endNodeFieldName, stateClass);
			ojBehavior.addToFields(field);
			if(isPersistent(umlBehavior)){
				if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
					HibernateUtil.addEnumResolverAsCustomType(field, stateClass);
				}else{
					OJAnnotationValue enumerated = new OJAnnotationValue(new OJPathName("javax.persistence.Enumerated"));
					field.putAnnotation(enumerated);
					enumerated.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.EnumType"), "STRING"));
				}
			}
		}
		for(INakedElement flow:topLevelFlows){
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
		OJIfStatement ifNameEquals = new OJIfStatement("step.getId()==nodeInstance.getNodeId()", "return true");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		isStepActive.getBody().addToStatements("return false");
		isStepActive.setReturnType(new OJPathName("java.lang.boolean"));
		ojBehavior.addToOperations(isStepActive);
	}
	protected void addGetNodeInstancesRecursively(OJClass ojBehavior){
		OJPathName collectionOfNodeInstances = new OJPathName("java.util.Collection");
		collectionOfNodeInstances.addToElementTypes(Jbpm5Util.getNodeInstance());
		ojBehavior.addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
		OJAnnotatedOperation getNodeInstancesRecursively = new OJAnnotatedOperation("getNodeInstancesRecursively", collectionOfNodeInstances);
		getNodeInstancesRecursively.getBody().addToStatements(
				"return (Collection<" + Jbpm5Util.getNodeInstance().getLast()
						+ ">)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true)");
		ojBehavior.addToOperations(getNodeInstancesRecursively);
	}
	private void doGetInnermostNonParallelStep(OJClass ojBehavior,INakedClassifier umlBehavior){
		OJOperation getInnermostNonParallelStep = new OJAnnotatedOperation("getInnermostNonParallelStep");
		getInnermostNonParallelStep.setReturnType(ABSTRACT_PROCESS_STEP);
		Collection<? extends INakedElement> topLevelFlows = getTopLevelFlows(umlBehavior);
		ojBehavior.addToOperations(getInnermostNonParallelStep);
		if(topLevelFlows.isEmpty() || topLevelFlows.size() > 1){
			getInnermostNonParallelStep.getBody().addToStatements("return null");
		}else{
			String fieldName = Jbpm5Util.endNodeFieldNameFor(topLevelFlows.iterator().next());
			OJIfStatement ifEnded = new OJIfStatement("this." + fieldName + "!=null", "return this." + fieldName);
			getInnermostNonParallelStep.getBody().addToStatements(ifEnded);
			ifEnded.setElsePart(new OJBlock());
			OJIfStatement ifMany = new OJIfStatement("getProcessInstance().getNodeInstances().size()>1", "return null");
			ifEnded.getElsePart().addToStatements(ifMany);
			OJAnnotatedField token = new OJAnnotatedField("nodeInstance", Jbpm5Util.getNodeInstance());
			token.setInitExp("(" + Jbpm5Util.getNodeInstance().getLast() + ")getProcessInstance().getNodeInstances().iterator().next()");
			ifEnded.getElsePart().addToLocals(token);
			OJWhileStatement whileOneChild = new OJWhileStatement();
			whileOneChild
					.setCondition("nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1");
			whileOneChild.getBody().addToStatements(
					"nodeInstance=(" + Jbpm5Util.getNodeInstance().getLast()
							+ ")((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next()");
			ifEnded.getElsePart().addToStatements(whileOneChild);
			ifEnded.getElsePart().addToStatements("return " + ojBehavior.getName() + "State.resolveById(nodeInstance.getNodeId())");
		}
	}
	private void addInit(OJAnnotatedClass activityClass){
		OJAnnotatedOperation init = new OJAnnotatedOperation("init");
		activityClass.addToOperations(init);
		init.addParam("context", Jbpm5Util.getProcessContext());
		copyDefaultConstructor(activityClass, init);
		init.getBody().addToStatements("this.setProcessInstanceId(context.getProcessInstance().getId())");
		init.getBody().addToStatements("((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true)");
	}
	private void copyDefaultConstructor(OJAnnotatedClass activityClass,OJAnnotatedOperation init){
		OJBlock body = activityClass.getDefaultConstructor().getBody();
		if(body.getStatements().size() > 0 && body.getStatements().get(0).toJavaString().contains("super()")){
			body.getStatements().set(0, new OJSimpleStatement("super.init(context)"));
		}
		init.setBody(body);
		activityClass.getDefaultConstructor().setBody(new OJBlock());
	}
	protected void addParameterDelegation(OJAnnotatedClass cls,INakedBehavior b){
		if(b.getSpecification() != null){
			for(INakedParameter p:b.getOwnedParameters()){
				if(p.getLinkedParameter() != null){
					NakedStructuralFeatureMap linkedMap = OJUtil.buildStructuralFeatureMap(b, p.getLinkedParameter());
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(b, p);
					OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter(), linkedMap.javaTypePath());
					cls.addToOperations(getter);
					getter.initializeResultVariable("super." + linkedMap.getter() + "()");
				}
			}
		}
	}
}