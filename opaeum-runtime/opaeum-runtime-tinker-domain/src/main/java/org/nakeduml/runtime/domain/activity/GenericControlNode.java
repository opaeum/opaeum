package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class GenericControlNode extends ControlNode<Token> {

	public GenericControlNode() {
		super();
	}

	public GenericControlNode(boolean persist, String name) {
		super(persist, name);
	}

	public GenericControlNode(Vertex vertex) {
		super(vertex);
	}

	@SuppressWarnings("rawtypes")
	public List<Token> getInTokens() {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<?> flow : getInFlows()) {
			if (flow instanceof ControlFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			} else if (flow instanceof ObjectFlowKnown<?>) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken(edge.getInVertex()));
				}
			} else {
				throw new IllegalStateException("wtf");
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<Token> getInTokens(String inFlowName) {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<?> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				if (flow instanceof ControlFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ControlToken(edge.getInVertex()));
					}
				} else if (flow instanceof ObjectFlowKnown<?>) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ObjectToken(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<Token> getOutTokens() {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<?> flow : getOutFlows()) {
			if (flow instanceof ControlFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			} else if (flow instanceof ObjectFlowKnown<?>) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken(edge.getInVertex()));
				}
			} else {
				throw new IllegalStateException("wtf");
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<Token> getOutTokens(String outFlowName) {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<?> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				if (flow instanceof ControlFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ControlToken(edge.getInVertex()));
					}
				} else if (flow instanceof ObjectFlowKnown<?>) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ObjectToken(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}

}
