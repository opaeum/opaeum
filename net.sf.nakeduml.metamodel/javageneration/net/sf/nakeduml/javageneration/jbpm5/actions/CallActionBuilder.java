package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.Caller;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

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
			//1. Setup variables for task or sub-process call
		} else {
			OJTryStatement tryStatement = delegate.surroundWithCatchIfNecessary(operation, operation.getBody());
			if (tryStatement != null) {
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
		}
	}


}
