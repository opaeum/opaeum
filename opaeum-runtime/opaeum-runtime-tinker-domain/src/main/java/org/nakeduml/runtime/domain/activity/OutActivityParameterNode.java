package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OutActivityParameterNode<O> extends ActivityParameterNode<O> {

	public OutActivityParameterNode() {
		super();
	}

	public OutActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public OutActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected List<ObjectFlow<O>> getOutFlows() {
		return Collections.emptyList();
	}

}
