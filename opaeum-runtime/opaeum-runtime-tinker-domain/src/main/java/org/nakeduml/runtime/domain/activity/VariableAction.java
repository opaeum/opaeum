package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class VariableAction<V> extends Action {

	public VariableAction() {
		super();
	}

	public VariableAction(boolean persist, String name) {
		super(persist, name);
	}

	public VariableAction(Vertex vertex) {
		super(vertex);
	}
	
	protected abstract V getVariable();

}
