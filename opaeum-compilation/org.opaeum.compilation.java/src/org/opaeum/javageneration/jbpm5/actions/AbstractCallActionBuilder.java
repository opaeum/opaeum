package org.opaeum.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractCallActionBuilder<T extends INakedCallAction> extends PotentialTaskActionBuilder<T>{
	private AbstractCaller<T> delegate;
	public AbstractCallActionBuilder(OpaeumLibrary l,T node,AbstractCaller<T> behaviorCaller){
		super(l, node);
		this.delegate = behaviorCaller;
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		if(node.isLongRunning() && node.isSynchronous()){
		}else{
			OJTryStatement tryStatement = delegate.surroundWithCatchIfNecessary(operation, operation.getBody());
			if(tryStatement != null){
				owner.addToImports(AbstractEventConsumptionImplementor.UML_NODE_INSTANCE);
				OJAnnotatedField waitingNode = new OJAnnotatedField("waitingNode", AbstractEventConsumptionImplementor.UML_NODE_INSTANCE);
				waitingNode.setInitExp("(" + AbstractEventConsumptionImplementor.UML_NODE_INSTANCE.getLast() + ")context.getNodeInstance()");
				tryStatement.getCatchPart().addToLocals(waitingNode);
				implementExceptionPins(operation, tryStatement);
				implementExceptionHandlers(operation, tryStatement);
			}
		}
	}
	private void implementCompleteMethod(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		String completeMethodName = null;
		INakedMessageStructure message = null;
		NakedOperationMap map = OJUtil.buildOperationMap(node.getCalledElement());
		activityClass.addToImplementedInterfaces(map.callbackListenerPath());
		completeMethodName = map.callbackOperName();
		message = node.getMessageStructure();
		implementCallbackOnComplete(activityClass, completeMethodName, message);
	}
	@Override
	public void implementCallbackMethods(OJClass owner){
		NakedOperationMap map = OJUtil.buildOperationMap(node.getCalledElement());
		implementCompleteMethod(owner);
		implementExceptionPins(owner, map);
		implementExceptionHandlers(owner, map);
		owner.getImplementedInterfaces().add(map.callbackListenerPath());
	}
	@Override
	public boolean isLongRunning(){
		return node.getCalledElement().isLongRunning();
	}
	private void implementExceptionPins(OJClassifier owner,NakedOperationMap map){
		if(node.getCalledElement().getExceptionParameters().size() > 0){
			Set<INakedParameter> exceptionParameters = new HashSet<INakedParameter>(node.getCalledElement().getExceptionParameters());
			for(INakedOutputPin p:node.getExceptionPins()){
				exceptionParameters.remove(p.getLinkedTypedElement());
				OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, (INakedParameter) p.getLinkedTypedElement(), false);
				if(p.getOutgoing().size() > 0){
					OJIfStatement ifAtNode = buildIfAtNode(onException);
					INakedActivityEdge outgoing = p.getOutgoing().iterator().next();
					ifAtNode.getThenPart().addToStatements("this.processDirty=true");
					flowTo(ifAtNode.getThenPart(), outgoing.getEffectiveTarget());
				}
			}
			for(INakedParameter ex:exceptionParameters){
				OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, ex, false);
				NakedStructuralFeatureMap exceptionMap = OJUtil.buildStructuralFeatureMap(node.getCalledElement().getContext(), ex);
				OJIfStatement ifAtNode = buildIfAtNode(onException);
				ifAtNode.getThenPart().addToStatements("propagateException(failedProcess." + exceptionMap.getter() + "())");
			}
		}
	}
	protected Collection<INakedClassifier> getRaisedExceptions(){
		Collection<INakedClassifier> exceptionParameters = node.getCalledElement() instanceof INakedOperation ? ((INakedOperation) node.getCalledElement())
				.getRaisedExceptions() : new HashSet<INakedClassifier>();
		return exceptionParameters;
	}
	private void implementExceptionPins(OJAnnotatedOperation operation,OJTryStatement tryStatement){
		List<INakedOutputPin> exceptionPins = node.getExceptionPins();
		for(INakedOutputPin e:exceptionPins){
			OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
			operation.getOwner().addToImports(pathName);
			OJIfStatement statement = new OJIfStatement();
			statement.setCondition("e.isParameter(\"" + e.getLinkedTypedElement().getName() + "\")");
			tryStatement.getCatchPart().addToStatements(statement);
			if(e.getOutgoing().size() > 0){
				INakedActivityEdge outgoing = e.getOutgoing().iterator().next();
				flowTo(statement.getThenPart(), outgoing.getEffectiveTarget());
			}
			tryStatement.getCatchPart().addToStatements(statement);
		}
	}
	private void implementExceptionHandlers(OJAnnotatedOperation operation,OJTryStatement tryStatement){
		for(INakedExceptionHandler e:node.getHandlers()){
			OJBlock catchPart = tryStatement.getCatchPart();
			implementHandler(operation, e, catchPart, "e.getValue()");
			catchPart.addToStatements("propagateException(exception)");
		}
	}
}