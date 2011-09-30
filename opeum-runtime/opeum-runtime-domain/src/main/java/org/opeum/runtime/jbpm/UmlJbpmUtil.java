package org.opeum.runtime.jbpm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.drools.definition.process.Connection;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.core.node.Split;
import org.jbpm.workflow.instance.NodeInstance;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.opeum.runtime.domain.TransitionListener;
import org.opeum.runtime.domain.UmlNodeInstance;

@SuppressWarnings({
	"unchecked","rawtypes"
})
public class UmlJbpmUtil{
	public static void transitionFromNodeToNode(UmlNodeInstance from,long id,TransitionListener effect,boolean isExternal){
		WorkflowProcessImpl process = (WorkflowProcessImpl) from.getProcessInstance().getProcess();
		NodeContainer nc = process;
		Node n = findNode(id, nc);
		UmlNodeInstance topmostSourceNodeInstance = getTopmostSourceNodeInstance(n, from);
		exitChildrenRecursively(topmostSourceNodeInstance);
		if(effect != null){
			effect.onTransition();
		}
		NodeInstanceContainer nic = (NodeInstanceContainer) topmostSourceNodeInstance.getNodeInstanceContainer();
		Node effectiveTarget = getTopmostTargetNode(nic, n);
		if(!effectiveTarget.equals(topmostSourceNodeInstance.getNode()) || isExternal){
			topmostSourceNodeInstance.triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
		}
		nic.removeNodeInstance(topmostSourceNodeInstance);
		enterRealTargetRecursively(n, nic, isExternal);
	}
	private static Split isOrthogonal(NodeInstanceContainer nic){
		List<Node> asList = new ArrayList(Arrays.asList(nic.getNodeContainer().getNodes()));
		for(Node node:asList){
			if(node instanceof Split){
				if(((Split) node).getType() == Split.TYPE_AND){
					return (Split) node;
				}
			}
		}
		return null;
	}
	private static void enterRealTargetRecursively(Node n,NodeInstanceContainer instanceContainer,boolean isExternal){
		Node nodeAtThisLevel = getNodeAtLevel(n, instanceContainer);
		Split split = isOrthogonal(instanceContainer);
		if(split != null){
			for(Connection connection:split.getOutgoingConnections(NodeImpl.CONNECTION_DEFAULT_TYPE)){
				if(!connection.getTo().equals(nodeAtThisLevel)){
					// Trigger orthogonal regions not participating in the transition
					instanceContainer.getNodeInstance(connection.getTo()).trigger(null, NodeImpl.CONNECTION_DEFAULT_TYPE);
				}
			}
		}
		UmlNodeInstance childNodeInstance = (UmlNodeInstance) instanceContainer.getNodeInstance(nodeAtThisLevel);
		if(nodeAtThisLevel.equals(n)){
			childNodeInstance.trigger(null, NodeImpl.CONNECTION_DEFAULT_TYPE);
		}else{
			if(isExternal){
				childNodeInstance.triggerEvent(ExtendedNodeImpl.EVENT_NODE_ENTER);
			}
			if(childNodeInstance instanceof NodeInstanceContainer){
				enterRealTargetRecursively(n, (NodeInstanceContainer) childNodeInstance, true);// just first internal counts
			}
		}
	}
	private static Node getNodeAtLevel(Node n,NodeInstanceContainer nodeInstance){
		if(n.getNodeContainer().equals(getNodeContainer(nodeInstance))){
			return n;
		}else{
			return getNodeAtLevel((Node) n.getNodeContainer(), nodeInstance);
		}
	}
	private static Node getTopmostTargetNode(NodeInstanceContainer nic,Node n){
		if(n.equals(getNodeContainer(nic))){
			// check for nodes at the same level first;
			return n;
		}else if(n.getNodeContainer().equals(getNodeContainer(nic))){
			return n;
		}else{
			return getTopmostTargetNode(nic, (Node) n.getNodeContainer());
		}
	}
	private static void exitChildrenRecursively(UmlNodeInstance from){
		if(from instanceof NodeInstanceContainer){
			NodeInstanceContainer currentContainer = (NodeInstanceContainer) from;
			Collection<NodeInstance> nodeInstances = (Collection) currentContainer.getNodeInstances();
			for(NodeInstance child:nodeInstances){
				if(child instanceof UmlNodeInstance){
					exitChildrenRecursively((UmlNodeInstance) child);
					from.triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
				}
				currentContainer.removeNodeInstance(child);
			}
		}
	}
	private static NodeContainer getNodeContainer(NodeInstanceContainer container){
		if(container instanceof WorkflowProcessInstance){
			return (NodeContainer) ((WorkflowProcessInstance) container).getProcess();
		}else{
			return (NodeContainer) ((NodeInstance) container).getNode();
		}
	}
	private static UmlNodeInstance getTopmostSourceNodeInstance(Node toNode,UmlNodeInstance fromNodeInstance){
		if(fromNodeInstance instanceof NodeInstanceContainer && couldContainInstancesOf((NodeInstanceContainer) fromNodeInstance, toNode)){
			// Check for direct containment first
			return fromNodeInstance;
		}else if(couldContainInstancesOf((NodeInstanceContainer) fromNodeInstance.getNodeInstanceContainer(), toNode)){
			// then Check potential for being siblings at either process or composite node level
			return fromNodeInstance;
		}else if(fromNodeInstance.getNodeInstanceContainer() instanceof UmlNodeInstance){
			// One level up
			return getTopmostSourceNodeInstance(toNode, (UmlNodeInstance) fromNodeInstance.getNodeInstanceContainer());
		}
		throw new IllegalStateException(fromNodeInstance.getNodeInstanceContainer().getClass().getName() + " not an instance of UmlNodeInstance");
	}
	private static boolean couldContainInstancesOf(NodeInstanceContainer c,Node toNode){
		boolean result = false;
		if(toNode.getNodeContainer().equals(getNodeContainer(c))){
			result = true;
		}else if(toNode.getNodeContainer() instanceof Node){
			result = couldContainInstancesOf(c, (Node) toNode.getNodeContainer());
		}
		return result;
	}
	private static Node findNode(long id,NodeContainer nc){
		Node n = null;
		Collection<Node> asList = (Collection) Arrays.asList(nc.getNodes());
		// Search Direct Children first
		for(Node node:asList){
			if(node.getId() == id){
				return node;
			}
		}
		// Now search descendants
		for(Node node:asList){
			if(node instanceof NodeContainer){
				n = findNode(id, (NodeContainer) node);
				if(n != null){
					return n;
				}
			}
		}
		return n;
	}
	public static void flowToNode(UmlNodeInstance fromNodeInstance,String name){
		Node node = (Node) fromNodeInstance.getNode();
		for(List<Connection> list:node.getOutgoingConnections().values()){
			for(Connection connection:list){
				if(connection.getTo().getName().equals(name)){
					fromNodeInstance.triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
					((org.jbpm.workflow.instance.NodeInstanceContainer) fromNodeInstance.getNodeInstanceContainer()).removeNodeInstance(fromNodeInstance);
					fromNodeInstance.triggerConnection(connection);
					return;
				}
			}
		}
	}
}
