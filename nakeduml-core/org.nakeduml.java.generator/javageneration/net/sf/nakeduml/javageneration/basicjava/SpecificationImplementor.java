package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.generated.OJIfStatementGEN;
import org.nakeduml.runtime.domain.IActiveEntity;

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
					implementCallbacksOnCompletionOrFailure(new NakedOperationMap(ob));
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
		OJUtil.addProperty(ojClass, "callingNodeInstanceUniqueId", new OJPathName("String"), true);
		ojClass.addToImports(Jbpm5Util.getNodeInstance());
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedBehavioredClassifier c){
		List<IOperation> operations = c.getOperations();
		for(IOperation o:operations){
			if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
				implementCallbacksOnCompletionOrFailure(new NakedOperationMap((IParameterOwner) o));
			}
		}
	}
	private void implementCallbacksOnCompletionOrFailure(NakedOperationMap map){
		if(map.getParameterOwner().isLongRunning()){
			OJAnnotatedClass ojOperationClass = (OJAnnotatedClass) javaModel.findClass(map.messageStructurePath());
			Jbpm5Util.implementRelationshipWithProcess(ojOperationClass, true, "callingProcess");
			addSetReturnInfo(ojOperationClass);
			OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
			ojOperationClass.addToOperations(complete);
			if(map.getParameterOwner().getPostConditions().size() > 0){
				complete.getBody().addToStatements("evaluatePostConditions()");
				OJUtil.addFailedConstraints(complete);
			}
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
	private void propagateExceptions(NakedOperationMap map,OJAnnotatedClass ojOperationClass){
		ojOperationClass.addToFields(new OJAnnotatedField(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object")));
		OJAnnotatedOperation propagateException = new OJAnnotatedOperation("propagateException");
		ojOperationClass.addToOperations(propagateException);
		propagateException.addParam("exception", new OJPathName("Object"));
		OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
		propagateException.getBody().addToLocals(callBackListener);
		callBackListener.setInitExp("getCallingProcessObject()");
		OJIfStatement ifNull = new OJIfStatement("callbackListener==null");
		propagateException.getBody().addToStatements(ifNull);
		OJIfStatement previousIf = ifNull;
		previousIf .setElsePart(new OJBlock());
		if(map.getParameterOwner() instanceof INakedOperation){
			INakedOperation oper = (INakedOperation) map.getParameterOwner();
			for(INakedClassifier ex:oper.getRaisedExceptions()){
				OJIfStatement ifInstance = new OJIfStatement("exception instanceof " + ex.getMappingInfo().getJavaName());
				previousIf.getElsePart().addToStatements(ifInstance);
				ifInstance.getThenPart().addToStatements("callbackListener." + map.exceptionOperName(ex) + "(getCallingNodeInstanceUniqueId(),exception,this)");
				previousIf = ifInstance;
				previousIf .setElsePart(new OJBlock());
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
		NakedOperationMap map = new NakedOperationMap(o.getSpecification() == null ? o : o.getSpecification());
		OJAnnotatedClass ojContext = findJavaClass(o.getContext());
		// Behaviours without
		// specifications are given an emulated specification
		List<OJPathName> parmTypes = o.isLongRunning()? map.javaParamTypePathsWithReturnInfo():map.javaParamTypePaths();
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
