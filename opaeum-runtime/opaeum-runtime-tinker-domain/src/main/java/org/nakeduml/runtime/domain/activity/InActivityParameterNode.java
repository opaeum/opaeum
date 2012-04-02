package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InActivityParameterNode<O> extends ActivityParameterNode<O> {

	public InActivityParameterNode() {
		super();
	}

	public InActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public InActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected List<ObjectFlowKnown<O>> getInFlows() {
		return Collections.emptyList();
	}
	
	@Override
	public List<ObjectToken<O>> getInTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(new ObjectToken<O>(edge.getInVertex()));
		}
		return result;
	}	
	
}
