package org.opaeum.javageneration.basicjava;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.bpm.AbstractBehaviorVisitor;
import org.opaeum.javageneration.bpm.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.ReflectionUtil;
import org.opaeum.runtime.domain.IActiveEntity;
import org.opaeum.runtime.domain.IProcessObjectBase;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class})
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
			if(EmfBehaviorUtil.isClassifierBehavior(ob)){
				implementStartClassifierBehavior(ob);
			}else{
				if((EmfBehaviorUtil.isStandaloneTask(ob) || EmfBehaviorUtil.isProcess(ob)) && ob.getSpecification() == null){
					implementCallbacksOnCompletionOrFailure(ojUtil.buildOperationMap(ob));
				}
				if(requiresOperationForInvocation(ob)){
					implementSpecification(ob);
				}
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(BehavioredClassifier c){
		implementIObserver(findJavaClass(c) , EmfTimeUtil.getTimeObservations(c), EmfTimeUtil.getDurationObservations(c));
		List<Operation> operations = c.getOperations();
		for(Operation o:operations){
			if(o.getOwner() == c || o.getOwner() instanceof Interface){
				implementCallbacksOnCompletionOrFailure(ojUtil.buildOperationMap(o));
			}
		}
	}
	private void implementCallbacksOnCompletionOrFailure(OperationMap map){
		if(map.hasMessageStructure()){
			OJAnnotatedClass ojOperationClass = (OJAnnotatedClass) javaModel.findClass(map.messageStructurePath());
			OJAnnotatedOperation complete = new OJAnnotatedOperation("complete");
			ojOperationClass.addToOperations(complete);
			if(map.getPostConditions().size() > 0){
				complete.getBody().addToStatements("evaluatePostconditions()");
				OJUtil.addFailedConstraints(complete);
			}
			OJAnnotatedField currentException = OJUtil.addTransientProperty(ojOperationClass, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD,
					new OJPathName("Object"), true);
			currentException.setVisibility(OJVisibilityKind.PROTECTED);
			if(map.isLongRunning()){
				OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
				complete.getBody().addToLocals(callBackListener);
				callBackListener.setInitExp("getCallingBehaviorExecution()");
				OJIfStatement ifNotNull = new OJIfStatement("callbackListener!=null", "callbackListener." + map.callbackOperName()
						+ "(getReturnInfo(),this)");
				complete.getBody().addToStatements(ifNotNull);
				addGetCallingProcessObject(ojOperationClass, map.callbackListenerPath());
				propagateExceptions(map, ojOperationClass);
			}
		}
	}
	protected OJAnnotatedOperation addGetCallingProcessObject(OJAnnotatedClass ojOperationClass,OJPathName type){
		// getCAllbackLister
		OJAnnotatedOperation getCallbackListener = new OJAnnotatedOperation("getCallingBehaviorExecution", type);
		ojOperationClass.addToOperations(getCallbackListener);
		addReturnInfo(ojOperationClass);
		getCallbackListener.initializeResultVariable("null");
		OJIfStatement processInstanceNotNull = new OJIfStatement("getReturnInfo()!=null ");
		getCallbackListener.getBody().addToStatements(processInstanceNotNull);
		processInstanceNotNull.getThenPart().addToStatements("result=(" + type.getLast() + ")getReturnInfo().getBehaviorExecution()");
		return getCallbackListener;
	}
	private void propagateExceptions(OperationMap map,OJAnnotatedClass ojOperationClass){
		OJAnnotatedOperation propagateException = new OJAnnotatedOperation("propagateException");
		ojOperationClass.addToOperations(propagateException);
		propagateException.addParam("exception", new OJPathName("Object"));
		OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
		propagateException.getBody().addToLocals(callBackListener);
		callBackListener.setInitExp("getCallingBehaviorExecution()");
		OJIfStatement ifNull = new OJIfStatement("callbackListener==null");
		propagateException.getBody().addToStatements(ifNull);
		OJIfStatement previousIf = ifNull;
		previousIf.setElsePart(new OJBlock());
		if(map.getNamedElement() instanceof Operation){
			Operation oper = (Operation) map.getNamedElement();
			for(Type ex:oper.getRaisedExceptions()){
				OJIfStatement ifInstance = new OJIfStatement("exception instanceof " + ex.getName());
				previousIf.getElsePart().addToStatements(ifInstance);
				ifInstance.getThenPart().addToStatements("callbackListener." + map.exceptionOperName(ex) + "(getReturnInfo(),exception,this)");
				previousIf = ifInstance;
				previousIf.setElsePart(new OJBlock());
			}
		}
		previousIf.getElsePart().addToStatements("callbackListener." + map.unhandledExceptionOperName() + "(getReturnInfo(),exception, this)");
	}
	private boolean requiresOperationForInvocation(Behavior behavior){
		return behavior.getContext() != null && !EmfBehaviorUtil.isClassifierBehavior(behavior);
	}
	private void invokeSimpleBehavior(Behavior behavior,OJOperation javaMethod){
	}
	private void implementSpecification(Behavior o){
		OperationMap map = ojUtil.buildOperationMap(o.getSpecification() == null ? o : o.getSpecification());
		OJAnnotatedClass ojContext = findJavaClass(o.getContext());
		if(ojContext != null){
			// Behaviours without
			// specifications are given an emulated specification
			List<OJPathName> parmTypes = EmfBehaviorUtil.isLongRunning(o) ? map.javaParamTypePathsWithReturnInfo() : map.javaParamTypePaths();
			OJAnnotatedOperation javaMethod = (OJAnnotatedOperation) ojContext.findOperation(map.javaOperName(), parmTypes);
//			if(javaMethod==null){
//				javaMethod = (OJAnnotatedOperation) ojContext.findOperation(map.javaOperName(), parmTypes);
//				System.err.println();
//			}
			if(EmfBehaviorUtil.isProcess(o)){
				implementProcessCreation(o, ojContext, javaMethod);
			}else if(EmfBehaviorUtil.isStandaloneTask(o)){
				implementTaskCreation(o, ojContext, javaMethod,map);
			}else{
				invokeSimpleBehavior(o, javaMethod);
			}
		}
	}
	private void implementTaskCreation(Behavior o,OJAnnotatedClass ojClass,OJAnnotatedOperation oper1, OperationMap operationMap){
		ojClass.addToImports("java.util.Arrays");
		oper1.getBody().addToStatements(0, new OJSimpleStatement("result.setRequest(new TaskRequest())"));
		OJAnnotatedOperation deepCopy = (OJAnnotatedOperation) oper1.getDeepCopy(operationMap.multiName());
		deepCopy.setStatic(true);
		deepCopy.initializeResultVariable("new " + ojUtil.classifierPathname(o).getLast() + "()");
		ojClass.addToImports(ojUtil.classifierPathname(getLibrary().getTaskRequest()));
		deepCopy.getBody().addToStatements(
				0,
				new OJSimpleStatement(
						"((TaskRequest)result.getRequest()).setPotentialOwners("+ojUtil.environmentPathname()+ ".INSTANCE.getCurrentPersistence().readAll("
								+ ojClass.getName() + ".class))"));
		deepCopy.getBody().removeFromStatements(deepCopy.getBody().getStatements().get(deepCopy.getBody().getStatements().size() - 1));// remove
																																																																		// event
		deepCopy.getBody().addToStatements(ojUtil.environmentPathname()+ ".INSTANCE.getCurrentPersistence().persist(result)"); // generator
		ojClass.addToOperations(deepCopy);
		oper1.getBody().addToStatements(1, new OJSimpleStatement("((TaskRequest)result.getRequest()).setPotentialOwners(Arrays.asList(this))"));
		oper1.initializeResultVariable("new " + ojUtil.classifierPathname(o).getLast() + "(this)");
		oper1.getResultVariable().setType(ojUtil.classifierPathname(o));
	}
	private void implementProcessCreation(Behavior o,OJAnnotatedClass ojContext,OJOperation javaMethod){
		OJPathName ojBehavior = ojUtil.classifierPathname(o);
		javaMethod.getOwner().addToImports(ojBehavior);
		// Leave preconditions in tact
		ojContext.addToImports(ojBehavior);
		OJAnnotatedOperation ojAnnotatedOperation = (OJAnnotatedOperation) javaMethod;
		ojAnnotatedOperation.initializeResultVariable("new " + ojBehavior.getLast() + "(this)");
		ojAnnotatedOperation.getResultVariable().setType(ojBehavior);
		javaMethod.setReturnType(ojBehavior);
	}
	private void implementStartClassifierBehavior(Behavior behavior){
		OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
		if(ojContext != null){
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
			PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite(behavior).getOtherEnd());
			start.getBody().addToStatements(otherMap.setter() + "(_behavior)");
			OJAnnotatedOperation getClassifierBehavior = new OJAnnotatedOperation("getClassifierBehavior", new OJPathName(
					IProcessObjectBase.class.getName()));
			ojContext.addToOperations(getClassifierBehavior);
			getClassifierBehavior.initializeResultVariable(ojUtil.buildStructuralFeatureMap(
					getLibrary().getEndToComposite(behavior).getOtherEnd()).getter()
					+ "()");
		}
	}
	private void populateBehavior(Behavior parameterOwner,OJOperation javaMethod){
		for(Parameter p:EmfBehaviorUtil.getArgumentParameters(parameterOwner)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
			javaMethod.getBody().addToStatements("_behavior." + map.setter() + "(" + map.fieldname() + ")");
		}
		if(parameterOwner.getPreconditions().size() > 0){
			OJUtil.addFailedConstraints(javaMethod);
		}
	}
}
