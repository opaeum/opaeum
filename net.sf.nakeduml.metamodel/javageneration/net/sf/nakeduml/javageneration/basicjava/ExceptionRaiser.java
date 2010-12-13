package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedRaiseExceptionAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ExceptionRaiser extends SimpleNodeBuilder<INakedRaiseExceptionAction> {

	public ExceptionRaiser(IOclEngine oclEngine, INakedRaiseExceptionAction action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		operation.getOwner().addToImports(BpmUtil.getExceptionHolder());
		block.addToStatements("throw new ExceptionHolder(this,\"_raised\"," + super.buildPinExpression(operation,block, node.getException()) + ")");
	}
}
