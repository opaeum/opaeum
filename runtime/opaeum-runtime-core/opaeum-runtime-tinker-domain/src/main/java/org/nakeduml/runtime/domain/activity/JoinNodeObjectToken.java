package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeObjectToken<O> extends JoinNode<ObjectToken<O>> {

	public JoinNodeObjectToken() {
		super();
	}
	
	public JoinNodeObjectToken(Vertex vertex) {
		super(vertex);
	}	

	public JoinNodeObjectToken(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	protected abstract ActivityEdge<ObjectToken<O>> getOutFlow();

	@Override
	protected List<? extends ActivityEdge<ObjectToken<O>>> getOutFlows() {
		List<ActivityEdge<ObjectToken<O>>> result = new ArrayList<ActivityEdge<ObjectToken<O>>>();
		result.add(getOutFlow());
		return result;
	}
	
	@Override
	protected abstract List<ActivityEdge<ObjectToken<O>>> getInFlows();
	
	@Override
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

	@Override
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
