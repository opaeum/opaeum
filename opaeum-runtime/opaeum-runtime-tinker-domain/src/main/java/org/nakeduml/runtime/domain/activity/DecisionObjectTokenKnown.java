package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class DecisionObjectTokenKnown<O> extends DecisionNode<ObjectToken<O>> {

	public DecisionObjectTokenKnown() {
		super();
	}

	public DecisionObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	public DecisionObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract ObjectFlowKnown<O> getInFlow();

	@Override
	protected List<ObjectFlowKnown<O>> getInFlows() {
		List<ObjectFlowKnown<O>> result = new ArrayList<ObjectFlowKnown<O>>();
		result.add(getInFlow());
		return result;
	}

	public List<ObjectToken<O>> getInTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ActivityEdge<ObjectToken<O>> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken<O>(edge.getInVertex()));
			}
		}
		return result;
	}

	public List<ObjectToken<O>> getInTokens(String inFlowName) {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ActivityEdge<ObjectToken<O>> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken<O>(edge.getInVertex()));
				}
			}
		}
		return result;
	}

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
