package org.nakeduml.jbpm.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstance;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.node.StateNodeInstance;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Uml2StateInstance extends StateNodeInstance implements UmlNodeInstance{
	public void takeTransition(String name,TransitionListener listener){
		for(List<Connection> list:getStateNode().getOutgoingConnections().values()){
			for(Connection connection:list){
				if(connection.getTo().getName().equals(name)){
					triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
					if(listener != null){
						listener.onTransition();
					}
					((org.jbpm.workflow.instance.NodeInstanceContainer) getNodeInstanceContainer()).removeNodeInstance(this);
					triggerConnection(connection);
					return;
				}
			}
		}
	}
	public void forceTransitionTo(IProcessStep step){
		WorkflowProcessImpl process = (WorkflowProcessImpl) getProcessInstance().getProcess();
		NodeContainer nc = process;
		Node n = findNode(step, nc);
		UmlNodeInstance ni = this;
		NodeContainer commonContainer = getCommonContainer(n, ni);
		do{
			if(ni instanceof NodeInstanceContainer){
				NodeInstanceContainer currentContainer = (NodeInstanceContainer) ni;
				Collection<NodeInstance> nodeInstances = (Collection) currentContainer.getNodeInstances();
				for(NodeInstance child:nodeInstances){
					if(child instanceof UmlNodeInstance){
						((UmlNodeInstance) child).triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
					}else{
						child.cancel();
					}
					currentContainer.removeNodeInstance(child);
				}
			}
			ni.triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
			((NodeInstanceContainer) ni.getNodeInstanceContainer()).removeNodeInstance(ni);
			if(ni.getNodeInstanceContainer() instanceof UmlNodeInstance && !ni.getNode().getNodeContainer().equals(commonContainer)){
				ni = (UmlNodeInstance) ni.getNodeInstanceContainer();
			}else{
				break;
			}
		}while(true);
		NodeInstanceContainer nodeInstanceContainer = startNodeContainerInstance((NodeContainer) n.getNodeContainer(), ni,
				(NodeInstanceContainer) ni.getNodeInstanceContainer());
		NodeInstance nodeInstance = nodeInstanceContainer.getNodeInstance(n);
		nodeInstance.trigger(nodeInstanceContainer.getNodeInstances().iterator().next(), NodeImpl.CONNECTION_DEFAULT_TYPE);
	}
	private NodeInstanceContainer startNodeContainerInstance(NodeContainer n,NodeInstance initialNode,NodeInstanceContainer nodeInstanceContainer){
		if(n.equals(nodeInstanceContainer.getNodeContainer())){
			return nodeInstanceContainer;
		}else{
			Node node = (Node) n;
			NodeInstanceContainer parentInstance = startNodeContainerInstance((NodeContainer) node.getNodeContainer(), initialNode, nodeInstanceContainer);
			Collection<NodeInstance> nodeInstances = (Collection) parentInstance.getNodeInstances();
			for(NodeInstance nodeInstance:nodeInstances){
				if(nodeInstance.getNode().equals(node)){
					return (NodeInstanceContainer) nodeInstanceContainer;
				}
			}
			// will be in the initial container, trigger from the initialNode
			NodeInstance result = parentInstance.getNodeInstance(node);
			result.trigger(initialNode, NodeImpl.CONNECTION_DEFAULT_TYPE);
			return (NodeInstanceContainer) result;
		}
	}
	private NodeContainer getCommonContainer(Node toNode,UmlNodeInstance ni){
		while(toNode.getNodeContainer() instanceof Node){
			Node fromNode = (Node) ni.getNode();
			while(fromNode.getNodeContainer() instanceof Node){
				if(fromNode.getNodeContainer().equals(toNode.getNodeContainer())){
					return (NodeContainer) fromNode.getNodeContainer();
				}else{
					fromNode = (Node) fromNode.getNodeContainer();
				}
			}
			toNode = (Node) toNode.getNodeContainer();
		}
		return (NodeContainer) toNode.getNodeContainer();
	}
	private Node findNode(IProcessStep step,NodeContainer nc){
		Node n = null;
		Collection<Node> asList = (Collection) Arrays.asList(nc.getNodes());
		for(Node node:asList){
			if(node.getId() == step.getId()){
				n = node;
			}else if(node instanceof NodeContainer){
				n = findNode(step, (NodeContainer) node);
			}
			if(n != null){
				return n;
			}
		}
		return null;
	}
	@Override
	public void takeTransition(String targetNodeName){
		takeTransition(targetNodeName, null);
	
	}
	public void triggerEvent(String type) {
		super.triggerEvent(type);
	}

}
