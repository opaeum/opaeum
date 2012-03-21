package org.nakeduml.runtime.domain.activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;
import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractActivity extends BaseTinkerSoftDelete {

	private static final long serialVersionUID = 7647066355373095288L;

	protected abstract List<? extends ActivityNode<?, ?>> getInitialNodes();

//	protected abstract List<? extends ActivityParameterNode<?>> getIncomingParameters();
//
//	protected abstract List<? extends ActivityParameterNode<?>> getOutgoingParameters();

	public Set<ActivityNode<? extends Token, ? extends Token>> getEnabledNodesWithMatchingTrigger(IEvent event) {
		Set<ActivityNode<? extends Token, ? extends Token>> result = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		Set<ActivityNode<? extends Token, ? extends Token>> visited = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		for (ActivityNode<? extends Token, ? extends Token> initNode : getInitialNodes()) {
			walkActivity(result, visited, initNode, event);
		}
		return result;
	}

	public Set<ActivityNode<? extends Token, ? extends Token>> getNodesForStatus(NodeStatus... nodeStatuses) {
		Set<ActivityNode<? extends Token, ? extends Token>> result = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		Set<ActivityNode<? extends Token, ? extends Token>> visited = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		for (ActivityNode<? extends Token, ? extends Token> initNode : getInitialNodes()) {
			walkActivity(result, visited, initNode, nodeStatuses);
		}
		return result;
	}

	public ActivityNode<? extends Token, ? extends Token> getNodeForName(String name) {
		Set<ActivityNode<? extends Token, ? extends Token>> result = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		Set<ActivityNode<? extends Token, ? extends Token>> visited = new HashSet<ActivityNode<? extends Token, ? extends Token>>();
		for (ActivityNode<? extends Token, ? extends Token> initNode : getInitialNodes()) {
			walkActivity(result, visited, initNode, name);
			if (!result.isEmpty()) {
				break;
			}
		}
		if (result.isEmpty()) {
			return null;
		} else {
			return result.iterator().next();
		}
	}

	public Set<ActivityNode<? extends Token, ? extends Token>> getActiveNodes() {
		return getNodesForStatus(NodeStatus.ACTIVE);
	}

	public Set<ActivityNode<? extends Token, ? extends Token>> getEnabledNodes() {
		return getNodesForStatus(NodeStatus.ENABLED);
	}

	public Set<ActivityNode<? extends Token, ? extends Token>> getInactiveNodes() {
		return getNodesForStatus(NodeStatus.INACTIVE);
	}

	public Set<ActivityNode<? extends Token, ? extends Token>> getCompletedNodes() {
		return getNodesForStatus(NodeStatus.COMPLETE);
	}

	private void walkActivity(Set<ActivityNode<? extends Token, ? extends Token>> result, Set<ActivityNode<? extends Token, ? extends Token>> visited,
			ActivityNode<? extends Token, ? extends Token> currentNode, IEvent event) {
		if (currentNode.isEnabled() && currentNode instanceof AcceptEventAction && ((AcceptEventAction) currentNode).containsTriggerForEvent(event)) {
			result.add(currentNode);
		}
		List<? extends ActivityEdge<? extends Token>> outgoing = currentNode.getOutFlows();
		for (ActivityEdge<? extends Token> outFlow : outgoing) {
			ActivityNode<? extends Token, ? extends Token> target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, event);
			} else {
				continue;
			}
		}
	}

	private void walkActivity(Set<ActivityNode<? extends Token, ? extends Token>> result, Set<ActivityNode<? extends Token, ? extends Token>> visited,
			ActivityNode<? extends Token, ? extends Token> currentNode, NodeStatus... nodeStatuses) {
		for (NodeStatus nodeStatus : nodeStatuses) {
			if (currentNode.getNodeStatus() == nodeStatus) {
				result.add(currentNode);
				break;
			}
		}
		List<? extends ActivityEdge<? extends Token>> outgoing = currentNode.getOutFlows();
		for (ActivityEdge<? extends Token> outFlow : outgoing) {
			ActivityNode<? extends Token, ? extends Token> target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, nodeStatuses);
			} else {
				continue;
			}
		}
	}

	private void walkActivity(Set<ActivityNode<? extends Token, ? extends Token>> result, Set<ActivityNode<? extends Token, ? extends Token>> visited,
			ActivityNode<? extends Token, ? extends Token> currentNode, String name) {
		if (currentNode.getName().equals(name)) {
			result.add(currentNode);
			return;
		}
		List<? extends ActivityEdge<? extends Token>> outgoing = currentNode.getOutFlows();
		for (ActivityEdge<? extends Token> outFlow : outgoing) {
			ActivityNode<? extends Token, ? extends Token> target = outFlow.getTarget();
			if (!visited.contains(target)) {
				walkActivity(result, visited, target, name);
			} else {
				continue;
			}
		}
		if (currentNode instanceof Action) {
			for (OutputPin<?> outputPin : ((Action) currentNode).getOutputPins()) {
				walkActivity(result, visited, outputPin, name);
			}
		} else if (currentNode instanceof InputPin) {
			InputPin<?> inputPin = (InputPin<?>) currentNode;
			walkActivity(result, visited, inputPin.getAction(), name);
		}
	}

	protected <T> void addEdgeToObject(T object, String parameterName) {
		Vertex v;
		if (object instanceof TinkerNode) {
			TinkerNode node = (TinkerNode) object;
			v = node.getVertex();
		} else if (object.getClass().isEnum()) {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", ((Enum<?>) object).name());
		} else {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", object);
		}
		Edge edge = GraphDb.getDb().addEdge(null, this.vertex, v, parameterName);
		edge.setProperty("inClass", object.getClass().getName());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getObject(String parameter) {
		Edge edge = this.vertex.getOutEdges(parameter).iterator().next();
		Class<?> c = getClassToInstantiate(edge);
		Vertex v = edge.getInVertex();
		T node = null;
		try {
			if (c.isEnum()) {
				Object value = v.getProperty("value");
				node = (T) Enum.valueOf((Class<? extends Enum>) c, (String) value);
			} else if (TinkerCompositionNode.class.isAssignableFrom(c)) {
				node = (T) c.getConstructor(Vertex.class).newInstance(v);
			} else {
				Object value = v.getProperty("value");
				node = (T) value;
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return node;
	}

	private Class<?> getClassToInstantiate(Edge edge) {
		try {
			return Class.forName((String) edge.getProperty("inClass"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
