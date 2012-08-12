package org.opaeum.javageneration.bpm.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.AbstractCaller;
import org.opaeum.javageneration.bpm.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.bpm.BpmUtil;

public abstract class AbstractCallActionBuilder<T extends CallAction> extends PotentialTaskActionBuilder<T>{
	private AbstractCaller<T> delegate;
	protected OperationMap calledElementMap;
	public AbstractCallActionBuilder(T node,AbstractCaller<T> behaviorCaller){
		super(behaviorCaller.getOjUtil(), node);
		this.delegate = behaviorCaller;
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		if(calledElementMap.isLongRunning() && node.isSynchronous()){
		}else{
			OJTryStatement tryStatement = delegate.surroundWithCatchIfNecessary(operation, operation.getBody());
			if(tryStatement != null){
				implementExceptionPins(operation, tryStatement);
				implementExceptionHandlers(operation, tryStatement);
			}
		}
	}
	private void implementCompleteMethod(OJClass activityClass){
		String completeMethodName = null;
		activityClass.addToImplementedInterfaces(calledElementMap.callbackListenerPath());
		completeMethodName = calledElementMap.callbackOperName();
		Classifier message = getLibrary().getMessageStructure(node);
		implementCallbackOnComplete(activityClass, completeMethodName, message);
	}
	@Override
	public void implementCallbackMethods(OJClass owner){
		implementCompleteMethod(owner);
		implementExceptionPins(owner, calledElementMap);
		implementExceptionHandlers(owner, calledElementMap);
		owner.getImplementedInterfaces().add(calledElementMap.callbackListenerPath());
	}
	@Override
	public boolean isLongRunning(){
		return calledElementMap.isLongRunning();
	}
	private void implementExceptionPins(OJClassifier owner,OperationMap map){
		if(map.getExceptionParameters().size() > 0){
			Set<Parameter> exceptionParameters = new HashSet<Parameter>(map.getExceptionParameters());
			for(OutputPin p:EmfActionUtil.getExceptionPins( node)){
				Parameter parameter = (Parameter) EmfActionUtil.getLinkedTypedElement( p);
				exceptionParameters.remove(parameter);
				OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, parameter, false);
				if(p.getOutgoings().size() > 0){
					OJIfStatement ifAtNode = buildIfAtNode(onException);
					ActivityEdge outgoing = p.getOutgoings().iterator().next();
					ifAtNode.getThenPart().addToStatements("this.processDirty=true");
					flowTo(ifAtNode.getThenPart(), EmfActivityUtil.getEffectiveTarget(outgoing));
				}
			}
			for(Parameter ex:exceptionParameters){
				OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, ex, false);
				StructuralFeatureMap exceptionMap = ojUtil.buildStructuralFeatureMap(ex);
				OJIfStatement ifAtNode = buildIfAtNode(onException);
				ifAtNode.getThenPart().addToStatements("propagateException(failedProcess." + exceptionMap.getter() + "())");
			}
		}
	}
	protected Collection<Type> getRaisedExceptions(){
		Collection<Type> exceptionParameters = node instanceof CallOperationAction ? ((CallOperationAction) node).getOperation()
				.getRaisedExceptions() : new HashSet<Type>();
		return exceptionParameters;
	}
	private void implementExceptionPins(OJAnnotatedOperation operation,OJTryStatement tryStatement){
		List<OutputPin> exceptionPins = EmfActionUtil.getExceptionPins( node);
		for(OutputPin e:exceptionPins){
			OJPathName pathName = ojUtil.classifierPathname((Classifier) e.getType());
			operation.getOwner().addToImports(pathName);
			OJIfStatement statement = new OJIfStatement();
			statement.setCondition("e.isParameter(\"" + EmfActionUtil.getLinkedTypedElement( e).getName() + "\")");
			tryStatement.getCatchPart().addToStatements(statement);
			if(e.getOutgoings().size() > 0){
				ActivityEdge outgoing = e.getOutgoings().iterator().next();
				flowTo(statement.getThenPart(), EmfActivityUtil.getEffectiveTarget(outgoing));
			}
			tryStatement.getCatchPart().addToStatements(statement);
		}
	}
	private void implementExceptionHandlers(OJAnnotatedOperation operation,OJTryStatement tryStatement){
		for(ExceptionHandler e:node.getHandlers()){
			OJBlock catchPart = tryStatement.getCatchPart();
			implementHandler(operation, e, catchPart, "e.getValue()");
			catchPart.addToStatements("propagateException(exception)");
		}
	}
}