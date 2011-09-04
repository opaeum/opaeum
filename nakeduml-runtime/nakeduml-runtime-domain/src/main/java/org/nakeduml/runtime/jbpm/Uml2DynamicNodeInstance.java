package org.nakeduml.runtime.jbpm;

import org.drools.definition.process.Connection;
import org.drools.definition.process.Node;
import org.drools.runtime.process.NodeInstance;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.instance.node.DynamicNodeInstance;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Uml2DynamicNodeInstance extends DynamicNodeInstance implements UmlNodeInstance{
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
	@Override
	public void nodeInstanceCompleted(org.jbpm.workflow.instance.NodeInstance nodeInstance,String outType){
		if (getNodeInstances(false).isEmpty()) {
    		triggerCompleted(NodeImpl.CONNECTION_DEFAULT_TYPE);
    	}
	}
	@Override
	public void internalTrigger(NodeInstance from,String type){
		super.internalTrigger(from, type);
		for (Node node: getCompositeNode().getNodes()) {
			if ("start".equals(node.getName())) {
    			NodeInstance nodeInstance = getNodeInstance(node);
                ((org.jbpm.workflow.instance.NodeInstance) nodeInstance)
                	.trigger(null, null);
    		}
		}
	}
}
