package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedMessageStructureMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.OJWhileStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.util.AbstractProcess;
import net.sf.nakeduml.util.AbstractProcessStep;
import net.sf.nakeduml.util.ActiveEntity;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.hibernate.annotations.CascadeType;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 1. Event listeners 2. Action methods
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor {
	private static final OJPathName WORKFLOW_PROCESS_INSTANCE = new OJPathName("org.jbpm.workflow.instance.WorkflowProcessInstance");
	private static final OJPathName NODE_INSTANCE_PATH = new OJPathName("org.jbpm.workflow.instance.NodeInstance");
	private static final OJPathName NODE_PATH = new OJPathName("org.jbpm.workflow.core.Node");
	public static final OJPathName ABSTRACT_PROCESS = new OJPathName(AbstractProcess.class.getName());
	public static final OJPathName ABSTRACT_PROCESS_STEP = new OJPathName(AbstractProcessStep.class.getName());
	public static final OJPathName ACTIVE_ENTITY = new OJPathName(ActiveEntity.class.getName());

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
					implementRelationshipFromContextToMessage(behavior, context, persistent);
				}
				addContextFieldAndConstructor(ojBehavior, behavior, behavior.getContext());
			} else {
				// its own context
				OJUtil.addMethod(ojBehavior, "getContextObject", ojBehavior.getPathName().toJavaString(), "this");
			}
			implementRelationshipWithProcess(ojBehavior, persistent);
		}
	}

	protected void implementRelationshipFromContextToMessage(IParameterOwner task, OJAnnotatedClass context, boolean persistent) {
		NakedMessageStructureMap map = new NakedMessageStructureMap(task);
		OJAnnotatedField field = new OJAnnotatedField(map.fieldName(), map.javaTypePath());
		context.addToFields(field);
		field.setInitExp(map.javaDefaultValue());
		context.addToImports(map.javaDefaultTypePath());
		if (persistent) {
			OJAnnotationValue oneToMany = new OJAnnotationValue(new OJPathName(OneToMany.class.getName()));
			HibernateUtil.addCascade(field, CascadeType.DELETE_ORPHAN);
			field.addAnnotationIfNew(oneToMany);
			oneToMany.putAttribute("mappedBy", "contextObject");
			oneToMany.putAttribute("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
		} else {
			OJAnnotationValue transientA = new OJAnnotationValue(new OJPathName(Transient.class.getName()));
			field.addAnnotationIfNew(transientA);
		}
		OJAnnotatedOperation adder = new OJAnnotatedOperation();
		context.addToOperations(adder);
		adder.setName(map.adder());
		adder.addParam(map.fieldName(), map.javaBaseTypePath());
		adder.getBody().addToStatements("this." + map.fieldName() + ".add(" + map.fieldName() + ")");
		OJAnnotatedOperation getter = new OJAnnotatedOperation();
		context.addToOperations(getter);
		getter.setReturnType(map.javaTypePath());
		getter.setName(map.getter());
		getter.getBody().addToStatements("return this." + map.fieldName());
	}

	protected void implementRelationshipWithProcess(OJAnnotatedClass ojBehavior, boolean persistent) {
		ojBehavior.addToImports(BpmUtil.getJbpmKnowledgeSession());
		OJAnnotatedField processInstanceField = OJUtil.addProperty(ojBehavior, "processInstance",
				new OJPathName("WorkflowProcessInstance"), true);
		if (persistent) {
			OJAnnotatedField processInstanceIdField = OJUtil.addProperty(ojBehavior, "processInstanceId", new OJPathName("Long"), true);
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "process_instance_id"));
			processInstanceIdField.putAnnotation(column);
			processInstanceField.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJOperation getter = OJUtil.findOperation(ojBehavior, "getProcessInstance");
			getter.setBody(new OJBlock());
			OJIfStatement ifNull = new OJIfStatement(
					"this.processInstance==null || true",
					"this.processInstance=(WorkflowProcessInstance)JbpmKnowledgeSession.getInstance().getKnowledgeSession().getProcessInstance(getProcessInstanceId())");
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

	protected OJConstructor addContextFieldAndConstructor(OJAnnotatedClass ojBehvior, INakedClassifier behaviorClass,
			INakedClassifier context) {
		ClassifierMap cm = new NakedClassifierMap(context);
		OJClass contextClass = this.javaModel.findClass(cm.javaTypePath());
		OJConstructor cons = findConstructor(ojBehvior, contextClass.getPathName());
		if (cons == null) {
			cons = new OJConstructor();
			ojBehvior.addToConstructors(cons);
			cons.addParam("contextObject", contextClass.getPathName());
			OJUtil.addProperty(ojBehvior, "contextObject", contextClass.getPathName(), true);
			if (isPersistent(behaviorClass)) {
				OJAnnotatedField contextObject = (OJAnnotatedField) ojBehvior.findField("contextObject");
				OJAnnotationValue manyToOne = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToOne"));
				contextObject.putAnnotation(manyToOne);
				OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
				column.putAttribute(new OJAnnotationAttributeValue("name", "context_object"));
				column.putAttribute(new OJAnnotationAttributeValue("nullable", Boolean.FALSE));
				contextObject.putAnnotation(column);
			}
			cons.getBody().addToStatements("this.contextObject=contextObject");
		}
		return cons;
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

	protected void implementSpecificationOrStartClassifierBehaviour(INakedBehavior behavior) {
		if (behavior.isClassifierBehavior()) {
			implementStartClassifierBehavior(behavior);
		} else if (requiresOperationForInvocation(behavior)) {
			implementSpecification(behavior);
		}
	}

	private void implementSpecification(INakedBehavior behavior) {
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		NameWrapper methodName = behavior.getSpecification() == null ? behavior.getMappingInfo().getJavaName() : behavior
				.getSpecification().getMappingInfo().getJavaName();
		OJPathName ojBehavior = OJUtil.classifierPathname(behavior);
		// Method implemented by Octopus because behaviours without
		// specifications are given an emulated specification
		OJOperation javaMethod = OJUtil.findOperation(ojContext, methodName.toString());
		javaMethod.getOwner().addToImports(ojBehavior);
		if (behavior.isProcess()) {
			// Leave preconditions in tact
			if (javaMethod.getBody().getStatements().size() > 0) {
				OJStatement st = javaMethod.getBody().getStatements().get(javaMethod.getBody().getStatements().size() - 1);
				if (st.toJavaString().contains("return ")) {
					javaMethod.getBody().removeFromStatements(st);
				}
			}
			ojContext.addToImports(ojBehavior);
			OJAnnotatedField behaviorField = new OJAnnotatedField("_behavior", ojBehavior);
			javaMethod.getBody().addToLocals(behaviorField);
			behaviorField.setInitExp("new " + ojBehavior.getLast() + "(this)");
			populateBehavior(behavior, javaMethod);
			NakedMessageStructureMap map = new NakedMessageStructureMap(behavior);
			javaMethod.getBody().addToStatements(map.adder() + "(_behavior)");
			javaMethod.getBody().addToStatements("return _behavior");
			javaMethod.setReturnType(ojBehavior);
		} else {
			invokeSimpleBehavior(behavior, ojBehavior, javaMethod);
		}
	}

	private void implementStartClassifierBehavior(INakedBehavior behavior) {
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		ojContext.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(ActiveEntity.class));
		OJAnnotatedOperation start = new OJAnnotatedOperation();
		start.setName("startClassifierBehavior");
		ojContext.addToOperations(start);
		OJPathName behaviorClass = OJUtil.classifierPathname(behavior);
		ojContext.addToImports(behaviorClass);
		OJAnnotatedField behaviorField = new OJAnnotatedField("_behavior", behaviorClass);
		start.getBody().addToLocals(behaviorField);
		behaviorField.setInitExp("new " + behaviorClass.getLast() + "(this)");
		populateBehavior(behavior, start);
		start.getBody().addToStatements("_behavior.execute()");
		start.getBody().addToStatements("this.classifierBehavior=_behavior");
		OJOperation addToOwner = OJUtil.findOperation(ojContext, "addToOwningObject");
		if (addToOwner != null) {
			addToOwner.getBody().addToStatements("startClassifierBehavior()");
		}
	}

	private boolean requiresOperationForInvocation(INakedBehavior behavior) {
		return behavior.getContext() != null && !behavior.isClassifierBehavior();
	}

	public void populateBehavior(INakedBehavior parameterOwner, OJOperation javaMethod) {
		for (INakedParameter p : parameterOwner.getArgumentParameters()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parameterOwner, p);
			javaMethod.getBody().addToStatements("_behavior." + map.setter() + "(" + map.umlName() + ")");
		}
		if (parameterOwner.getPreConditions().size() > 0) {
			OJUtil.addFailedConstraints(javaMethod);
		}
	}

	protected void createJbpmProcess(IParameterOwner parameterOwner, OJOperation javaMethod) {
		OJClass owner = (OJClass) javaMethod.getOwner();
		owner.addToImports(BpmUtil.getJbpmKnowledgeSession());
		owner.addToImports(WORKFLOW_PROCESS_INSTANCE);
		owner.addToImports("org.jbpm.workflow.core.impl.WorkflowProcessImpl");
		OJPathName mapPath = new OJPathName("java.util.HashMap");
		mapPath.addToElementTypes(new OJPathName("String"));
		mapPath.addToElementTypes(new OJPathName("Object"));
		OJAnnotatedField params = new OJAnnotatedField("params", mapPath);
		javaMethod.getOwner().addToImports(mapPath);
		params.setInitExp("new HashMap<String, Object>()");
		javaMethod.getBody().addToLocals(params);
		javaMethod.getBody().addToLocals(new OJAnnotatedField("processInstance", WORKFLOW_PROCESS_INSTANCE));
		javaMethod.getBody().addToStatements("params.put(\"processObject\", this)");
		javaMethod.getBody().addToStatements(
				"processInstance = (WorkflowProcessInstance)JbpmKnowledgeSession.getInstance().getKnowledgeSession().startProcess(\""
						+ BpmUtil.generateProcessName(parameterOwner) + "\",params)");
		javaMethod.getBody().addToStatements("((WorkflowProcessImpl)processInstance.getProcess()).setAutoComplete(true)");
	}

	protected void invokeSimpleBehavior(INakedBehavior behavior, OJPathName activityClass, OJOperation javaMethod) {
		if (behavior.getReturnParameter() != null) {
			// remove "Return" statements
			javaMethod.getBody().removeFromStatements(
					javaMethod.getBody().getStatements().get(javaMethod.getBody().getStatements().size() - 1));
		}
		javaMethod.getBody().addToStatements(activityClass.getLast() + " _behavior=new " + activityClass.getLast() + "(this)");
		populateBehavior(behavior, javaMethod);
		javaMethod.getBody().addToStatements("_behavior.execute()");
		if (behavior.hasMultipleConcurrentResults()) {
			// TODO such behaviours should always be called from an activity
			// that can actually retrieve the result
			javaMethod.getBody().addToStatements("return _behavior");
			javaMethod.setReturnType(activityClass);
		} else if (behavior.getReturnParameter() != null) {
			javaMethod.getBody().addToStatements(
					"return _behavior.get" + behavior.getReturnParameter().getMappingInfo().getJavaName().getCapped() + "()");
		}
	}

	protected void implementProcessInterfaceOperations(OJClass ojBehavior, OJPathName stepEnumeration, INakedBehavior umlBehavior) {
		ojBehavior.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(AbstractProcess.class));
		doGetInnermostNonParallelStep(ojBehavior, umlBehavior);
		doIsStepActive(ojBehavior, umlBehavior);
		doGetActiveLeafSteps(ojBehavior, umlBehavior);
		doForceToStep(ojBehavior, stepEnumeration, umlBehavior);
		addGetNodeInstancesRecursively(ojBehavior);
	}

	private void doForceToStep(OJClass ojBehavior, OJPathName stepEnumeration, INakedBehavior umlBehavior) {
		OJOperation forceStateChange = new OJAnnotatedOperation();
		forceStateChange.setName("forceToStep");
		forceStateChange.addParam("step", ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		OJAnnotatedField nextStep = new OJAnnotatedField();
		nextStep.setName("nextStep");
		nextStep.setType(stepEnumeration);
		nextStep.setInitExp("(" + stepEnumeration.getLast() + ")step");
		OJBlock body = forceStateChange.getBody();
		body.addToLocals(nextStep);
		OJAnnotatedField node = new OJAnnotatedField("newNode", NODE_PATH);
		node.setInitExp("getNodeForStep(nextStep)");
		body.addToLocals(node);
		addGetNodeForStep(ojBehavior, stepEnumeration);
		OJForStatement forNodeInstances = new OJForStatement();
		body.addToStatements(forNodeInstances);
		forNodeInstances.setBody(new OJBlock());
		ojBehavior.addToImports("java.util.Collection");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		forNodeInstances.setElemName("curNodeInstance");
		forNodeInstances.setElemType(NODE_INSTANCE_PATH);
		OJIfStatement ifSameParent = new OJIfStatement("curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer()",
				"transition(curNodeInstance,newNode)");
		forNodeInstances.getBody().addToStatements(ifSameParent);
		ifSameParent.getThenPart().addToStatements("return");
		ojBehavior.addToOperations(forceStateChange);
		addTransition(ojBehavior);
	}

	public void addTransition(OJClass ojBehavior) {
		OJAnnotatedOperation transition = new OJAnnotatedOperation("transition");
		ojBehavior.addToOperations(transition);
		transition.addParam("curNodeInstance", NODE_INSTANCE_PATH);
		transition.addParam("newNode", NODE_PATH);
		OJAnnotatedField container = new OJAnnotatedField("container", new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer"));
		container.setInitExp("(NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer()");
		transition.getBody().addToLocals(container);
		ojBehavior.addToImports(container.getType());
		OJAnnotatedField nodeInstance = new OJAnnotatedField("newNodeInstance", NODE_INSTANCE_PATH);
		nodeInstance.setInitExp("container.getNodeInstance(newNode)");
		transition.getBody().addToLocals(nodeInstance);
		transition.getBody().addToStatements("container.removeNodeInstance(curNodeInstance)");
		transition.getBody().addToStatements("curNodeInstance.trigger(newNodeInstance, \"asdf\")");
		ojBehavior.addToImports(nodeInstance.getType());
	}

	private void addGetNodeForStep(OJClass ojBehavior, OJPathName stepEnumeration) {
		// TODO, look recursively for node
		OJAnnotatedOperation getNodeForState = new OJAnnotatedOperation("getNodeForStep", NODE_PATH);
		ojBehavior.addToOperations(getNodeForState);
		getNodeForState.setVisibility(OJVisibilityKind.PRIVATE);
		getNodeForState.addParam("step", stepEnumeration);
		getNodeForState.getBody().addToStatements(
				"return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()))");
		OJAnnotatedOperation recursivelyFindNode = new OJAnnotatedOperation("recursivelyFindNode", NODE_PATH);
		OJPathName collectionOfNode = new OJPathName("Collection");
		collectionOfNode.addToElementTypes(new OJPathName("Node"));
		recursivelyFindNode.addParam("step", stepEnumeration);
		recursivelyFindNode.addParam("collection", collectionOfNode);
		ojBehavior.addToOperations(recursivelyFindNode);
		OJForStatement forNodes = new OJForStatement();
		recursivelyFindNode.getBody().addToStatements(forNodes);
		forNodes.setElemName("curNode");
		forNodes.setBody(new OJBlock());
		forNodes.setElemType(NODE_PATH);
		ojBehavior.addToImports("java.util.Arrays");
		forNodes.setCollection("collection");
		OJIfStatement ifNodeFound = new OJIfStatement("curNode.getName().equals(step.getQualifiedName())", "return curNode");
		forNodes.getBody().addToStatements(ifNodeFound);
		ifNodeFound.setElsePart(new OJBlock());
		ojBehavior.addToImports("org.jbpm.workflow.core.NodeContainer");
		OJIfStatement ifNodeContainer = new OJIfStatement();
		ifNodeFound.getElsePart().addToStatements(ifNodeContainer);
		ifNodeContainer.setCondition("curNode instanceof NodeContainer");
		OJAnnotatedField childNode = new OJAnnotatedField("childNode", NODE_PATH);
		childNode.setInitExp("recursivelyFindNode(step, (Collection)Arrays.asList(((NodeContainer)curNode).getNodes()))");
		ifNodeContainer.getThenPart().addToLocals(childNode);
		OJIfStatement ifNotNull = new OJIfStatement("childNode!=null", "return childNode");
		ifNodeContainer.getThenPart().addToStatements(ifNotNull);
		recursivelyFindNode.getBody().addToStatements("return null");
	}

	private void doGetActiveLeafSteps(OJClass ojBehavior, INakedBehavior umlBehavior) {
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

	private void doIsStepActive(OJClass ojBehavior, INakedBehavior umlBehavior) {
		OJOperation isStepActive = new OJAnnotatedOperation();
		isStepActive.setName("isStepActive");
		isStepActive.addParam("step", ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		OJForStatement forNodeInstances = new OJForStatement();
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(new OJPathName("NodeInstance"));
		forNodeInstances.setElemName("nodeInstance");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		isStepActive.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("step.getQualifiedName().equals(nodeInstance.getNode().getName())", "return true");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		isStepActive.getBody().addToStatements("return false");
		isStepActive.setReturnType(new OJPathName("java.lang.boolean"));
		ojBehavior.addToOperations(isStepActive);
	}

	private void addGetNodeInstancesRecursively(OJClass ojBehavior) {
		OJPathName collectionOfNodeInstances = new OJPathName("java.util.Collection");
		collectionOfNodeInstances.addToElementTypes(new OJPathName("NodeInstance"));
		ojBehavior.addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
		OJAnnotatedOperation getNodeInstancesRecursively = new OJAnnotatedOperation("getNodeInstancesRecursively",
				collectionOfNodeInstances);
		getNodeInstancesRecursively.getBody().addToStatements(
				"return (Collection<NodeInstance>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true)");
		ojBehavior.addToOperations(getNodeInstancesRecursively);
	}

	private void doGetInnermostNonParallelStep(OJClass ojBehavior, INakedBehavior umlBehavior) {
		OJOperation getInnermostNonParallelStep = new OJAnnotatedOperation();
		if (umlBehavior.getContext() != null && umlBehavior.isClassifierBehavior()) {
			OJAnnotatedClass ojContext = findJavaClass(umlBehavior.getContext());
			OJAnnotatedField field = OJUtil.addProperty(ojContext, "currentState", new OJPathName("net.sf.nakeduml.util.AbstractProcessStep"), true);
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
		getInnermostNonParallelStep.setName("getInnermostNonParallelStep");
		getInnermostNonParallelStep.setReturnType(ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		OJIfStatement ifMany = new OJIfStatement("getProcessInstance().getNodeInstances().size()>1", "return null");
		getInnermostNonParallelStep.getBody().addToStatements(ifMany);
		OJAnnotatedField token = new OJAnnotatedField();
		token.setName("nodeInstance");
		token.setType(new OJPathName("NodeInstance"));
		token.setInitExp("(NodeInstance)getProcessInstance().getNodeInstances().iterator().next()");
		getInnermostNonParallelStep.getBody().addToLocals(token);
		OJWhileStatement whileOneChild = new OJWhileStatement();
		whileOneChild
				.setCondition("nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1");
		whileOneChild.getBody().addToStatements(
				"nodeInstance=(NodeInstance)((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next()");
		getInnermostNonParallelStep.getBody().addToStatements(whileOneChild);
		getInnermostNonParallelStep.getBody().addToStatements(
				"return " + ojBehavior.getName() + "State.resolveByQualifiedName(nodeInstance.getNode().getName())");
		ojBehavior.addToOperations(getInnermostNonParallelStep);
	}
}
