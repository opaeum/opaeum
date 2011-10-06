package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class CallOperationActionBuilder extends AbstractCallActionBuilder<INakedCallOperationAction>{
	@Override
	public boolean isLongRunning(){
		return node instanceof INakedResponsibility;
	}
	public CallOperationActionBuilder(OpaeumLibrary oclEngine,INakedCallOperationAction node){
		super(oclEngine, node,new OperationCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
}
