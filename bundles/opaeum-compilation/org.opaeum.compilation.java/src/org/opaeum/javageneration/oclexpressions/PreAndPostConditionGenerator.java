package org.opaeum.javageneration.oclexpressions;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

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
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.textmetamodel.TextWorkspace;

//TODO implement post conditions 
//as a method similar to "checkInvariants" on operations that are represented as classifiers/ tuples
//or in the finally block
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,SpecificationImplementor.class},after = {
		OperationAnnotator.class,SpecificationImplementor.class,AttributeExpressionGenerator.class
/* Need the sequence of the ocl generating steps to be repeatable to ensure minimal deviations from previous generation */
})
public class PreAndPostConditionGenerator extends AbstractJavaProducingVisitor{
	OperationAnnotator operationAnnotator=new OperationAnnotator();
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		operationAnnotator.initialize(pac, config, textWorkspace, workspace, ojUtil);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavioredClassifier(BehavioredClassifier bc){
		for(Behavior b:bc.getOwnedBehaviors()){
			visitBehavior(b);
			if(b instanceof OpaqueBehavior){
				visitOpaqueBehavior((OpaqueBehavior) b);
			}
		}
		if(bc instanceof Behavior && ((Behavior) bc).getContext()==null &&EmfBehaviorUtil.hasExecutionInstance((Behavior) bc)){
			Behavior behavior=(Behavior) bc;
			boolean hasSuperClass = EmfBehaviorUtil.hasSuperClass(behavior);
			addEvaluationMethod(behavior.getPreconditions(), "evaluatePreconditions", behavior, hasSuperClass);
			addEvaluationMethod(behavior.getPostconditions(), "evaluatePostconditions", behavior, hasSuperClass);
		}
	}
	private void visitBehavior(Behavior behavior){
		if(ojUtil.hasOJClass(behavior.getContext()) && behavior.getOwner() instanceof Classifier){
			// Ignore transition effects and state actions for now
			if(EmfBehaviorUtil.hasExecutionInstance(behavior)){
				boolean hasSuperClass = EmfBehaviorUtil.hasSuperClass(behavior);
				addEvaluationMethod(behavior.getPreconditions(), "evaluatePreconditions", behavior, hasSuperClass);
				addEvaluationMethod(behavior.getPostconditions(), "evaluatePostconditions", behavior, hasSuperClass);
			}else{
				OperationMap mapper = ojUtil.buildOperationMap(behavior);
				OJAnnotatedClass myOwner = findJavaClass(behavior.getContext());
				addLocalConditions(myOwner, mapper, behavior.getPreconditions(), true);
				addLocalConditions(myOwner, mapper, behavior.getPostconditions(), false);
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
					OJAnnotatedField result = new OJAnnotatedField("result", map.javaReturnTypePath());
					result.setInitExp(map.javaReturnDefaultValue());
					execute.getBody().addToLocals(result);
					PropertyMap resultMap = ojUtil.buildStructuralFeatureMap(map.getReturnParameter());
					Classifier actualType = getLibrary().getActualType(map.getReturnParameter());
					execute.getBody().addToStatements("result=" + valueSpecificationUtil.expressOcl(oclBehaviorContext, execute, actualType));
					execute.getBody().addToStatements(resultMap.setter() + "(result)");
				}
			}else if(ojUtil.hasOJClass(behavior.getContext()) && behavior.getOwner() instanceof Classifier){
				OJAnnotatedClass javaContext = findJavaClass(behavior.getContext());
				OperationMap map = ojUtil.buildOperationMap(behavior);
				OJAnnotatedOperation oper = (OJAnnotatedOperation) javaContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
				if(oper==null){
					//TODO investigate why this happens whith selection behaviors on simple activities
					oper=this.operationAnnotator.findOrCreateOperation(behavior.getContext(), javaContext, map, false);
				}
				this.addBody(oper, map, oclBehaviorContext);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(Classifier owner){
		if(ojUtil.hasOJClass(owner) && !(owner instanceof Interface)){
			for(Operation oper:EmfOperationUtil.getEffectiveOperations(owner)){
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
					OpaqueExpression oe = (OpaqueExpression) specification;
					OpaqueExpressionContext oclExpressionContext = getLibrary().getOclExpressionContext(oe);
					if(!oclExpressionContext.hasErrors()){
						addBody(myOper, mapper, oclExpressionContext);
					}
				}
			}
		}
		//
		if(EmfBehaviorUtil.hasExecutionInstance(oper)){
			Classifier messageClass = getLibrary().getMessageStructure(oper);
			boolean hasSuperClass = oper.getRedefinedOperations().size() > 0
					&& EmfBehaviorUtil.hasExecutionInstance(oper.getRedefinedOperations().get(0));
			addEvaluationMethod(oper.getPreconditions(), "evaluatePreconditions", messageClass, hasSuperClass);
			addEvaluationMethod(oper.getPostconditions(), "evaluatePostconditions", messageClass, hasSuperClass);
		}else{
			OJClass myOwner = findJavaClass(owner);
			addConditionsForImmediateOperationRecursively(oper, myOwner, mapper);
		}
	}
	protected void addConditionsForImmediateOperationRecursively(Operation oper,OJClass owner,OperationMap mapper){
		if(oper.getRedefinedOperations().size() > 0){
			addConditionsForImmediateOperationRecursively(oper.getRedefinedOperations().get(0), owner, mapper);
		}
		addLocalConditions(owner, mapper, oper.getPreconditions(), true);
		addLocalConditions(owner, mapper, oper.getPostconditions(), false);
	}
	public void addLocalConditions(OJClass myOwner,OperationMap mapper,Collection<Constraint> conditions,boolean pre){
		OJOperation myOper1 = myOwner.findOperation(mapper.javaOperName(), mapper.javaParamTypePaths());
		ConstraintGenerator cg = new ConstraintGenerator(ojUtil, myOwner, mapper.getNamedElement());
		if(conditions.size() > 0){
			cg.addConstraintChecks(myOper1, conditions, pre);
		}
	}
	public void addEvaluationMethod(Collection<Constraint> conditions,String evaluationMethodName,Classifier messageClass,
			boolean hasSuperClass){
		OJClass myOwner = findJavaClass(messageClass);
		OJOperation myOper1 = new OJAnnotatedOperation(evaluationMethodName);
		myOwner.addToOperations(myOper1);
		if(hasSuperClass){
			myOper1.getBody().addToStatements("super." + evaluationMethodName+"()");
		}
		ConstraintGenerator cg = new ConstraintGenerator(ojUtil, myOwner, messageClass);
		if(conditions.size() > 0){
			cg.addConstraintChecks(myOper1, conditions, true);
			// true because they can sit anywhere in the method
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