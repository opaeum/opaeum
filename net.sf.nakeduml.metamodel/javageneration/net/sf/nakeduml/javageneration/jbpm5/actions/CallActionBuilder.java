package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.Caller;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.runtime.domain.UmlNodeInstance;

public class CallActionBuilder extends PotentialTaskActionBuilder<INakedCallAction> {
	private Caller delegate;

	public CallActionBuilder(IOclEngine engine, INakedCallAction node) {
		super(engine, node);
		delegate = new Caller(engine, node, new Jbpm5ObjectNodeExpressor(engine));
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation) {
		delegate.implementActionOn(operation, operation.getBody());
		if (BehaviorUtil.isTaskOrProcess(node) && node.isSynchronous()) {
		} else {
			OJTryStatement tryStatement = delegate.surroundWithCatchIfNecessary(operation, operation.getBody());
			if (tryStatement != null) {
				OJPathName umlNodeInstance = ReflectionUtil.getUtilInterface(UmlNodeInstance.class);
				operation.getOwner().addToImports(umlNodeInstance);
				OJAnnotatedField waitingNode = new OJAnnotatedField("waitingNode", umlNodeInstance);
				waitingNode.setInitExp("(" + umlNodeInstance.getLast() + ")context.getNodeInstance()");
				tryStatement.getCatchPart().addToLocals(waitingNode);
				implementExceptionPins(operation, tryStatement);
				implementExceptionHandlers(operation, tryStatement);
			}
		}
	}

	private void implementExceptionPins(OJAnnotatedOperation operation, OJTryStatement tryStatement) {
		List<INakedOutputPin> exceptionPins = node.getExceptionPins();
		for (INakedOutputPin e : exceptionPins) {
			OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
			operation.getOwner().addToImports(pathName);
			OJIfStatement statement = new OJIfStatement();
			statement.setCondition("e.isParameter(\"" + e.getLinkedTypedElement().getName() + "\")");
			tryStatement.getCatchPart().addToStatements(statement);
			if (e.getOutgoing().size() > 0) {
				INakedActivityEdge outgoing = e.getOutgoing().iterator().next();
				maybeContinueFlow(operation, statement.getThenPart(), outgoing);
			}
			tryStatement.getCatchPart().addToStatements(statement);
		}
	}

	private void implementExceptionHandlers(OJAnnotatedOperation operation, OJTryStatement tryStatement) {
		for (INakedExceptionHandler e : node.getHandlers()) {
			StringBuilder sb = new StringBuilder();
			Iterator<INakedClassifier> iter = e.getExceptionTypes().iterator();
			while (iter.hasNext()) {
				INakedClassifier type = iter.next();
				OJPathName pathName = OJUtil.classifierPathname(type);
				sb.append("e.getValue() instanceof ");
				sb.append(pathName.getLast());
				operation.getOwner().addToImports(pathName);
				if (iter.hasNext()) {
					sb.append("||");
				}
			}
			OJIfStatement statement = new OJIfStatement(sb.toString());
			tryStatement.getCatchPart().addToStatements(statement);
			if (e.getHandlerBody() != null) {
				flowTo(statement.getThenPart(), e.getHandlerBody());
			}
			// break flow on exception
			statement.getThenPart().addToStatements("return");
			tryStatement.getCatchPart().addToStatements(statement);
		}
	}
}
