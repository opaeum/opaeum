package org.nakeduml.runtime.domain.activity;

import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.AbstractPipe;

public abstract class ActivityNode<T extends Token> extends AbstractPipe<T, Boolean> {

	protected Vertex vertex;
	protected NodeStat nodeStat;

	public ActivityNode() {
		super();
	}

	public ActivityNode(Vertex vertex) {
		super();
		this.vertex = vertex;
		this.nodeStat = new NodeStat(vertex);
	}

	public ActivityNode(boolean persist, String name) {
		super();
		this.vertex = GraphDb.getDb().addVertex(null);
		nodeStat = new NodeStat(vertex, true);
		this.vertex.setProperty("name", name);
	}
	
	protected abstract boolean mayContinue();
	protected abstract boolean mayAcceptToken();
	protected abstract List<? extends ActivityEdge<T>> getInFlows();
	protected abstract List<? extends ActivityEdge<T>> getOutFlows();
	public abstract List<T> getInTokens(String inFlowName);
	public abstract List<T> getOutTokens();
	public abstract List<T> getOutTokens(String outFlowName);
	public abstract List<T> getInTokens();
	
	public Vertex getVertex() {
		return vertex;
	}

	protected void execute() {
		// Do nothing
	}

	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		// Persist incoming control tokens
		while (mayAcceptToken() && this.starts.hasNext()) {
			T token = this.starts.next();
			// This also removes the token from the source
			addIncomingToken(token);
		}
		if (mayContinue()) {
			return executeNode();
		} else {
			return false;
		}
	}

	protected abstract Boolean executeNode();

	public NodeStatus getNodeStatus() {
		return (NodeStatus) org.util.TinkerUtil.convertEnumFromPersistence(NodeStatus.class, (String) this.vertex.getProperty("nodeStatus"));
	}

	public void setNodeStatus(NodeStatus nodeStatus) {
		this.vertex.setProperty("nodeStatus", org.util.TinkerUtil.convertEnumForPersistence(nodeStatus));
	}

	protected void addIncomingToken(T token) {
		token.removeEdgeFromActivityNode();
		token.addEdgeToActivityNode(this);
	}	
	
	public void addOutgoingToken(T token) {
		Edge edge = GraphDb.getDb().addEdge(null, this.vertex, token.getVertex(), Token.TOKEN + token.getEdgeName());
		edge.setProperty("outClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
	}

	protected void removeIncomingControlTokens() {
		for (T token : getInTokens()) {
			if (token instanceof ControlToken) {
				GraphDb.getDb().removeVertex(token.getVertex());
			} else {
				throw new IllegalStateException("This must not be called for ObjectTokens");
			}
		}
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		for (ActivityEdge<T> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			if (iter.iterator().hasNext()) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public NodeStat getNodeStat() {
		return nodeStat;
	}

	public String getName() {
		return (String) this.vertex.getProperty("name");
	}

	public boolean isEnabled() {
		return getNodeStatus() == NodeStatus.ENABLED;
	}

	public boolean isActive() {
		return getNodeStatus() == NodeStatus.ACTIVE;
	}

	public boolean isComplete() {
		return getNodeStatus() == NodeStatus.COMPLETE;
	}

	public boolean isInActive() {
		return getNodeStatus() == NodeStatus.INACTIVE;
	}

	protected abstract BaseTinkerSoftDelete getContextObject();

}
