package org.nakeduml.jbpm.domain;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.instance.node.CompositeContextNodeInstance;
import org.jbpm.workflow.instance.node.CompositeNodeInstance;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Uml2CompositeContextNodeInstance extends CompositeContextNodeInstance implements UmlNodeInstance{
	private static final long serialVersionUID = 796953697582738704L;
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
