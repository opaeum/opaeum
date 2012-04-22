package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InActivityParameterNode<O, IN extends ObjectToken<O>, OUT extends ObjectToken<O>> extends ActivityParameterNode<O, IN, OUT> {

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

}
