package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNode<IN extends Token, OUT extends Token> extends ControlNode<IN, OUT> {

	public JoinNode() {
		super();
	}

	public JoinNode(Vertex vertex) {
		super(vertex);
	}

	public JoinNode(boolean persist, String name) {
		super(persist, name);
	}

	@Override
	protected abstract List<? extends ActivityEdge<? extends IN>> getInFlows();

	protected abstract ActivityEdge<OUT> getOutFlow();

	@Override
	protected List<? extends ActivityEdge<OUT>> getOutFlows() {
		List<ActivityEdge<OUT>> result = new ArrayList<ActivityEdge<OUT>>();
		result.add(getOutFlow());
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
					token = new ObjectToken(edge.getInVertex());
				}
				result.add((IN) token);
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
						token = new ObjectToken(edge.getInVertex());
					}
					result.add((IN) token);
				}
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
					token = new ObjectToken(edge.getInVertex());
				}
				result.add((OUT) token);
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
						token = new ObjectToken(edge.getInVertex());
					}
					result.add((OUT) token);
				}
			}
		}
		return result;
	}

}
