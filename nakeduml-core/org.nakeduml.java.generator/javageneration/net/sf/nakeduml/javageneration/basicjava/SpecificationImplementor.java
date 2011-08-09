package net.sf.nakeduml.javageneration.basicjava;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.activity.ResponsibilityImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IActiveEntity;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
public class SpecificationImplementor extends AbstractBehaviorVisitor{
	@VisitBefore
	public void visitBehavior(INakedBehavior ob){
		if(BehaviorUtil.hasExecutionInstance(ob)){
			// Most like a long running
			if(ob.isClassifierBehavior()){
				implementStartClassifierBehavior(ob);
			}else if(requiresOperationForInvocation(ob)){
				implementSpecification(ob);
			}
		}
	}
	@VisitAfter(matchSubclasses=true)
	public void visitClassifier(INakedBehavioredClassifier  c){
		List<IOperation> operations = c.getOperations();
		for(IOperation o:operations){
			if(o.getOwner()==c || o.getOwner() instanceof INakedInterface){
				visitOperation(c, (INakedOperation) o);
			}
		}
	}
	private void visitOperation(INakedClassifier owner, INakedOperation o){
		if(o.isLongRunning()){
			INakedMessageStructure oc = o.getMessageStructure();
			OJAnnotatedClass ojOperationClass = findJavaClass(oc);
			Jbpm5Util.implementRelationshipWithProcess(ojOperationClass, true, "callingProcess");
			addSetReturnInfo(ojOperationClass);
			// implementRelationshipWithProcess(ojOperationClass, true);
			OJAnnotatedClass ojContext = findJavaClass(o.getOwner());
			NakedOperationMap map = new NakedOperationMap(o);
			OJOperation ojOper = ojContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
			OJPathName behaviorClass = ojOperationClass.getPathName();
			// createJbpmProcess(o, ojOper);
			ojOper.getBody().addToStatements(o.getName() + " _" + o.getName() + "= new " + o.getName() + "(this)");
			ojOper.getBody().addToStatements("_" + o.getName() + ".setReturnInfo(context)");
			List<? extends INakedParameter> args = o.getArgumentParameters();
			for(INakedParameter arg:args){
				NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap(oc, arg);
				ojOper.getBody().addToStatements("_" + o.getName() + "." + argMap.setter() + "(" + argMap.umlName() + ")");
			}
			if(o instanceof INakedResponsibility){
				ojOperationClass.addToImports(ResponsibilityImplementor.RESPONSIBILITY_OBJECT);
				ojOperationClass.addToImplementedInterfaces(ResponsibilityImplementor.RESPONSIBILITY_OBJECT);
				ojOper.getBody().addToStatements("_" + o.getName() + ".execute()");
			}
			ojOper.getBody().addToStatements("return _" + o.getName());
			ojOper.setReturnType(behaviorClass);
			OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
			ojOperationClass.addToOperations(complete);
			if(o.getPostConditions().size() > 0){
				complete.getBody().addToStatements("evaluatePostConditions()");
				OJUtil.addFailedConstraints(complete);
			}
			OJAnnotatedField callBackListener = new OJAnnotatedField("callBackListener", map.callbackListenerPath());
			complete.getBody().addToLocals(callBackListener);
			callBackListener.setInitExp("getCallingProcessObject()");
			OJIfStatement ifNotNull = new OJIfStatement("callBackListener!=null", "callBackListener." + map.callbackOperName() + "(this)");
			complete.getBody().addToStatements(ifNotNull);
			addGetCallingProcessObject(ojOperationClass, map.callbackListenerPath());
		}
	}
	private boolean requiresOperationForInvocation(INakedBehavior behavior){
		return behavior.getContext() != null && !behavior.isClassifierBehavior();
	}
	private void invokeSimpleBehavior(INakedBehavior behavior,OJPathName activityClass,OJOperation javaMethod){
		if(behavior.getReturnParameter() != null){
			// remove "Return" statements
			javaMethod.getBody().removeFromStatements(javaMethod.getBody().getStatements().get(javaMethod.getBody().getStatements().size() - 1));
		}
		javaMethod.getBody().addToStatements(activityClass.getLast() + " _behavior=new " + activityClass.getLast() + "(this)");
		populateBehavior(behavior, javaMethod);
		javaMethod.getBody().addToStatements("_behavior.execute()");
		if(behavior.hasMultipleConcurrentResults()){
			// TODO such behaviours should always be called from an activity
			// that can actually retrieve the result
			javaMethod.getBody().addToStatements("return _behavior");
			javaMethod.setReturnType(activityClass);
		}else if(behavior.getReturnParameter() != null){
			javaMethod.getBody().addToStatements("return _behavior.get" + behavior.getReturnParameter().getMappingInfo().getJavaName().getCapped() + "()");
		}
	}
	private void implementSpecification(INakedBehavior behavior){
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		NameWrapper methodName = behavior.getSpecification() == null ? behavior.getMappingInfo().getJavaName() : behavior.getSpecification().getMappingInfo()
				.getJavaName();
		OJPathName ojBehavior = OJUtil.classifierPathname(behavior);
		// Method implemented by Octopus because behaviours without
		// specifications are given an emulated specification
		OJOperation javaMethod = OJUtil.findOperation(ojContext, methodName.toString());
		javaMethod.getOwner().addToImports(ojBehavior);
		if(behavior.isProcess()){
			// Leave preconditions in tact
			OJUtil.removeReturnStatement(javaMethod);
			ojContext.addToImports(ojBehavior);
			OJAnnotatedField behaviorField = new OJAnnotatedField("_behavior", ojBehavior);
			javaMethod.getBody().addToLocals(behaviorField);
			behaviorField.setInitExp("new " + ojBehavior.getLast() + "(this)");
			populateBehavior(behavior, javaMethod);
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(behavior.getEndToComposite().getOtherEnd());
			javaMethod.getBody().addToStatements(map.adder() + "(_behavior)");
			if(behavior.getSpecification() != null){
				javaMethod.getBody().addToStatements("_behavior.execute()");
			}
			javaMethod.getBody().addToStatements("return _behavior");
			javaMethod.setReturnType(ojBehavior);
		}else{
			invokeSimpleBehavior(behavior, ojBehavior, javaMethod);
		}
	}
	private void implementStartClassifierBehavior(INakedBehavior behavior){
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		ojContext.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(IActiveEntity.class));
		OJAnnotatedOperation start = new OJAnnotatedOperation("startClassifierBehavior");
		ojContext.addToOperations(start);
		OJPathName behaviorClass = OJUtil.classifierPathname(behavior);
		ojContext.addToImports(behaviorClass);
		OJAnnotatedField behaviorField = new OJAnnotatedField("_behavior", behaviorClass);
		start.getBody().addToLocals(behaviorField);
		behaviorField.setInitExp("new " + behaviorClass.getLast() + "(this)");
		populateBehavior(behavior, start);
		start.getBody().addToStatements("_behavior.execute()");
		start.getBody().addToStatements("set" + behavior.getMappingInfo().getJavaName().getCapped() + "(_behavior)");
		OJOperation addToOwner = OJUtil.findOperation(ojContext, "addToOwningObject");
		if(addToOwner != null){
			addToOwner.getBody().addToStatements("startClassifierBehavior()");
		}
	}
	private void populateBehavior(INakedBehavior parameterOwner,OJOperation javaMethod){
		for(INakedParameter p:parameterOwner.getArgumentParameters()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parameterOwner, p);
			javaMethod.getBody().addToStatements("_behavior." + map.setter() + "(" + map.umlName() + ")");
		}
		if(parameterOwner.getPreConditions().size() > 0){
			OJUtil.addFailedConstraints(javaMethod);
		}
	}
}
