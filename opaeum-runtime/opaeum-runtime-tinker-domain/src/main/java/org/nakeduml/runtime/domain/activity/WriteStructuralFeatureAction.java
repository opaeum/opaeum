package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class WriteStructuralFeatureAction<V, O> extends StructuralFeatureAction<O> {

	public WriteStructuralFeatureAction() {
		super();
	}

	public WriteStructuralFeatureAction(boolean persist, String name) {
		super(persist, name);
	}

	public WriteStructuralFeatureAction(Vertex vertex) {
		super(vertex);
	}
	
	public abstract InputPin<V, SingleObjectToken<V>> getValue();
	public abstract OutputPin<O, SingleObjectToken<O>> getResult();

}
