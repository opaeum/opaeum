package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.metamodel.actions.INakedRaiseExceptionAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ExceptionRaiser extends SimpleNodeBuilder<INakedRaiseExceptionAction> {

	public ExceptionRaiser(NakedUmlLibrary oclEngine, INakedRaiseExceptionAction action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
		block.addToStatements("throw new ExceptionHolder(this,\"_raised\"," + super.readPin(operation,block, node.getException()) + ")");
	}
}
