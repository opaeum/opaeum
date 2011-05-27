package net.sf.nakeduml.javageneration.jbpm5;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedMessageStructureMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;
import org.nakeduml.runtime.domain.ActiveEntity;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor {


	protected void implementRelationshipFromContextToProcess(IParameterOwner task, OJAnnotatedClass context, boolean persistent) {
		NakedMessageStructureMap map = new NakedMessageStructureMap(task);
		OJAnnotatedField field = new OJAnnotatedField(map.fieldName(), map.javaTypePath());
		context.addToFields(field);
		field.setInitExp(map.javaDefaultValue());
		context.addToImports(map.javaDefaultTypePath());
		if (persistent) {
			OJAnnotationValue oneToMany = new OJAnnotationValue(new OJPathName(OneToMany.class.getName()));
//			HibernateUtil.addCascade(field, CascadeType.DELETE_ORPHAN);
			field.addAnnotationIfNew(oneToMany);
			oneToMany.putAttribute("mappedBy", "contextObject");
			oneToMany.putAttribute("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
			oneToMany.putAttribute("orphanRemoval", true);
			oneToMany.putAttribute("cascade", new OJEnumValue(new OJPathName("javax.persistence.CascadeType"), "ALL"));
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


	protected OJConstructor addContextFieldAndConstructor(OJAnnotatedClass ojBehvior, INakedClassifier behaviorClass,
			INakedClassifier context) {
		ClassifierMap cm = new NakedClassifierMap(context);
		OJClass contextClass = this.javaModel.findClass(cm.javaTypePath());
		OJConstructor cons = findConstructor(ojBehvior, contextClass.getPathName());
		if (cons == null) {
			cons = new OJConstructor();
			ojBehvior.addToConstructors(cons);
			cons.addParam("contextObject", contextClass.getPathName());
			cons.getBody().addToStatements("this.contextObject=contextObject");
		}
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
		return cons;
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
}
