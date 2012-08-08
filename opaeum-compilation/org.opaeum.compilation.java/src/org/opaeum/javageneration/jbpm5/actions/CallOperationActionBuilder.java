package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.CallOperationAction;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opaeum.javageneration.util.OJUtil;

public class CallOperationActionBuilder extends AbstractCallActionBuilder<CallOperationAction>{
	@Override
	public boolean isLongRunning(){
		return EmfBehaviorUtil.isResponsibility(node.getOperation());
	}
	public CallOperationActionBuilder(OJUtil ojUtil,CallOperationAction node){
		super( node,new OperationCaller( node, new Jbpm5ObjectNodeExpressor(ojUtil)));
		calledElementMap = ojUtil.buildOperationMap(node.getOperation());
	}
}
