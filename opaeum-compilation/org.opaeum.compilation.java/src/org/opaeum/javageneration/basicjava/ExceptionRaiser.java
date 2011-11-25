package org.opaeum.javageneration.basicjava;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.metamodel.actions.INakedRaiseExceptionAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ExceptionRaiser extends SimpleNodeBuilder<INakedRaiseExceptionAction>{
	public ExceptionRaiser(OpaeumLibrary oclEngine,INakedRaiseExceptionAction action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
		if(node.getActivity().isLongRunning()){
			block.addToStatements("propagateException(" + super.readPin(operation, block, node.getException()) + ")");
		}else if(node.getActivity().getActivityKind() == ActivityKind.COMPLEX_SYNCHRONOUS_METHOD){
			block.addToStatements("this." + Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "=" + super.readPin(operation, block, node.getException()) + ")");
		}else{
			block.addToStatements("throw new ExceptionHolder(this,\"_raised\"," + super.readPin(operation, block, node.getException()) + ")");
		}
	}
}
