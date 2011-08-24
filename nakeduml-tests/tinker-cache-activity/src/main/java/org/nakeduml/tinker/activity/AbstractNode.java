package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.AbstractPipe;

public abstract class AbstractNode extends AbstractPipe<ControlToken, Boolean> {

	public static final String CONTROL_TOKEN = "controlToken_";
	protected Vertex vertex;

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

	protected List<ControlToken> getInControlTokens() {
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

	protected List<ControlToken> getOutControlTokens() {
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

}
