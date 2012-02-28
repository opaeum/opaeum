package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InvocationAction extends Action {

	public InvocationAction() {
		super();
	}

	public InvocationAction(boolean persist, String name) {
		super(persist, name);
	}

	public InvocationAction(Vertex vertex) {
		super(vertex);
	}

}
