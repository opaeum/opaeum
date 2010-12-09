package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

public class TaskExecutionImplementor extends AbstractBehaviorVisitor {
	@VisitBefore
	public void visitOpaqueBehavior(INakedOpaqueBehavior ob) {
		// TODO find better place for this
		if (BehaviorUtil.hasExecutionInstance(ob)) {
			super.implementSpecificationOrStartClassifierBehaviour(ob);
			if (ob.getContext() != null) {
				addContextFieldAndConstructor(findJavaClass(ob), ob, ob.getContext());
			}
		}
	}

	@VisitBefore
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		if (oa.isTask() && oa.getTargetElement() != null) {
			INakedMessageStructure message = oa.getMessageStructure();
			OJAnnotatedClass ojClass = findJavaClass(message);
			implementExecute(oa, ojClass);
			super.addContextFieldAndConstructor(ojClass, message, oa.getActivity());
			addGetName(oa, ojClass);
			OJAnnotatedOperation completeMethod = addCompleteMethod(oa, ojClass);
			completeMethod.getBody().addToStatements("getContextObject().on" + oa.getMappingInfo().getJavaName().getCapped() + "Completed()");
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getTargetElement());
			OJAnnotatedField user = OJUtil.addProperty(ojClass, "user", map.javaBaseTypePath(), true);
			//TODO could be Interface - use @Any
			
			user.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		}
	}

	@VisitAfter
	public void visitOperation(INakedOperation o) {
		if (BehaviorUtil.isUserResponsibility(o)) {
			super.implementRelationshipFromContextToMessage(o, findJavaClass(o.getOwner()), true);
			OperationMessageStructureImpl oc = new OperationMessageStructureImpl(o);
			OJAnnotatedClass ojOperationClass = findJavaClass(oc);
			implementExecute(o, ojOperationClass);
			OJAnnotatedClass ojContext = findJavaClass(o.getOwner());
			NakedOperationMap map = new NakedOperationMap(o);
			OJOperation ojOper = ojContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
			// TODO implement process Invocation on Execute method
			OJPathName behaviorClass = ojOperationClass.getPathName();
			createJbpmProcess(o, ojOper);
			ojOper.getBody().addToStatements(o.getName() + " _" + o.getName() + "= new " + o.getName() + "(this)");
			ojOper.getBody().addToStatements("_" + o.getName() + ".execute()");
			ojOper.getBody().addToStatements("return _" + o.getName());
			ojOper.setReturnType(behaviorClass);
			super.addContextFieldAndConstructor(ojOperationClass, oc, o.getOwner());
			addGetName(o, ojOperationClass);
			addCompleteMethod(o, ojOperationClass);
		}
	}

	private OJAnnotatedOperation addCompleteMethod(PreAndPostConstrained constrained, OJAnnotatedClass ojOperationClass) {
		OJAnnotatedOperation complete = new OJAnnotatedOperation();
		ojOperationClass.addToOperations(complete);
		complete.setName("complete");
		if (constrained.getPostConditions().size() > 0) {
			complete.getBody().addToStatements("evaluatePostConditions()");
			OJUtil.addFailedConstraints(complete);
		}
		complete.getBody().addToStatements("setComplete(true)");
		OJUtil.addProperty(ojOperationClass, "complete", new OJPathName("boolean"), true);
		return complete;
	}

	private OJOperation implementExecute(PreAndPostConstrained element, OJAnnotatedClass ojClass) {
		OJOperation execute = new OJAnnotatedOperation();
		execute.setName("execute");
		ojClass.addToOperations(execute);
		if (element instanceof INakedOperation && element.getPreConditions().size() > 0) {
			OJUtil.addFailedConstraints(execute);
			execute.getBody().addToStatements("evaluatePreConditions()");
		}
		// add executedOn property for sorting purposes
		OJUtil.addProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		OJAnnotatedField f = (OJAnnotatedField) ojClass.findField("executedOn");
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
		f.putAnnotation(column);
		OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
				"javax.persistence.TemporalType"), "TIMESTAMP"));
		f.putAnnotation(temporal);
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		OJBlock block = execute.getBody();
		return execute;
	}

	private void addGetName(INakedElement c, OJAnnotatedClass ojOperationClass) {
		OJOperation getName = OJUtil.findOperation(ojOperationClass, "getName");
		if (getName == null) {
			getName = new OJAnnotatedOperation();
			getName.setName("getName");
			getName.setReturnType(new OJPathName("String"));
			ojOperationClass.addToOperations(getName);
		} else {
			getName.setBody(new OJBlock());
		}
		getName.getBody().addToStatements("return \"" + c.getName() + "On\"+getContextObject().getName()");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction ca) {
		// Always order invocations of processes or tasks by the executedOn date
		if (BehaviorUtil.isTaskOrProcess(ca)) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(ca, getOclEngine().getOclLibrary());
			if (map.isMany()) {
				OJAnnotatedClass ojOwner = findJavaClass(ca.getActivity());
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
			}
		}
	}
}