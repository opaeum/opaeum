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
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.oclengine.IOclContext;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
public class OperationExpressionGenerator extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true, match = { INakedOpaqueAction.class, INakedBehavior.class, INakedOperation.class })
	public void operationBefore(PreAndPostConstrained constrained) {
		if (hasOJClass(constrained.getContext()) && constrained.getOwnerElement() instanceof INakedClassifier) {
			// DO not do transition effects and state actions
			buildConstraints(constrained, constrained.getPreConditions(), true);
			buildConstraints(constrained, constrained.getPostConditions(), false);
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void opaqueActionBefore(PreAndPostConstrained constrained) {
		if (hasOJClass(constrained.getContext()) && constrained.getOwnerElement() instanceof INakedClassifier) {
			// DO not do transition effects and state actions
			buildConstraints(constrained, constrained.getPreConditions(), true);
			buildConstraints(constrained, constrained.getPostConditions(), false);
		}
	}

	@VisitBefore(matchSubclasses = false)
	public NakedOperationMap buildBody(INakedOperation oper) {
		NakedOperationMap mapper = new NakedOperationMap(oper);
		if (oper.getBodyCondition() != null && oper.getBodyCondition().getSpecification() != null) {
			OJPathName path = OJUtil.classifierPathname(oper.getContext());
			OJClass myOwner = javaModel.findClass(path);
			if (myOwner != null) {
				OJAnnotatedOperation myOper = (OJAnnotatedOperation) myOwner.findOperation(mapper.javaOperName(),
						mapper.javaParamTypePaths());
				addBody(myOper, oper.getContext(), mapper, oper.getBodyCondition());
			}
		}
		return mapper;
	}

	private void buildConstraints(PreAndPostConstrained constrained, Collection<IOclContext> conditions, boolean pre) {
		OJOperation myOper1 = null;
		OJClass myOwner = null;
		boolean hasMethod = constrained instanceof INakedOperation && ((INakedOperation) constrained).getMethods().size() > 0;
		if (!hasMethod && BehaviorUtil.hasExecutionInstance(constrained) && conditions.size() > 0) {
			INakedClassifier messageClass = null;
			if (constrained instanceof INakedOperation) {
				messageClass = new OperationMessageStructureImpl((INakedOperation) constrained);
			} else {
				messageClass = (INakedClassifier) constrained;
			}
			addEvaluationMethod(conditions, pre, messageClass);
		} else {// Could not find execution instance, - these are local
				// preconditions
			myOwner = findJavaClass(constrained.getContext());
			myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
			ConstraintGenerator cg = new ConstraintGenerator(myOwner, constrained);
			if (conditions.size() > 0) {
				cg.addConstraintChecks(myOper1, conditions, pre);
			}
		}
	}

	public void addEvaluationMethod(Collection<IOclContext> conditions, boolean pre, INakedClassifier messageClass) {
		OJClass myOwner = findJavaClass(messageClass);
		String evaluationMethodName = pre ? "evaluatePreConditions" : "evaluatePostConditions";
		OJOperation myOper1 = new OJAnnotatedOperation();
		myOper1.setName(evaluationMethodName);
		myOwner.addToOperations(myOper1);
		ConstraintGenerator cg = new ConstraintGenerator(myOwner, messageClass);
		if (conditions.size() > 0) {
			cg.addConstraintChecks(myOper1, conditions, true);//true because they're treated as preconditions
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