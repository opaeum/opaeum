package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.AbstractBehaviorCaller;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.BehaviorCaller;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class CallBehaviorActionBuilder extends Jbpm5ActionBuilder<INakedCallBehaviorAction>{
	private AbstractBehaviorCaller delegate;
	public CallBehaviorActionBuilder(NakedUmlLibrary engine,INakedCallBehaviorAction node){
		super(engine, node);
		delegate = new BehaviorCaller(engine, node, new Jbpm5ObjectNodeExpressor(engine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
		if(node.isLongRunning() && node.isSynchronous()){
		}else{
			OJTryStatement tryStatement = delegate.surroundWithCatchIfNecessary(operation, operation.getBody());
			if(tryStatement != null){
				operation.getOwner().addToImports(AbstractEventHandlerInserter.UML_NODE_INSTANCE);
				OJAnnotatedField waitingNode = new OJAnnotatedField("waitingNode", AbstractEventHandlerInserter.UML_NODE_INSTANCE);
				waitingNode.setInitExp("(" + AbstractEventHandlerInserter.UML_NODE_INSTANCE.getLast() + ")context.getNodeInstance()");
				tryStatement.getCatchPart().addToLocals(waitingNode);
				implementExceptionPins(operation, tryStatement);
				implementExceptionHandlers(operation, tryStatement);
			}
		}
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
			StringBuilder sb = new StringBuilder();
			Iterator<INakedClassifier> iter = e.getExceptionTypes().iterator();
			while(iter.hasNext()){
				INakedClassifier type = iter.next();
				OJPathName pathName = OJUtil.classifierPathname(type);
				sb.append("e.getValue() instanceof ");
				sb.append(pathName.getLast());
				operation.getOwner().addToImports(pathName);
				if(iter.hasNext()){
					sb.append("||");
				}
			}
			OJIfStatement statement = new OJIfStatement(sb.toString());
			tryStatement.getCatchPart().addToStatements(statement);
			if(e.getHandlerBody() != null){
				flowTo(statement.getThenPart(), e.getHandlerBody());
			}
			// break flow on exception
			statement.getThenPart().addToStatements("return");
			tryStatement.getCatchPart().addToStatements(statement);
		}
	}

}
