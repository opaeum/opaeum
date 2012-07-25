package org.opaeum.javageneration.basicjava;

import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ExceptionRaiser extends SimpleNodeBuilder<RaiseExceptionAction>{
	public ExceptionRaiser(OpaeumLibrary oclEngine,RaiseExceptionAction action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
		if(EmfBehaviorUtil.isLongRunning(EmfActivityUtil.getContainingActivity(node))){
			block.addToStatements("propagateException(" + super.readPin(operation, block, node.getException()) + ")");
		}else if(EmfBehaviorUtil.isComplectSynchronousMethod(getContainingActivity())){
			block.addToStatements("this." + Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "=" + super.readPin(operation, block, node.getException()) + ")");
		}else{
			block.addToStatements("throw new ExceptionHolder(this,\"_raised\"," + super.readPin(operation, block, node.getException()) + ")");
		}
	}
}
