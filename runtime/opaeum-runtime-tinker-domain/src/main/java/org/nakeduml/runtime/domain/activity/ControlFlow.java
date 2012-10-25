package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class ControlFlow extends ActivityEdge<ControlToken> {

	public ControlFlow(Edge edge) {
		super(edge);
	}

}
