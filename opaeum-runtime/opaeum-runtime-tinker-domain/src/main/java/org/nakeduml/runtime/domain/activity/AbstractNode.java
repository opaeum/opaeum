package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.AbstractPipe;

public abstract class AbstractNode extends AbstractPipe<ControlToken, Boolean> {

	public static final String CONTROL_TOKEN = "controlToken_";
	protected Vertex vertex;
	protected NodeStat nodeStat;

	public AbstractNode() {
		super();
	}

	public AbstractNode(Vertex vertex) {
		super();
		this.vertex = vertex;
		this.nodeStat = new NodeStat(vertex);
	}
	
	public AbstractNode(boolean persist, String name) {
		super();
		this.vertex = GraphDb.getDb().addVertex(null);
		nodeStat = new NodeStat(vertex, true);
		this.vertex.setProperty("name", name);
	}

	public Vertex getVertex() {
		return vertex;
	}

	protected abstract List<? extends AbstractControlFlowEdge> getInControlFlows();

	protected abstract List<? extends AbstractControlFlowEdge> getOutControlFlows();

	protected abstract Boolean processNextStart() throws NoSuchElementException;

	public NodeStatus getNodeStatus() {
		return (NodeStatus) org.util.TinkerUtil.convertEnumFromPersistence(NodeStatus.class, (String) this.vertex.getProperty("nodeStatus"));
	}

	public void setNodeStatus(NodeStatus nodeStatus) {
		this.vertex.setProperty("nodeStatus", org.util.TinkerUtil.convertEnumForPersistence(nodeStatus));
	}

	public void addIncomingControlToken(ControlToken controlToken) {
		// Remove edge to previous node
		if (controlToken.getVertex().getInEdges().iterator().hasNext()) {
			GraphDb.getDb().removeEdge(controlToken.getVertex().getInEdges().iterator().next());
			if (controlToken.getVertex().getInEdges().iterator().hasNext()) {
				throw new IllegalStateException("ControlToken can not have more than one edge!");
			}
		}
		// Multiple control tokens from the same incoming edge is merged
		if (!this.vertex.getOutEdges(CONTROL_TOKEN + controlToken.getEdgeName()).iterator().hasNext()) {
			Edge edge = GraphDb.getDb().addEdge(null, this.vertex, controlToken.getVertex(), CONTROL_TOKEN + controlToken.getEdgeName());
			edge.setProperty("outClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
		}
	}

	public void addOutgoingControlToken(ControlToken controlToken) {
		Edge edge = GraphDb.getDb().addEdge(null, this.vertex, controlToken.getVertex(), CONTROL_TOKEN + controlToken.getEdgeName());
		edge.setProperty("outClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
	}

	public List<ControlToken> getInControlTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (AbstractControlFlowEdge flow : getInControlFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(CONTROL_TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}

	protected List<ControlToken> getInControlTokens(String inFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (AbstractControlFlowEdge flow : getInControlFlows()) {
			if (inFlowName.equals(flow.getName())) {
				Iterable<Edge> iter = this.vertex.getOutEdges(CONTROL_TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	public List<ControlToken> getOutControlTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (AbstractControlFlowEdge flow : getOutControlFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(CONTROL_TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}

	protected List<ControlToken> getOutControlTokens(String outFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (AbstractControlFlowEdge flow : getOutControlFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(CONTROL_TOKEN + flow.getName());
				for (Edge edge : iter) {
					ControlToken e = new ControlToken(edge.getInVertex());
					result.add(e);
				}
			}
		}
		return result;
	}

	public NodeStat getNodeStat() {
		return nodeStat;
	}

	public String getName() {
		return (String) this.vertex.getProperty("name");
	}
	
	public boolean isEnabled() {
		return getNodeStatus()==NodeStatus.ENABLED;
	}

	public boolean isActive() {
		return getNodeStatus()==NodeStatus.ACTIVE;
	}

	public boolean isComplete() {
		return getNodeStatus()==NodeStatus.COMPLETE;
	}

	public boolean isInActive() {
		return getNodeStatus()==NodeStatus.INACTIVE;
	}

}
