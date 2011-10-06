package org.opeum.runtime.jbpm;



import org.drools.definition.process.Connection;
import org.jbpm.workflow.instance.node.EndNodeInstance;
import org.opeum.runtime.domain.TransitionListener;
import org.opeum.runtime.domain.UmlNodeInstance;

public class Uml2EndStateInstance extends EndNodeInstance implements UmlNodeInstance {
	private static final long serialVersionUID = 4564655827620748188L;
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
