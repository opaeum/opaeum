package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ExecutableNode extends ActivityNode<ControlToken> {

	public ExecutableNode() {
		super();
	}

	public ExecutableNode(boolean persist, String name) {
		super(persist, name);
	}

	public ExecutableNode(Vertex vertex) {
		super(vertex);
	}

}
