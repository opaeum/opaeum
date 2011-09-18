package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.OperationCaller;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

public class CallOperationActionBuilder extends AbstractCallActionBuilder<INakedCallOperationAction>{
	@Override
	public boolean isLongRunning(){
		return node instanceof INakedResponsibility;
	}
	public CallOperationActionBuilder(NakedUmlLibrary oclEngine,INakedCallOperationAction node){
		super(oclEngine, node,new OperationCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
	}
}
