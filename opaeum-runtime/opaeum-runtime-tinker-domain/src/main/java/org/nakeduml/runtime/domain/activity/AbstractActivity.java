package org.nakeduml.runtime.domain.activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;
import org.opaeum.runtime.domain.ISignal;

public abstract class AbstractActivity extends BaseTinkerSoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7647066355373095288L;

	public Set<AbstractNode> getEnabledNodesWithMatchingTrigger(ISignal signal) {
		Set<AbstractNode> result = new HashSet<AbstractNode>();
		Set<AbstractNode> visited = new HashSet<AbstractNode>();
		walkActivity(result, visited, getInitialNode(), signal);
		return result;
	}
	
	public Set<AbstractNode> getNodesForStatus(NodeStatus... nodeStatuses) {
		Set<AbstractNode> result = new HashSet<AbstractNode>();
		Set<AbstractNode> visited = new HashSet<AbstractNode>();
		walkActivity(result, visited, getInitialNode(), nodeStatuses);
		return result;
	}

	public AbstractNode getNodeForName(String name) {
		Set<AbstractNode> result = new HashSet<AbstractNode>();
		Set<AbstractNode> visited = new HashSet<AbstractNode>();
		walkActivity(result, visited, getInitialNode(), name);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.iterator().next();
		}
	}

	public Set<AbstractNode> getActiveNodes() {
		return getNodesForStatus(NodeStatus.ACTIVE);
	}

	public Set<AbstractNode> getEnabledNodes() {
		return getNodesForStatus(NodeStatus.ENABLED);
	}

	public Set<AbstractNode> getInactiveNodes() {
		return getNodesForStatus(NodeStatus.INACTIVE);
	}

	public Set<AbstractNode> getCompletedNodes() {
		return getNodesForStatus(NodeStatus.COMPLETE);
	}

	protected abstract AbstractNode getInitialNode();

	private void walkActivity(Set<AbstractNode> result, Set<AbstractNode> visited, AbstractNode currentNode, ISignal signal) {
		if (currentNode.isEnabled() && currentNode instanceof AbstractAcceptEventAction && ((AbstractAcceptEventAction) currentNode).containsTriggerWithSignalType(signal.getClass())) {
			result.add(currentNode);
		}
		List<? extends AbstractControlFlowEdge> outgoing = currentNode.getOutControlFlows();
		for (AbstractControlFlowEdge outFlow : outgoing) {
			AbstractNode target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, signal);
			} else {
				continue;
			}
		}
	}

	private void walkActivity(Set<AbstractNode> result, Set<AbstractNode> visited, AbstractNode currentNode, NodeStatus... nodeStatuses) {
		for (NodeStatus nodeStatus : nodeStatuses) {
			if (currentNode.getNodeStatus() == nodeStatus) {
				result.add(currentNode);
				break;
			}
		}
		List<? extends AbstractControlFlowEdge> outgoing = currentNode.getOutControlFlows();
		for (AbstractControlFlowEdge outFlow : outgoing) {
			AbstractNode target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, nodeStatuses);
			} else {
				continue;
			}
		}
	}

	private void walkActivity(Set<AbstractNode> result, Set<AbstractNode> visited, AbstractNode currentNode, String name) {
		if (currentNode.getName().equals(name)) {
			result.add(currentNode);
			return;
		}
		List<? extends AbstractControlFlowEdge> outgoing = currentNode.getOutControlFlows();
		for (AbstractControlFlowEdge outFlow : outgoing) {
			AbstractNode target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, name);
			} else {
				continue;
			}
		}
	}

}
