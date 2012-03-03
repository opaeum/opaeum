package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class MergeNodeObjectTokenKnown<O> extends MergeNode<ObjectToken<O>> {

	public MergeNodeObjectTokenKnown() {
		super();
	}

	public MergeNodeObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	public MergeNodeObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected abstract ObjectFlowKnown<O> getOutFlow();

	@Override
	protected abstract List<ObjectFlowKnown<O>> getInFlows();

	@Override
	protected List<ObjectFlowKnown<O>> getOutFlows() {
		List<ObjectFlowKnown<O>> result = new ArrayList<ObjectFlowKnown<O>>();
		result.add(getOutFlow());
		return result;
	}

	@Override
	public List<ObjectToken<O>> getInTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ObjectFlowKnown<O> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken<O>(edge.getInVertex()));
			}
		}
		return result;
	}

	@Override
	public List<ObjectToken<O>> getInTokens(String inFlowName) {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ObjectFlowKnown<O> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken<O>(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	@Override
	public List<ObjectToken<O>> getOutTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ObjectFlowKnown<O> flow : getOutFlows()) {
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
		for (ObjectFlowKnown<O> flow : getOutFlows()) {
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
