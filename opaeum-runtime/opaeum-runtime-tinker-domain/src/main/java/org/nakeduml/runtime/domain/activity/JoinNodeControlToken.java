package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeControlToken extends JoinNode<ControlToken> {

	public JoinNodeControlToken() {
		super();
	}
	
	public JoinNodeControlToken(Vertex vertex) {
		super(vertex);
	}	

	public JoinNodeControlToken(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	protected abstract ActivityEdge<ControlToken> getOutFlow();

	@Override
	protected List<? extends ActivityEdge<ControlToken>> getOutFlows() {
		List<ActivityEdge<ControlToken>> result = new ArrayList<ActivityEdge<ControlToken>>();
		result.add(getOutFlow());
		return result;
	}
	
	@Override
	protected abstract List<? extends ActivityEdge<ControlToken>> getInFlows();	

	@Override
	public List<ControlToken> getInTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}

	@Override
	public List<ControlToken> getInTokens(String inFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	@Override
	public List<ControlToken> getOutTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}

	@Override
	public List<ControlToken> getOutTokens(String outFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}

}
