package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ReadVariableAction<V> extends VariableAction<V> {

	public ReadVariableAction() {
	}

	public ReadVariableAction(boolean persist, String name) {
		super(persist, name);
	}

	public ReadVariableAction(Vertex vertex) {
		super(vertex);
	}

}
