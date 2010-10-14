package net.sf.nakeduml.javageneration.bpm.activity;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.bpm.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

public class TaskExecutionImplementor extends AbstractBehaviorVisitor {
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (hasOJClass(c) && c instanceof INakedOpaqueBehavior) {
			OJAnnotatedClass ojBehavior = findJavaClass(c);
			INakedOpaqueBehavior ob = (INakedOpaqueBehavior) c;
			if (ob.getContext() != null) {
				implementExecute(ojBehavior, ob);
				implementSpecificationOrStartClassifierBehaviour(ob);
				super.addContextFieldAndConstructor(ojBehavior, ob, ob.getContext());
			}
			addGetName(c, ojBehavior);
		}
	}

	@VisitBefore
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		INakedMessageStructure message = oa.getMessageStructure();
		OJAnnotatedClass ojClass = findJavaClass(message);
		implementExecute(ojClass, message);
		super.addContextFieldAndConstructor(ojClass, message, oa.getActivity());
		addGetName(oa, ojClass);
	}
	@VisitAfter
	public void visitOperation(INakedOperation o) {
		if (BehaviorUtil.isUserResponsibility(o)) {
			super.implementRelationshipFromContextToMessage(o, findJavaClass(o.getOwner()));
			OperationMessageStructureImpl oc = new OperationMessageStructureImpl(o);
			OJAnnotatedClass ojOperationClass = findJavaClass(oc);
			implementExecute(ojOperationClass, oc);
			OJAnnotatedClass ojContext = findJavaClass(o.getOwner());
			NakedOperationMap map = new NakedOperationMap(o);
			OJOperation ojOper = ojContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
			if (BehaviorUtil.isUserResponsibility(o)) {
				//TODO implement process Invocation on Execute method
				super.invokeProcess(o, ojContext, ojOperationClass.getPathName(), ojOper);
			} else {
				super.invokeSimpleBehavior(o, ojOperationClass.getPathName(), ojOper);
			}
			OJConstructor constructor = super.addContextFieldAndConstructor(ojOperationClass, oc, oc.getOperation().getOwner());
			super.implementRelationshipWithProcess(ojOperationClass, constructor);
			addGetName(o, ojOperationClass);
		}
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
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(ca, getOclEngine().getOclLibrary());
		if (BehaviorUtil.isTaskOrProcess(ca) && map.isMany()) {
			OJAnnotatedClass ojOwner = findJavaClass(ca.getActivity());
			OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
			field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
		}
	}
}