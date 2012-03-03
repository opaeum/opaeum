package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class DecisionObjectTokenUnknown extends DecisionNode<ObjectToken<?>> {

	public DecisionObjectTokenUnknown() {
		super();
	}

	public DecisionObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}

	public DecisionObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract ObjectFlowUnknown getInFlow();
	@Override
	protected abstract List<ObjectFlowUnknown> getOutFlows();

	
	@Override
	protected List<ObjectFlowUnknown> getInFlows() {
		List<ObjectFlowUnknown> result = new ArrayList<ObjectFlowUnknown>();
		result.add(getInFlow());
		return result;
	}
	
	public List<ObjectToken<?>> getInTokens() {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ObjectFlowUnknown flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				@SuppressWarnings("rawtypes")
				ObjectToken<?> e = new ObjectToken(edge.getInVertex());
				result.add(e);
			}
		}
		return result;
	}

	public List<ObjectToken<?>> getInTokens(String inFlowName) {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ObjectFlowUnknown flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					@SuppressWarnings("rawtypes")
					ObjectToken<?> e = new ObjectToken(edge.getInVertex());
					result.add(e);
				}
			}
		}
		return result;
	}

	public List<ObjectToken<?>> getOutTokens() {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ObjectFlowUnknown flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				@SuppressWarnings("rawtypes")
				ObjectToken<?> e = new ObjectToken(edge.getInVertex());
				result.add(e);
			}
		}
		return result;
	}

	public List<ObjectToken<?>> getOutTokens(String outFlowName) {
		List<ObjectToken<?>> result = new ArrayList<ObjectToken<?>>();
		for (ObjectFlowUnknown flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					@SuppressWarnings("rawtypes")
					ObjectToken<?> e = new ObjectToken(edge.getInVertex());
					result.add(e);
				}
			}
		}
		return result;
	}	
}
