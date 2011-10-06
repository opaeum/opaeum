package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opeum.metamodel.actions.INakedCallOperationAction;
import org.opeum.metamodel.bpm.INakedResponsibility;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class CallOperationActionBuilder extends AbstractCallActionBuilder<INakedCallOperationAction>{
	@Override
	public boolean isLongRunning(){
		return node instanceof INakedResponsibility;
	}
	public CallOperationActionBuilder(OpeumLibrary oclEngine,INakedCallOperationAction node){
		super(oclEngine, node,new OperationCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
}
