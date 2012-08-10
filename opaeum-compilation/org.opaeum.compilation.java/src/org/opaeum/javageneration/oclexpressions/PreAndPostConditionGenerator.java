package org.opaeum.javageneration.oclexpressions;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,SpecificationImplementor.class},after = {
		OperationAnnotator.class,SpecificationImplementor.class,AttributeExpressionGenerator.class
/* Need the sequence of the ocl generating steps to be repeatable to ensure minimal deviations from previous generation */
})
public class PreAndPostConditionGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavioredClassifier(BehavioredClassifier bc){
		for(Behavior b:bc.getOwnedBehaviors()){
			visitBehavior(b);
			if(b instanceof OpaqueBehavior){
				visitOpaqueBehavior((OpaqueBehavior) b);
			}
		}
	}
	private void visitBehavior(Behavior behavior){
		if(OJUtil.hasOJClass(behavior.getContext()) && behavior.getOwner() instanceof Classifier){
			// Ignore transition effects and state actions for now
			if(EmfBehaviorUtil.hasExecutionInstance(behavior)){
				addEvaluationMethod(behavior.getPreconditions(), "evaluatePreconditions", behavior, "this");
				addEvaluationMethod(behavior.getPostconditions(), "evaluatePostconditions", behavior, "this");
			}else{
				OperationMap mapper = ojUtil.buildOperationMap(behavior);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPreconditions(), true);
				addLocalConditions(behavior.getContext(), mapper, behavior.getPostconditions(), false);
			}
		}
	}
	private void visitOpaqueBehavior(OpaqueBehavior behavior){
		OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext(behavior);
		if(!oclBehaviorContext.hasErrors()){
			if(EmfBehaviorUtil.hasExecutionInstance(behavior)){
				OJAnnotatedClass javaContext = findJavaClass(behavior);
				OJAnnotatedOperation execute = (OJAnnotatedOperation) javaContext.getUniqueOperation("execute");
				if(execute == null){
					execute = new OJAnnotatedOperation("execute");
					javaContext.addToOperations(execute);
				}
				OperationMap map = ojUtil.buildOperationMap(behavior);
				if(map.getReturnParameter() == null){
					execute.getBody().addToStatements(
							valueSpecificationUtil.expressOcl(oclBehaviorContext, execute, oclBehaviorContext.getExpression().getType()));
				}else{
					OJField result = new OJField();
					result.setName("result");
					result.setType(map.javaReturnTypePath());
					result.setInitExp(map.javaReturnDefaultValue());
					execute.getBody().addToLocals(result);
					StructuralFeatureMap resultMap = ojUtil.buildStructuralFeatureMap(map.getReturnParameter());
					Classifier actualType = getLibrary().getActualType(map.getReturnParameter());
					execute.getBody().addToStatements("result=" + valueSpecificationUtil.expressOcl(oclBehaviorContext, execute, actualType));
					execute.getBody().addToStatements(resultMap.setter() + "(result)");
				}
			}else if(OJUtil.hasOJClass(behavior.getContext()) && behavior.getOwner() instanceof Classifier){
				OJAnnotatedClass javaContext = findJavaClass(behavior.getContext());
				OperationMap map = ojUtil.buildOperationMap(behavior);
				OJAnnotatedOperation oper = (OJAnnotatedOperation) javaContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
				this.addBody(oper, map, oclBehaviorContext);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(Classifier owner){
		if(OJUtil.hasOJClass(owner) && !(owner instanceof Interface)){
			for(Operation oper:owner.getAllOperations()){
				if(oper.getOwner() instanceof Interface || oper.getOwner() == owner){
					processOperation(oper, owner);
				}
			}
		}
	}
	private void processOperation(Operation oper,Classifier owner){
		OperationMap mapper = ojUtil.buildOperationMap(oper);
		if(oper.getBodyCondition() != null && oper.getBodyCondition().getSpecification() != null){
			OJClass myOwner = findJavaClass(owner);
			if(myOwner != null){
				OJAnnotatedOperation myOper = (OJAnnotatedOperation) myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
				ValueSpecification specification = oper.getBodyCondition().getSpecification();
				if(specification instanceof OpaqueExpression){
					OpaqueExpression oe =(OpaqueExpression) specification;
					OpaqueExpressionContext oclExpressionContext = getLibrary().getOclExpressionContext(oe);
					if(!oclExpressionContext.hasErrors()){
						addBody(myOper, mapper, oclExpressionContext);
					}
				}
			}
		}
		//
		if(EmfBehaviorUtil.hasExecutionInstance(oper) && oper.getMethods().isEmpty()){
			Classifier messageClass = getLibrary().getMessageStructure(oper);
			addEvaluationMethod(oper.getPreconditions(), "evaluatePreconditions", messageClass, "getContextObject()");
			addEvaluationMethod(oper.getPostconditions(), "evaluatePostconditions", messageClass, "getContextObject()");
		}else{
			addLocalConditions(owner, mapper, oper.getPreconditions(), true);
			if(!EmfBehaviorUtil.isLongRunning(oper)){
				// implement on Operation Message Structure instead
				addLocalConditions(owner, mapper, oper.getPostconditions(), false);
			}
		}
	}
	public void addLocalConditions(Classifier owner,OperationMap mapper,Collection<Constraint> conditions,boolean pre){
		OJClass myOwner = findJavaClass(owner);
		OJOperation myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
		ConstraintGenerator cg = new ConstraintGenerator(ojUtil, myOwner, mapper.getNamedElement());
		if(conditions.size() > 0){
			cg.addConstraintChecks(myOper1, conditions, pre, "this");
		}
	}
	public void addEvaluationMethod(Collection<Constraint> conditions,String evaluationMethodName,Classifier messageClass,
			String selfExpression){
		if(conditions.size() > 0){
			OJClass myOwner = findJavaClass(messageClass);
			OJOperation myOper1 = new OJAnnotatedOperation(evaluationMethodName);
			myOwner.addToOperations(myOper1);
			ConstraintGenerator cg = new ConstraintGenerator(ojUtil, myOwner, messageClass);
			if(conditions.size() > 0){
				cg.addConstraintChecks(myOper1, conditions, true, selfExpression);
				// true because they can sit anywhere in the method
			}
		}
	}
	private void addBody(OJAnnotatedOperation ojOper,OperationMap map,AbstractOclContext specification){
		if(map.getReturnParameter() == null){
			ojOper.getBody().addToStatements(valueSpecificationUtil.expressOcl(specification, ojOper, specification.getExpression().getType()));
		}else{
			ojOper.initializeResultVariable(valueSpecificationUtil.expressOcl(specification, ojOper,
					getLibrary().getActualType(map.getReturnParameter())));
		}
	}
}