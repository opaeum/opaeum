package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class ObjectFlow<O> extends ActivityEdge<ObjectToken<O>> {

	public ObjectFlow(Edge edge) {
		super(edge);
	}

}
