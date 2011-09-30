package org.opeum.javageneration.oclexpressions;

import java.util.Collection;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.basicjava.SpecificationImplementor;
import org.opeum.javageneration.maps.NakedOperationMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedValueSpecification;
import org.opeum.metamodel.core.IParameterOwner;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,SpecificationImplementor.class,NakedParsedOclStringResolver.class
},after = {
		OperationAnnotator.class,SpecificationImplementor.class
})
public class PreAndPostConditionGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior behavior){
		if(OJUtil.hasOJClass(behavior.getContext()) && behavior.getOwnerElement() instanceof INakedClassifier){
			// Ignore transition effects and state actions for now
			if(BehaviorUtil.hasExecutionInstance(behavior)){
				addEvaluationMethod(behavior.getPreConditions(), "evaluatePreConditions", behavior);
				addEvaluationMethod(behavior.getPostConditions(), "evaluatePostConditions", behavior);
			}else{
				NakedOperationMap mapper = new NakedOperationMap(behavior);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPreConditions(), true);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPostConditions(), false);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueBehavior(INakedOpaqueBehavior behavior){
		if(BehaviorUtil.hasExecutionInstance(behavior)){
			OJAnnotatedClass javaContext = findJavaClass(behavior);
			OJAnnotatedOperation execute = (OJAnnotatedOperation) OJUtil.findOperation(javaContext, "execute");
			if(execute == null){
				execute = new OJAnnotatedOperation("execute");
				javaContext.addToOperations(execute);
			}
			if(behavior.getBodyExpression() != null){
				NakedOperationMap map = new NakedOperationMap(behavior);
				INakedClassifier owner = behavior.getContext();
				INakedValueSpecification specification = behavior.getBody();
				if(map.getParameterOwner().getReturnParameter() == null){
					execute.getBody().addToStatements(ValueSpecificationUtil.expressValue(execute, specification, owner, specification.getType()));
				}else{
					OJField result = new OJField();
					result.setName("result");
					result.setType(map.javaReturnTypePath());
					result.setInitExp(map.javaReturnDefaultValue());
					IParameterOwner oper = map.getParameterOwner();
					execute.getBody().addToLocals(result);
					NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(behavior, behavior.getReturnParameter());
					execute.getBody()
							.addToStatements("result=" + ValueSpecificationUtil.expressValue(execute, specification, owner, oper.getReturnParameter().getType()));
					execute.getBody().addToStatements(resultMap.setter() + "(result)");
				}
			}
		}else if(OJUtil.hasOJClass(behavior.getContext()) && behavior.getOwnerElement() instanceof INakedClassifier && behavior.getBodyExpression() != null){
			OJAnnotatedClass javaContext = findJavaClass(behavior.getContext());
			NakedOperationMap map = new NakedOperationMap(behavior);
			OJAnnotatedOperation oper = (OJAnnotatedOperation) javaContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
			this.addBody(oper, behavior.getContext(), map, behavior.getBody());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier owner){
		if(OJUtil.hasOJClass(owner) && !(owner instanceof INakedInterface)){
			for(INakedOperation oper:owner.getEffectiveOperations()){
				if(oper.getOwner() instanceof INakedInterface || oper.getOwner() == owner){
					processOperation(oper, owner);
				}
			}
		}
	}
	private void processOperation(INakedOperation oper,INakedClassifier owner){
		NakedOperationMap mapper = new NakedOperationMap(oper);
		if(oper.getBodyCondition() != null && oper.getBodyCondition().getSpecification() != null){
			OJPathName path = OJUtil.classifierPathname(owner);
			OJClass myOwner = javaModel.findClass(path);
			if(myOwner != null){
				OJAnnotatedOperation myOper = (OJAnnotatedOperation) myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
				INakedValueSpecification specification = oper.getBodyCondition().getSpecification();
				addBody(myOper, owner, mapper, specification);
			}
		}
		//
		if(BehaviorUtil.hasExecutionInstance(oper) && oper.getMethods().isEmpty()){
			INakedMessageStructure messageClass = oper.getMessageStructure();
			addEvaluationMethod(oper.getPreConditions(), "evaluatePreConditions", messageClass);
			addEvaluationMethod(oper.getPostConditions(), "evaluatePostConditions", messageClass);
		}else{
			addLocalConditions(owner, mapper, oper.getPreConditions(), true);
			if(!oper.isLongRunning()){
				// implement on Operation Message Structure instead
				addLocalConditions(owner, mapper, oper.getPostConditions(), false);
			}
		}
	}
	public void addLocalConditions(INakedClassifier owner,NakedOperationMap mapper,Collection<INakedConstraint> conditions,boolean pre){
		OJClass myOwner = findJavaClass(owner);
		OJOperation myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
		ConstraintGenerator cg = new ConstraintGenerator(myOwner, mapper.getParameterOwner());
		if(conditions.size() > 0){
			cg.addConstraintChecks(myOper1, conditions, pre);
		}
	}
	public void addEvaluationMethod(Collection<INakedConstraint> conditions,String evaluationMethodName,INakedClassifier messageClass){
		if(conditions.size() > 0){
			OJClass myOwner = findJavaClass(messageClass);
			OJOperation myOper1 = new OJAnnotatedOperation(evaluationMethodName);
			myOwner.addToOperations(myOper1);
			ConstraintGenerator cg = new ConstraintGenerator(myOwner, messageClass);
			if(conditions.size() > 0){
				cg.addConstraintChecks(myOper1, conditions, true);
				// true because they can sit anywhere in the method
			}
		}
	}
	private void addBody(OJAnnotatedOperation ojOper,INakedClassifier owner,NakedOperationMap map,INakedValueSpecification specification){
		if(map.getParameterOwner().getReturnParameter() == null){
			ojOper.getBody().addToStatements(ValueSpecificationUtil.expressValue(ojOper, specification, owner, specification.getType()));
		}else{
			IParameterOwner oper = map.getParameterOwner();
			ojOper.initializeResultVariable(ValueSpecificationUtil.expressValue(ojOper, specification, owner, oper.getReturnParameter().getType()));
		}
	}
}