package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ControlNode<IN extends Token, OUT extends Token> extends ActivityNode<IN, OUT> {

	public ControlNode() {
		super();
	}

	public ControlNode(boolean persist, String name) {
		super(persist, name);
	}

	public ControlNode(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract List<? extends ActivityEdge<? extends IN>> getInFlows();

	@Override
	protected boolean mayAcceptToken() {
		return true;
	}
	
	@Override
	protected boolean mayContinue() {
		return doAllIncomingFlowsHaveTokens();
	}	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<IN> getInTokens() {
		List<IN> result = new ArrayList<IN>();
		for (ActivityEdge<? extends IN> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				Token token;
				if (flow instanceof ControlFlow) {
					token = new ControlToken(edge.getInVertex());
				} else {
					token = contructInToken(edge);
				}
				result.add((IN) token);
			}
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<IN> getInTokens(String inFlowName) {
		List<IN> result = new ArrayList<IN>();
		for (ActivityEdge<? extends IN> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					Token token;
					if (flow instanceof ControlFlow) {
						token = new ControlToken(edge.getInVertex());
					} else {
						token = contructInToken(edge);
					}
					result.add((IN) token);
				}
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<OUT> getOutTokens() {
		List<OUT> result = new ArrayList<OUT>();
		for (ActivityEdge<OUT> flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				Token token;
				if (flow instanceof ControlFlow) {
					token = new ControlToken(edge.getInVertex());
				} else {
					token = contructOutToken(edge);
				}
				result.add((OUT) token);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OUT> getOutTokens(String outFlowName) {
		List<OUT> result = new ArrayList<OUT>();
		for (ActivityEdge<OUT> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					Token token;
					if (flow instanceof ControlFlow) {
						token = new ControlToken(edge.getInVertex());
					} else {
						token = contructOutToken(edge);
					}
					result.add((OUT) token);
				}
			}
		}
		return result;
	}

	protected abstract List<? extends ActivityEdge<OUT>> getOutFlows();

	@SuppressWarnings("unchecked")
	protected IN contructInToken(Edge edge) {
		try {
			Class<?> c = Class.forName((String) edge.getProperty("tokenClass"));
			return (IN) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected OUT contructOutToken(Edge edge) {
		try {
			Class<?> c = Class.forName((String) edge.getProperty("tokenClass"));
			return (OUT) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		for (IN token : getInTokens()) {
			// For each out flow add a token
			for (ActivityEdge<OUT> flow : getOutFlows()) {
				OUT duplicate = token.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			token.remove();
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<OUT> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}
		
		setNodeStatus(NodeStatus.COMPLETE);
		boolean result = true;
		for (Boolean b : flowResult) {
			if (!b) {
				result = false;
				break;
			}
		}
		return result;
	}

}
