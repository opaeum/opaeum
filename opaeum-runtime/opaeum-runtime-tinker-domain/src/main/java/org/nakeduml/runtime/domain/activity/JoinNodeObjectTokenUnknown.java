package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeObjectTokenUnknown extends JoinNode<ObjectToken<?>> {

	public JoinNodeObjectTokenUnknown() {
		super();
	}
	
	public JoinNodeObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}	

	public JoinNodeObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	protected abstract ObjectFlowUnknown getOutFlow();

	@Override
	protected List<ObjectFlowUnknown> getOutFlows() {
		List<ObjectFlowUnknown> result = new ArrayList<ObjectFlowUnknown>();
		result.add(getOutFlow());
		return result;
	}
	
	@Override
	protected abstract List<ObjectFlowUnknown> getInFlows();
	
	@Override
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

	@Override
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

	@Override
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

	@Override
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
