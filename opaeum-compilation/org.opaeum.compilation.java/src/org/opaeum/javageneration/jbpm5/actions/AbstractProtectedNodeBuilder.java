package org.opaeum.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Type;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ExceptionRaisingMap;
import org.opaeum.javageneration.util.OJUtil;

public abstract class AbstractProtectedNodeBuilder<T extends Action> extends Jbpm5ActionBuilder<T>{
	static final String IF_TOKEN_FOUND = "ifTokenFound";
	protected StructuralFeatureMap callMap;
	public AbstractProtectedNodeBuilder(OJUtil util,T node,StructuralFeatureMap callMap){
		super(util, node);
		this.callMap = callMap;
	}
	protected void implementExceptionHandlers(OJClassifier owner,ExceptionRaisingMap map){
		Collection<Type> exceptions = getRaisedExceptions();
		for(ExceptionHandler p:node.getHandlers()){
			Collection<Classifier> exceptionTypes = p.getExceptionTypes();
			exceptions.removeAll(exceptionTypes);
			for(Classifier exception:exceptionTypes){
				OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, exception, true);
				onException.getBody().addToStatements(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "=exception");
				OJIfStatement ifAtNode = buildIfAtNode(onException);
				ifAtNode.getThenPart().addToStatements("this.processDirty=true");
				flowTo(ifAtNode.getThenPart(), p.getHandlerBody());
			}
		}
		for(Type ex:exceptions){
			OJAnnotatedOperation onException = findOrCreateExceptionListener(owner, map, ex, true);
			OJIfStatement ifAtNode = buildIfAtNode(onException);
			ifAtNode.getThenPart().addToStatements("propagateException(exception)");
			cancelAllPeers(ifAtNode.getThenPart());
		}
		OJAnnotatedOperation unhandledExceptionHandler = new OJAnnotatedOperation(map.unhandledExceptionOperName());
		owner.addToOperations(unhandledExceptionHandler);
		unhandledExceptionHandler.addParam("nodeInstanceUniqueId", new OJPathName("String"));
		unhandledExceptionHandler.addParam("exception", new OJPathName("Object"));
		unhandledExceptionHandler.addParam("failedProcess", map.messageStructurePath());
		unhandledExceptionHandler.getBody().addToStatements("propagateException(exception)");
		cancelAllPeers(unhandledExceptionHandler.getBody());
	}
	private void cancelAllPeers(OJBlock thenPart){
		if(callMap.isOne()){
			thenPart.addToStatements(callMap.getter() + "().cancel()");
		}else{
			OJForStatement forEachTask = new OJForStatement("workObject", callMap.javaBaseTypePath(), callMap.getter() + "()");
			thenPart.addToStatements(forEachTask);
			forEachTask.getBody().addToStatements("workObject.cancel()");
		}
	}
	protected abstract Collection<Type> getRaisedExceptions();
	protected OJAnnotatedOperation findOrCreateExceptionListener(OJClassifier owner,ExceptionRaisingMap map,NamedElement exceptionPArameter,boolean takesException){
		OJAnnotatedOperation onException = (OJAnnotatedOperation) ((OJClass) owner).getUniqueOperation(map.exceptionOperName(exceptionPArameter));
		if(onException == null){
			onException = new OJAnnotatedOperation(map.exceptionOperName(exceptionPArameter));
			owner.addToOperations(onException);
			onException.addParam("nodeInstanceUniqueId", new OJPathName("String"));
			if(takesException){
				onException.addParam("exception", new OJPathName("Object"));
			}
			onException.addParam("failedProcess", map.messageStructurePath());
			OJAnnotatedField waitingNode = new OJAnnotatedField("waitingNode", AbstractEventConsumptionImplementor.UML_NODE_INSTANCE);
			onException.getBody().addToLocals(waitingNode);
			OJIfStatement ifTokenFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
			onException.getBody().addToStatements(ifTokenFound);
			ifTokenFound.setName(IF_TOKEN_FOUND);
		}
		return onException;
	}
	protected OJIfStatement buildIfAtNode(OJAnnotatedOperation onException){
		OJIfStatement ifTokenFound = (OJIfStatement) onException.getBody().findStatementRecursive(IF_TOKEN_FOUND);
		String literalExpression = onException.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node);
		OJIfStatement ifAtNode = new OJIfStatement("waitingNode.getNodeId()==" + literalExpression + ".getId()");
		ifTokenFound.addToThenPart(ifAtNode);
		return ifAtNode;
	}
	protected void implementCallbackOnComplete(OJClass activityClass,String completeMethodName,Classifier message){
		OJAnnotatedOperation complete;
		complete = (OJAnnotatedOperation) activityClass.getUniqueOperation(completeMethodName);
		activityClass.addToImports(AbstractEventConsumptionImplementor.UML_NODE_INSTANCE);
		if(complete == null){
			complete = new OJAnnotatedOperation(completeMethodName);
			activityClass.addToOperations(complete);
			complete.getBody().addToLocals(new OJAnnotatedField("waitingNode", AbstractEventConsumptionImplementor.UML_NODE_INSTANCE));
			complete.addParam("nodeInstanceUniqueId", new OJPathName("String"));
			complete.addParam("completedWorkObject", ojUtil.classifierPathname(message));
		}
		OJIfStatement ifFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
		complete.getBody().addToStatements(ifFound);
		implementConditions(complete, ifFound.getThenPart(), node, false);
		if(callMap.isOne()){
			// continue with process
		}else{
			// continue with process only if all tasks are complete
			OJForStatement forEachTask = new OJForStatement("workObject", callMap.javaBaseTypePath(), callMap.getter() + "()");
			ifFound.getThenPart().addToStatements(forEachTask);
			forEachTask.getBody().addToStatements(new OJIfStatement("!workObject.isComplete()", "return"));
		}
		implementConditionalFlows(complete, ifFound.getThenPart());
	}
	protected void implementHandler(OJAnnotatedOperation operation,ExceptionHandler e,OJBlock catchPart,String exceptionExpression){
		catchPart.addToStatements(Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "=" + exceptionExpression);
		StringBuilder sb = new StringBuilder();
		Iterator<Classifier> iter = e.getExceptionTypes().iterator();
		while(iter.hasNext()){
			Classifier type = iter.next();
			OJPathName pathName = ojUtil.classifierPathname(type);
			sb.append(exceptionExpression + " instanceof ");
			sb.append(pathName.getLast());
			operation.getOwner().addToImports(pathName);
			if(iter.hasNext()){
				sb.append("||");
			}
		}
		OJIfStatement statement = new OJIfStatement(sb.toString());
		catchPart.addToStatements(statement);
		if(e.getHandlerBody() != null){
			flowTo(statement.getThenPart(), e.getHandlerBody());
		}
		// break flow on exception
		statement.getThenPart().addToStatements("return");
		// If no handler then propagate
		catchPart.addToStatements(statement);
	}
}