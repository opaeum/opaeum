package org.opaeum.javageneration.basicjava;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfBehaviorUtil;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.ReflectionUtil;
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
	public void visitBehavior(Behavior ob){
		if(EmfBehaviorUtil.hasExecutionInstance(ob)){
			// Most likely long running
			if(EmfBehaviorUtil.isClassifierBehavior( ob)){
				implementStartClassifierBehavior(ob);
			}else{
				if(EmfBehaviorUtil.isProcess( ob) && ob.getSpecification() == null){
					implementCallbacksOnCompletionOrFailure(ojUtil.buildOperationMap(ob));
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
	public void visitClassifier(BehavioredClassifier c){
		List<Operation> operations = c.getOperations();
		for(Operation o:operations){
			if(o.getOwner() == c || o.getOwner() instanceof Interface){
				implementCallbacksOnCompletionOrFailure(ojUtil.buildOperationMap( o));
			}
		}
	}
	private void implementCallbacksOnCompletionOrFailure(OperationMap map){
		if(map.hasMessageStructure()){
			OJAnnotatedClass ojOperationClass = (OJAnnotatedClass) javaModel.findClass(map.messageStructurePath());
			OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
			ojOperationClass.addToOperations(complete);
			if(map.getPostConditions().size() > 0){
				complete.getBody().addToStatements("evaluatePostConditions()");
				OJUtil.addFailedConstraints(complete);
			}
			if(map.getNamedElement() instanceof Behavior){
				complete.getBody().addToStatements("getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED)");
			}
			OJAnnotatedField currentException = OJUtil.addTransientProperty(ojOperationClass, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"),true);
			currentException.setVisibility(OJVisibilityKind.PROTECTED);
			if(map.isLongRunning()){
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
	private void propagateExceptions(OperationMap map,OJAnnotatedClass ojOperationClass){
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
		if(map.getNamedElement() instanceof Operation){
			Operation oper = (Operation) map.getNamedElement();
			for(Type ex:oper.getRaisedExceptions()){
				OJIfStatement ifInstance = new OJIfStatement("exception instanceof " + ex.getName());
				previousIf.getElsePart().addToStatements(ifInstance);
				ifInstance.getThenPart().addToStatements("callbackListener." + map.exceptionOperName(ex) + "(getCallingNodeInstanceUniqueId(),exception,this)");
				previousIf = ifInstance;
				previousIf.setElsePart(new OJBlock());
			}
		}
		previousIf.getElsePart().addToStatements("callbackListener." + map.unhandledExceptionOperName() + "(getCallingNodeInstanceUniqueId(),exception, this)");
	}
	private boolean requiresOperationForInvocation(Behavior behavior){
		return behavior.getContext() != null && !EmfBehaviorUtil.isClassifierBehavior( behavior);
	}
	private void invokeSimpleBehavior(Behavior behavior,OJOperation javaMethod){
	}
	private void implementSpecification(Behavior o){
		OperationMap map = ojUtil.buildOperationMap(o.getSpecification() == null ? o : o.getSpecification());
		OJAnnotatedClass ojContext = findJavaClass(o.getContext());
		// Behaviours without
		// specifications are given an emulated specification
		List<OJPathName> parmTypes = EmfBehaviorUtil.isLongRunning(o) ? map.javaParamTypePathsWithReturnInfo() : map.javaParamTypePaths();
		OJOperation javaMethod = ojContext.findOperation(map.javaOperName(), parmTypes);
		if(EmfBehaviorUtil.isProcess( o)){
			implementProcessCreation(o, ojContext, javaMethod);
		}else{
			invokeSimpleBehavior(o, javaMethod);
		}
	}
	private void implementProcessCreation(Behavior o,OJAnnotatedClass ojContext,OJOperation javaMethod){
		OJPathName ojBehavior = ojUtil.classifierPathname(o);
		javaMethod.getOwner().addToImports(ojBehavior);
		// Leave preconditions in tact
		StructuralFeatureMap featureMap = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite( o).getOtherEnd());
		ojContext.addToImports(ojBehavior);
		OJAnnotatedOperation ojAnnotatedOperation = (OJAnnotatedOperation) javaMethod;
		ojAnnotatedOperation.initializeResultVariable("new " + ojBehavior.getLast() + "(this)");
		ojAnnotatedOperation.getResultVariable().setType(ojBehavior);
		javaMethod.getBody().addToStatements("this." + featureMap.adder() + "(result)");
		javaMethod.setReturnType(ojBehavior);
	}
	private void implementStartClassifierBehavior(Behavior behavior){
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		ojContext.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(IActiveEntity.class));
		OJAnnotatedOperation start = new OJAnnotatedOperation("startClassifierBehavior");
		ojContext.addToOperations(start);
		OJPathName behaviorClass = ojUtil.classifierPathname(behavior);
		ojContext.addToImports(behaviorClass);
		OJAnnotatedField behaviorField = new OJAnnotatedField("_behavior", behaviorClass);
		start.getBody().addToLocals(behaviorField);
		behaviorField.setInitExp("new " + behaviorClass.getLast() + "(this)");
		populateBehavior(behavior, start);
		start.getBody().addToStatements("_behavior.execute()");
		StructuralFeatureMap otherMap = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite(behavior).getOtherEnd());
		start.getBody().addToStatements(otherMap.setter() + "(_behavior)");
	}
	private void populateBehavior(Behavior parameterOwner,OJOperation javaMethod){
		for(Parameter p:EmfBehaviorUtil.getArgumentParameters(parameterOwner)){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
			javaMethod.getBody().addToStatements("_behavior." + map.setter() + "(" + map.fieldname() + ")");
		}
		if(parameterOwner.getPreconditions().size() > 0){
			OJUtil.addFailedConstraints(javaMethod);
		}
	}
}
