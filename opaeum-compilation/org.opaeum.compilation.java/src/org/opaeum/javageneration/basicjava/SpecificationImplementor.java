package org.opaeum.javageneration.basicjava;

import java.util.List;

import nl.klasse.octopus.model.IOperation;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.AbstractBehaviorVisitor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.ReflectionUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.runtime.domain.IActiveEntity;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
/**
 * Implements the handshake between the caller of a behavior/operation and the host of the behavior/operation. As such, it implements both the invocation and the ensuing callback
 * @author ampie
 *
 */
public class SpecificationImplementor extends AbstractBehaviorVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior ob){
		if(BehaviorUtil.hasExecutionInstance(ob)){
			// Most likely long running
			if(ob.isClassifierBehavior()){
				implementStartClassifierBehavior(ob);
			}else{
				if(ob.isProcess() && ob.getSpecification() == null){
					implementCallbacksOnCompletionOrFailure(OJUtil.buildOperationMap(ob));
				}
				if(requiresOperationForInvocation(ob)){
					implementSpecification(ob);
				}
			}
		}
	}
	private void addSetReturnInfo(OJAnnotatedClass ojClass){
		OJAnnotatedOperation setReturnInfo = new OJAnnotatedOperation("setReturnInfo");
		ojClass.addToOperations(setReturnInfo);
		setReturnInfo.addParam("context", Jbpm5Util.getProcessContext());
		setReturnInfo.getBody().addToStatements("this.callingProcessInstanceId=context.getProcessInstance().getId()");
		setReturnInfo.getBody().addToStatements(
				"this.callingNodeInstanceUniqueId=((" + Jbpm5Util.getNodeInstance().getLast() + ")context.getNodeInstance()).getUniqueId()");
		OJUtil.addPersistentProperty(ojClass, "callingNodeInstanceUniqueId", new OJPathName("String"), true);
		ojClass.addToImports(Jbpm5Util.getNodeInstance());
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedBehavioredClassifier c){
		List<IOperation> operations = c.getOperations();
		for(IOperation o:operations){
			if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
				implementCallbacksOnCompletionOrFailure(OJUtil.buildOperationMap((IParameterOwner) o));
			}
		}
	}
	private void implementCallbacksOnCompletionOrFailure(NakedOperationMap map){
		if(BehaviorUtil.hasExecutionInstance(map.getParameterOwner())){
			OJAnnotatedClass ojOperationClass = (OJAnnotatedClass) javaModel.findClass(map.messageStructurePath());
			OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
			ojOperationClass.addToOperations(complete);
			if(map.getParameterOwner().getPostConditions().size() > 0){
				complete.getBody().addToStatements("evaluatePostConditions()");
				OJUtil.addFailedConstraints(complete);
			}
			if(map.getParameterOwner() instanceof INakedBehavior){
				complete.getBody().addToStatements("getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED)");
			}
			OJAnnotatedField currentException = OJUtil.addTransientProperty(ojOperationClass, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"),true);
			currentException.setVisibility(OJVisibilityKind.PROTECTED);
			if(map.getParameterOwner().isLongRunning()){
				Jbpm5Util.implementRelationshipWithProcess(ojOperationClass, true, "callingProcess");
				addSetReturnInfo(ojOperationClass);
				OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
				complete.getBody().addToLocals(callBackListener);
				callBackListener.setInitExp("getCallingProcessObject()");
				OJIfStatement ifNotNull = new OJIfStatement("callbackListener!=null", "callbackListener." + map.callbackOperName()
						+ "(getCallingNodeInstanceUniqueId(),this)");
				complete.getBody().addToStatements(ifNotNull);
				addGetCallingProcessObject(ojOperationClass, map.callbackListenerPath());
				propagateExceptions(map, ojOperationClass);
			}
		}
	}
	private void propagateExceptions(NakedOperationMap map,OJAnnotatedClass ojOperationClass){
		OJAnnotatedOperation propagateException = new OJAnnotatedOperation("propagateException");
		ojOperationClass.addToOperations(propagateException);
		propagateException.addParam("exception", new OJPathName("Object"));
		OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
		propagateException.getBody().addToLocals(callBackListener);
		callBackListener.setInitExp("getCallingProcessObject()");
		OJIfStatement ifNull = new OJIfStatement("callbackListener==null");
		propagateException.getBody().addToStatements(ifNull);
		OJIfStatement previousIf = ifNull;
		previousIf.setElsePart(new OJBlock());
		if(map.getParameterOwner() instanceof INakedOperation){
			INakedOperation oper = (INakedOperation) map.getParameterOwner();
			for(INakedClassifier ex:oper.getRaisedExceptions()){
				OJIfStatement ifInstance = new OJIfStatement("exception instanceof " + ex.getMappingInfo().getJavaName());
				previousIf.getElsePart().addToStatements(ifInstance);
				ifInstance.getThenPart().addToStatements("callbackListener." + map.exceptionOperName(ex) + "(getCallingNodeInstanceUniqueId(),exception,this)");
				previousIf = ifInstance;
				previousIf.setElsePart(new OJBlock());
			}
		}
		previousIf.getElsePart().addToStatements("callbackListener." + map.unhandledExceptionOperName() + "(getCallingNodeInstanceUniqueId(),exception, this)");
	}
	private boolean requiresOperationForInvocation(INakedBehavior behavior){
		return behavior.getContext() != null && !behavior.isClassifierBehavior();
	}
	private void invokeSimpleBehavior(INakedBehavior behavior,OJOperation javaMethod){
	}
	private void implementSpecification(INakedBehavior o){
		NakedOperationMap map = OJUtil.buildOperationMap(o.getSpecification() == null ? o : o.getSpecification());
		OJAnnotatedClass ojContext = findJavaClass(o.getContext());
		// Behaviours without
		// specifications are given an emulated specification
		List<OJPathName> parmTypes = o.isLongRunning() ? map.javaParamTypePathsWithReturnInfo() : map.javaParamTypePaths();
		OJOperation javaMethod = ojContext.findOperation(map.javaOperName(), parmTypes);
		if(o.isProcess()){
			implementProcessCreation(o, ojContext, javaMethod);
		}else{
			invokeSimpleBehavior(o, javaMethod);
		}
	}
	private void implementProcessCreation(INakedBehavior o,OJAnnotatedClass ojContext,OJOperation javaMethod){
		OJPathName ojBehavior = OJUtil.classifierPathname(o);
		javaMethod.getOwner().addToImports(ojBehavior);
		// Leave preconditions in tact
		NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(o.getEndToComposite().getOtherEnd());
		ojContext.addToImports(ojBehavior);
		OJAnnotatedOperation ojAnnotatedOperation = (OJAnnotatedOperation) javaMethod;
		ojAnnotatedOperation.initializeResultVariable("new " + ojBehavior.getLast() + "(this)");
		ojAnnotatedOperation.getResultVariable().setType(ojBehavior);
		javaMethod.getBody().addToStatements("this." + featureMap.adder() + "(result)");
		javaMethod.setReturnType(ojBehavior);
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
		NakedStructuralFeatureMap otherMap = OJUtil.buildStructuralFeatureMap(behavior.getEndToComposite().getOtherEnd());
		start.getBody().addToStatements(otherMap.setter() + "(_behavior)");
	}
	private void populateBehavior(INakedBehavior parameterOwner,OJOperation javaMethod){
		for(INakedParameter p:parameterOwner.getArgumentParameters()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(parameterOwner, p);
			javaMethod.getBody().addToStatements("_behavior." + map.setter() + "(" + map.fieldname() + ")");
		}
		if(parameterOwner.getPreConditions().size() > 0){
			OJUtil.addFailedConstraints(javaMethod);
		}
	}
}
