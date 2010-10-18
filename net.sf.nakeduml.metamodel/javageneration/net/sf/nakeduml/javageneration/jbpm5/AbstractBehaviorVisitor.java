package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.OneToMany;

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
import net.sf.nakeduml.javametamodel.OJWhileStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.javametamodel.utilities.OJOperationComparator;
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
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.hibernate.annotations.CascadeType;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 1. Event listeners 2. Action methods
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor {
	public static final OJPathName ABSTRACT_PROCESS = new OJPathName(AbstractProcess.class.getName());
	public static final OJPathName ABSTRACT_PROCESS_STEP = new OJPathName(AbstractProcessStep.class.getName());
	public static final OJPathName ACTIVE_ENTITY = new OJPathName(ActiveEntity.class.getName());

	protected void implementRelationshipsWithContextAndProcess(INakedBehavior behavior, OJAnnotatedClass ojBehavior) {
		if (behavior instanceof INakedStateMachine || behavior instanceof INakedActivity) {
			if (behavior.getContext() != null) {
				OJAnnotatedClass context = findJavaClass(behavior.getContext());
				if (behavior.isClassifierBehavior()) {
					OJAnnotatedField cb = OJUtil.addProperty(context, "classifierBehavior", ojBehavior.getPathName(), true);
					OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName("javax.persistence.OneToOne"));
					cb.addAnnotationIfNew(toOne);
					JpaUtil.fetchLazy(toOne);
					JpaUtil.cascadeAll(toOne);
					toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", "contextObject"));
				} else {
					implementRelationshipFromContextToMessage(behavior, context);
				}
				addContextFieldAndConstructor(ojBehavior, behavior, behavior.getContext());
			} else {
				// its own context
				OJUtil.addMethod(ojBehavior, "getContextObject", ojBehavior.getPathName().toJavaString(), "this");
			}
			if (behavior.isProcess()) {
				implementRelationshipWithProcess(ojBehavior);
			}
		}
	}

	protected void implementRelationshipFromContextToMessage(IParameterOwner task, OJAnnotatedClass context) {
		NakedMessageStructureMap map = new NakedMessageStructureMap(task);
		OJAnnotatedField field = new OJAnnotatedField(map.fieldName(), map.javaTypePath());
		context.addToFields(field);
		OJAnnotationValue oneToMany = new OJAnnotationValue(new OJPathName(OneToMany.class.getName()));
		HibernateUtil.addCascade(field, CascadeType.DELETE_ORPHAN);
		field.addAnnotationIfNew(oneToMany);
		oneToMany.putAttribute("mappedBy", "contextObject");
		oneToMany.putAttribute("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
		OJAnnotatedOperation adder = new OJAnnotatedOperation();
		context.addToOperations(adder);
		adder.setName(map.adder());
		adder.addParam(map.fieldName(), map.javaBaseTypePath());
		adder.getBody().addToStatements("this." + map.fieldName() + ".add(" + map.fieldName() + ")");
		// TODO implement getter and setter
	}

	protected void implementRelationshipWithProcess(OJAnnotatedClass ojBehavior) {
		// Take the context constructor and
		OJPathName processInstancePath = new OJPathName("org.jbpm.workflow.instance.WorkflowProcessInstance");
		OJUtil.addProperty(ojBehavior, "processInstanceId", new OJPathName("Long"), true);
		OJAnnotatedField processInstanceField = (OJAnnotatedField) ojBehavior.findField("processInstanceId");
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "process_instance_id"));
		processInstanceField.putAnnotation(column);
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getProcessInstance");
		getter.setReturnType(processInstancePath);
		ojBehavior.addToImports(BpmUtil.getJbpm5Environment());
		getter.getBody().addToStatements(
				"return (WorkflowProcessInstance)Jbpm5Environment.getKnowledgeSession().getProcessInstance(getProcessInstanceId())");
		ojBehavior.addToOperations(getter);
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
		if (isTaskOrProcess(oc)) {
			// add executedOn property for sorting purposes
			OJUtil.addProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
			OJAnnotatedField f = (OJAnnotatedField) ojClass.findField("executedOn");
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
			f.putAnnotation(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(
					new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
			f.putAnnotation(temporal);
			execute.getBody().addToStatements("setExecutedOn(new Date())");
			createJbpmProcess(oc, execute);
			execute.getBody().addToStatements("setProcessInstance(processInstance)");
			if (!oc.getPreConditions().isEmpty()) {
				execute.getBody().addToStatements("_behavior.evaluatePreConditions()");
				OJUtil.addFailedConstraints(execute);
			}
			execute.getBody().addToStatements("getProcessInstance().getContextInstance().setVariable(\"processObject\",this)");
		}
		return execute;
	}

	private boolean isTaskOrProcess(INakedClassifier c) {
		return c instanceof INakedStateMachine || (c instanceof INakedActivity && ((INakedActivity) c).isProcess())
				|| c instanceof OpaqueActionMessageStructureImpl || c instanceof OperationMessageStructureImpl;
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
			instantiateProcess(behavior, ojBehavior, javaMethod);
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
		instantiateProcess(behavior, OJUtil.classifierPathname(behavior), start);
		start.getBody().addToStatements("this.classifierBehavior=_behavior");
		OJOperation addToOwner = OJUtil.findOperation(ojContext, "addToOwningObject");
		if (addToOwner != null) {
			addToOwner.getBody().addToStatements("startClassifierBehavior()");
		}
	}

	private boolean requiresOperationForInvocation(INakedBehavior behavior) {
		return behavior.getContext() != null && !behavior.isClassifierBehavior();
	}

	protected void instantiateProcess(INakedBehavior parameterOwner, OJPathName behaviorClass, OJOperation javaMethod) {
		OJClass owner = (OJClass) javaMethod.getOwner();
		owner.addToImports(behaviorClass);
		javaMethod.getBody().addToStatements(behaviorClass.getLast() + " _behavior=new " + behaviorClass.getLast() + "(this)");
		populateBehavior(parameterOwner, javaMethod);
		NakedMessageStructureMap map = new NakedMessageStructureMap(parameterOwner);
		javaMethod.getBody().addToStatements(map.adder() + "(_behavior)");
		javaMethod.getBody().addToStatements("_behavior.execute()");
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

	public void createJbpmProcess(IParameterOwner parameterOwner, OJOperation javaMethod) {
		OJClass owner = (OJClass) javaMethod.getOwner();
		owner.addToImports(BpmUtil.getJbpm5Environment());
		javaMethod.getBody().addToStatements(
				"ProcessInstance processInstance = Jbpm4Environment.getKnowledgeSession.startProcess(\""
						+ generateProcessName(parameterOwner) + "\")");
	}

	protected String generateProcessName(IParameterOwner parameterOwner) {
		return parameterOwner.getOwnerElement().getMappingInfo().getPersistentName() + "_"
				+ parameterOwner.getMappingInfo().getPersistentName();
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
		doGetAuditLog(ojBehavior, umlBehavior);
		doGetInnermostNonParallelStep(ojBehavior, umlBehavior);
		doIsStepActive(ojBehavior, umlBehavior);
		doGetActiveLeafSteps(ojBehavior, umlBehavior);
		doForceToStep(ojBehavior, stepEnumeration, umlBehavior);
	}

	private void doGetAuditLog(OJClass ojBehavior, INakedBehavior umlBehavior) {
		OJOperation getAuditLog = new OJAnnotatedOperation();
		getAuditLog.setName("getAuditLog");
		getAuditLog.setReturnType(new OJPathName(List.class.getName()));
		getAuditLog.getBody().addToStatements("return processInstance.getLoggingInstance().getLogs()");
		ojBehavior.addToOperations(getAuditLog);
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
		OJAnnotatedField node = new OJAnnotatedField();
		node.setName("node");
		node.setType(new OJPathName("org.jbpm.graph.def.Node"));
		ojBehavior.addToImports(node.getType());
		node.setInitExp("processInstance.getProcessDefinition().findNode(nextStep.getQualifiedName())");
		body.addToLocals(node);
		OJAnnotatedField transition = new OJAnnotatedField();
		transition.setName("transition");
		transition.setType(new OJPathName("org.jbpm.graph.def.Transition"));
		ojBehavior.addToImports(transition.getType());
		transition.setInitExp("new Transition()");
		body.addToLocals(transition);
		OJAnnotatedField token = new OJAnnotatedField();
		token.setName("token");
		token.setType(new OJPathName("org.jbpm.graph.exe.Token"));
		ojBehavior.addToImports(token.getType());
		token.setInitExp("null");
		body.addToLocals(token);
		OJForStatement forTokens = new OJForStatement();
		body.addToStatements(forTokens);
		forTokens.setElemName("t");
		forTokens.setElemType(new OJPathName("Token"));
		ojBehavior.addToImports("java.util.Collection");
		forTokens.setCollection("(Collection<Token>)processInstance.findAllTokens()");
		OJIfStatement ifParentSame = new OJIfStatement("t.getNode().getParent().equals(node.getParent())", "token=t");
		ifParentSame.getThenPart().addToStatements("break");
		forTokens.setBody(new OJBlock());
		forTokens.getBody().addToStatements(ifParentSame);
		forceStateChange.getBody().addToStatements("transition.setFrom(token.getNode())");
		forceStateChange.getBody().addToStatements("transition.setTo(node)");
		forceStateChange.getBody().addToStatements("token.getNode().leave(new ExecutionContext(token),transition)");
		ojBehavior.addToImports(new OJPathName("org.jbpm.graph.exe.ExecutionContext"));
		// TODO end the child tokens....?
		ojBehavior.addToOperations(forceStateChange);
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
		forNodeInstances.setCollection("getProcessInstance().getNodeInstances()");
		isStepActive.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("nodeInstance.getNode().getName().equals(step.getQualifiedName())", "return true");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		isStepActive.getBody().addToStatements("return false");
		isStepActive.setReturnType(new OJPathName("java.lang.boolean"));
		ojBehavior.addToOperations(isStepActive);
	}

	private void doGetInnermostNonParallelStep(OJClass ojBehavior, INakedBehavior umlBehavior) {
		OJOperation getInnermostNonParallelStep = new OJAnnotatedOperation();
		getInnermostNonParallelStep.setName("getInnermostNonParallelStep");
		getInnermostNonParallelStep.setReturnType(ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		OJBlock body = getInnermostNonParallelStep.getBody();
		OJAnnotatedField token = new OJAnnotatedField();
		token.setName("token");
		token.setType(new OJPathName("Token"));
		token.setInitExp("processInstance.getRootToken()");
		body.addToLocals(token);
		OJWhileStatement whileOneChild = new OJWhileStatement();
		whileOneChild.setCondition("token.getChildren()!=null && token.getChildren().size()==1");
		whileOneChild.getBody().addToStatements("token=(Token)token.getChildren().values().iterator().next()");
		body.addToStatements(whileOneChild);
		body.addToStatements("return " + ojBehavior.getName() + "State.resolveByQualifiedName(token.getNode().getFullyQualifiedName())");
		ojBehavior.addToOperations(getInnermostNonParallelStep);
	}
}
