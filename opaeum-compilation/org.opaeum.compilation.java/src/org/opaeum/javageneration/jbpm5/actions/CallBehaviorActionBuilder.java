package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.javageneration.basicjava.simpleactions.BehaviorCaller;
import org.opaeum.javageneration.util.OJUtil;

public class CallBehaviorActionBuilder extends AbstractCallActionBuilder<CallBehaviorAction>{
	public CallBehaviorActionBuilder(OJUtil ojUtil,CallBehaviorAction node){
		super(node, new BehaviorCaller(node, new Jbpm5ObjectNodeExpressor(ojUtil)));
		calledElementMap = ojUtil.buildOperationMap(node.getBehavior());
	}
}
