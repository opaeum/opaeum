package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class StructuralFeatureAction<O> extends Action {

	public StructuralFeatureAction() {
		super();
	}

	public StructuralFeatureAction(boolean persist, String name) {
		super(persist, name);
	}

	public StructuralFeatureAction(Vertex vertex) {
		super(vertex);
	}
	
	public abstract InputPin<O> getObject();
	
}
