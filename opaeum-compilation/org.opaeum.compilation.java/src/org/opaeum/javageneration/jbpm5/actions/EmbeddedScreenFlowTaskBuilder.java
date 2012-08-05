package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opaeum.javageneration.util.OJUtil;

public class EmbeddedScreenFlowTaskBuilder extends AbstractCallActionBuilder<CallBehaviorAction>{
	public EmbeddedScreenFlowTaskBuilder(OJUtil ojUtil,CallBehaviorAction node){
		super( node,new EmbeddedScreenFlowTaskCaller(node, new Jbpm5ObjectNodeExpressor(ojUtil)));
		calledElementMap = ojUtil.buildOperationMap(node.getBehavior());
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
