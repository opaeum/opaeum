package org.opaeum.runtime.jbpm;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.instance.node.StateNodeInstance;
import org.opaeum.runtime.domain.TransitionListener;
import org.opaeum.runtime.domain.UmlNodeInstance;

public class Uml2StateInstance extends StateNodeInstance implements UmlNodeInstance{
	private static final long serialVersionUID = -7995202606349607806L;
	@Override
	public void triggerConnection(Connection arg0){
		super.triggerConnection(arg0);
	}
	public void transitionToNode(long id){
		UmlJbpmUtil.transitionFromNodeToNode(this, id, null, true);
	}
	public void triggerEvent(String type){
		super.triggerEvent(type);
	}
	@Override
	public void transitionToNode(long to,TransitionListener listener){
		UmlJbpmUtil.transitionFromNodeToNode(this, to, listener, true);
	}
	@Override
	public void flowToNode(String targetNodeName){
		UmlJbpmUtil.flowToNode(this, targetNodeName);
	}
}
