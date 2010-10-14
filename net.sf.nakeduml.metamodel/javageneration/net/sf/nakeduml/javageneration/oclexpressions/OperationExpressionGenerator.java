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
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.oclengine.IOclContext;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
public class OperationExpressionGenerator extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true, match = { INakedBehavior.class, INakedOperation.class })
	public void operationBefore(IParameterOwner oper) {
		if (hasOJClass(oper.getContext()) && oper.getOwnerElement() instanceof INakedClassifier) {
			// DO not do transition effects and state actions
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
			buildConstraints(mapper, oper.getPreConditions(), "evaluatePreConditions");
			buildConstraints(mapper, oper.getPostConditions(), "evaluatePostConditions");
		}
	}

	private ConstraintGenerator buildConstraints(NakedOperationMap mapper, Collection<IOclContext> conditions, String evaluationMethodName) {
		OJOperation myOper1 = null;
		OJClass myOwner = null;
		boolean hasMethod = mapper.getOperation() instanceof INakedOperation
				&& ((INakedOperation) mapper.getOperation()).getMethods().size() > 0;
		if (!hasMethod && BehaviorUtil.hasExecutionInstance(mapper.getOperation())) {
			if (mapper.getOperation() instanceof INakedOperation) {
				myOwner = findJavaClass(new OperationMessageStructureImpl((INakedOperation) mapper.getOperation()));
			} else {
				myOwner = findJavaClass((INakedClassifier) mapper.getOperation());
			}
			myOper1 = new OJAnnotatedOperation();
			myOper1.setName(evaluationMethodName);
			myOwner.addToOperations(myOper1);
		} else {// Could not find execution instance, typically when an
				// Operation speficies a Process - these are local preconditions
			myOwner = findJavaClass(mapper.getOperation().getContext());
			myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
		}
		ConstraintGenerator cg = new ConstraintGenerator(myOwner, mapper.getOperation());
		if (conditions.size() > 0) {
			cg.addConstraintChecks(myOper1, conditions, true);
		}
		return cg;
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