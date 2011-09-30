package org.opeum.javageneration.basicjava;

import org.opeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.metamodel.actions.INakedRaiseExceptionAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class ExceptionRaiser extends SimpleNodeBuilder<INakedRaiseExceptionAction>{
	public ExceptionRaiser(OpeumLibrary oclEngine,INakedRaiseExceptionAction action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
		if(node.getActivity().isLongRunning()){
			block.addToStatements("propagateException(" + super.readPin(operation, block, node.getException()) + ")");
		}else{
			block.addToStatements("throw new ExceptionHolder(this,\"_raised\"," + super.readPin(operation, block, node.getException()) + ")");
		}
	}
}
