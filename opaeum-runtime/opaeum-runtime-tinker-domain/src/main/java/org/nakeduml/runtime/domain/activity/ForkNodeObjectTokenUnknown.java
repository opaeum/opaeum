package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ForkNodeObjectTokenUnknown extends ForkNode<ObjectToken<?>> {

	public ForkNodeObjectTokenUnknown() {
		super();
	}

	public ForkNodeObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}

	public ForkNodeObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}

	@SuppressWarnings("rawtypes")
	public List<ObjectToken<?>> getInTokens() {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ActivityEdge<ObjectToken<?>> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken(edge.getInVertex()));
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<ObjectToken<?>> getInTokens(String inFlowName) {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ActivityEdge<ObjectToken<?>> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<ObjectToken<?>> getOutTokens() {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ActivityEdge<ObjectToken<?>> flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken(edge.getInVertex()));
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List<ObjectToken<?>> getOutTokens(String outFlowName) {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ActivityEdge<ObjectToken<?>> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}		
}
