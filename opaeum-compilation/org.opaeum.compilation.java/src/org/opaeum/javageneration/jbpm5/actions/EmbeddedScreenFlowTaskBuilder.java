package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedScreenFlowTaskBuilder extends AbstractCallActionBuilder<CallBehaviorAction>{
	public EmbeddedScreenFlowTaskBuilder(OpaeumLibrary oclEngine,CallBehaviorAction node){
		super(oclEngine, node,new EmbeddedScreenFlowTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine)));
		calledElementMap = OJUtil.buildOperationMap(node.getBehavior());
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
