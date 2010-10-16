package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.oclengine.IOclContext;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
public class PreAndPostConditionGenerator extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior behavior) {
		if (hasOJClass(behavior.getContext()) && behavior.getOwnerElement() instanceof INakedClassifier) {
			if (BehaviorUtil.hasExecutionInstance(behavior)) {
				// DO not do transition effects and state actions
				addEvaluationMethod(behavior.getPreConditions(), "evaluatePreConditions", behavior);
				addEvaluationMethod(behavior.getPostConditions(), "evaluatePostConditions", behavior);
			} else {
				NakedOperationMap mapper = new NakedOperationMap(behavior);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPreConditions(), true);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPostConditions(), false);
			}
		}
	}

	@VisitBefore(matchSubclasses = false)
	public void visitOpaqueAction(INakedOpaqueAction constrained) {
		OpaqueActionMessageStructureImpl messageClass = new OpaqueActionMessageStructureImpl(constrained);
		addEvaluationMethod(constrained.getPostConditions(), "evaluatePostConditions", messageClass);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedBehavioredClassifier owner) {
		for (INakedOperation oper : owner.getEffectiveOperations()) {
			if (oper.getOwner() instanceof INakedInterface || oper.getOwner()==owner) {
				processOperation(oper, owner);
			}
		}
	}

	public void processOperation(INakedOperation oper, INakedBehavioredClassifier owner) {
		NakedOperationMap mapper = new NakedOperationMap(oper);
		if (oper.getBodyCondition() != null && oper.getBodyCondition().getSpecification() != null) {
			OJPathName path = OJUtil.classifierPathname(owner);
			OJClass myOwner = javaModel.findClass(path);
			if (myOwner != null) {
				OJAnnotatedOperation myOper = (OJAnnotatedOperation) myOwner.findOperation(mapper.javaOperName(),
						mapper.javaParamTypePaths());
				addBody(myOper, owner, mapper, oper.getBodyCondition());
			}
		}
		//
		if (BehaviorUtil.hasExecutionInstance(oper) && oper.getMethods().isEmpty()) {
			OperationMessageStructureImpl messageClass = new OperationMessageStructureImpl(oper);
			addEvaluationMethod(oper.getPreConditions(), "evaluatePreConditions", messageClass);
			addEvaluationMethod(oper.getPostConditions(), "evaluatePostConditions", messageClass);
		} else {
			addLocalConditions(owner, mapper, oper.getPreConditions(), true);
			addLocalConditions(owner, mapper, oper.getPostConditions(), false);
		}
	}

	public void addLocalConditions(INakedBehavioredClassifier owner, NakedOperationMap mapper, Collection<IOclContext> conditions, boolean pre) {
		OJClass myOwner = findJavaClass(owner);
		OJOperation myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
		ConstraintGenerator cg = new ConstraintGenerator(myOwner, mapper.getOperation());
		if (conditions.size() > 0) {
			cg.addConstraintChecks(myOper1, conditions, pre);
		}
	}

	public void addEvaluationMethod(Collection<IOclContext> conditions, String evaluationMethodName, INakedClassifier messageClass) {
		if (conditions.size() > 0) {
			OJClass myOwner = findJavaClass(messageClass);
			OJOperation myOper1 = new OJAnnotatedOperation();
			myOper1.setName(evaluationMethodName);
			myOwner.addToOperations(myOper1);
			ConstraintGenerator cg = new ConstraintGenerator(myOwner, messageClass);
			if (conditions.size() > 0) {
				cg.addConstraintChecks(myOper1, conditions, true);
				// true because they can sit anywhere in the method
			}
		}
	}

	private void addBody(OJAnnotatedOperation ojOper, INakedClassifier owner, NakedOperationMap map, INakedConstraint bodyCondition) {
		if (map.getOperation().getReturnParameter() == null) {
			ojOper.getBody().addToStatements(
					ValueSpecificationUtil.expressValue(ojOper, bodyCondition.getSpecification(), owner, bodyCondition.getSpecification()
							.getType()));
		} else {
			String expString = "";
			OJField result = new OJField();
			result.setName("result");
			result.setType(map.javaReturnTypePath());
			result.setInitExp(map.javaReturnDefaultValue());
			IParameterOwner oper = map.getOperation();
			expString = "result= "
					+ ValueSpecificationUtil.expressValue(ojOper, bodyCondition.getSpecification(), owner, oper.getReturnParameter()
							.getType());
			ojOper.getBody().addToLocals(result);
			ojOper.getBody().removeAllFromStatements();
			ojOper.getBody().addToStatements(expString);
			ojOper.getBody().addToStatements("return result");
		}
	}
}