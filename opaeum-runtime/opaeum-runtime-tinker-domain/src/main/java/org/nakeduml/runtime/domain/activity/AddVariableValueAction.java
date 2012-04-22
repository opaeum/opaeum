package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AddVariableValueAction<V> extends WriteVariableAction<V> {

	public AddVariableValueAction() {
	}

	public AddVariableValueAction(boolean persist, String name) {
		super(persist, name);
	}

	public AddVariableValueAction(Vertex vertex) {
		super(vertex);
	}

}
