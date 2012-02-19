package org.nakeduml.runtime.domain.activity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class InitialNode extends ControlNode<ControlToken> {

	public InitialNode() {
		super();
	}
	
	public InitialNode(Vertex vertex) {
		super(vertex);
	}	

	public InitialNode(boolean persist, String name) {
		super(persist, name);
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
	
	@Override
	protected List<? extends ActivityEdge<ControlToken>> getInFlows() {
		return Collections.<ActivityEdge<ControlToken>>emptyList();
	}

	@Override
	protected boolean mayContinue() {
		return true;
	}
	
	@Override
	public List<ControlToken> getInTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(new ControlToken(edge.getInVertex()));
		}
		return result;
	}	

}
