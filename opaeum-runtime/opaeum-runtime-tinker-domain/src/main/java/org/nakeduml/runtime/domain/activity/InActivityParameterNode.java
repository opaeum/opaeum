package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IInActivityParameterNode;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InActivityParameterNode<O, IN extends ObjectToken<O>> extends ActivityParameterNode<O, IN> implements IInActivityParameterNode<O, IN> {

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
	protected List<? extends ObjectFlowKnown<O, IN>> getInFlows() {
		return Collections.emptyList();
	}
	
	@Override
	public List<IN> getInTokens() {
		List<IN> result = new ArrayList<IN>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(constructInToken(edge));
		}
		return result;
	}	
	
}
