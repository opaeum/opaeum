package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.CallOperationAction;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class CallOperationActionBuilder extends AbstractCallActionBuilder<CallOperationAction>{
	@Override
	public boolean isLongRunning(){
		return EmfBehaviorUtil.isResponsibility(node.getOperation());
	}
	public CallOperationActionBuilder(OpaeumLibrary oclEngine,CallOperationAction node){
		super(oclEngine, node,new OperationCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
		calledElementMap = OJUtil.buildOperationMap(node.getOperation());
	}
}
