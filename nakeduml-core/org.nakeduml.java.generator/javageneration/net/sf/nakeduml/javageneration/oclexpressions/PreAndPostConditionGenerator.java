package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.basicjava.SpecificationImplementor;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

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