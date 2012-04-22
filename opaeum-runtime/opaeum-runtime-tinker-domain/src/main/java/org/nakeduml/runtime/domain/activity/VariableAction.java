package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

/*
 * V is the type of the variable, if it has multiplicity of many then the elements are of type V
 */
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

}
