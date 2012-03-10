package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class MergeNodeObjectTokenKnownWithInControlToken<O> extends MergeNode<Token, ObjectToken<O>> {

	public MergeNodeObjectTokenKnownWithInControlToken() {
		super();
	}

	public MergeNodeObjectTokenKnownWithInControlToken(boolean persist, String name) {
		super(persist, name);
	}

	public MergeNodeObjectTokenKnownWithInControlToken(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract ObjectFlowKnown<O> getOutFlow();
	
	@Override
	protected abstract List<ObjectFlowUnknown> getInFlows();

	@Override
	protected List<ObjectFlowKnown<O>> getOutFlows() {
		List<ObjectFlowKnown<O>> result = new ArrayList<ObjectFlowKnown<O>>();
		result.add(getOutFlow());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Token> getInTokens() {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<? extends Token> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				Token token;
				if (flow instanceof ControlFlow) {
					token = new ControlToken(edge.getInVertex());
				} else {
					token = new ObjectToken(edge.getInVertex());
				}
				result.add(token);
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Token> getInTokens(String inFlowName) {
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<? extends Token> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					Token token;
					if (flow instanceof ControlFlow) {
						token = new ControlToken(edge.getInVertex());
					} else {
						token = new ObjectToken(edge.getInVertex());
					}
					result.add(token);
				}
			}
		}
		return result;
	}

	@Override
	public List<ObjectToken<O>> getOutTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken<O>(edge.getInVertex()));
			}
		}
		return result;
	}

	@Override
	public List<ObjectToken<O>> getOutTokens(String outFlowName) {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken<O>(edge.getInVertex()));
				}
			}
		}
		return result;
	}

}
