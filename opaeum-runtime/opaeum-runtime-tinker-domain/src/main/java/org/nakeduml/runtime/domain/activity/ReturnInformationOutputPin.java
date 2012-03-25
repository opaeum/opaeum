package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ReturnInformationOutputPin<O> extends OutputPin<O> {

	public ReturnInformationOutputPin() {
		super();
	}

	public ReturnInformationOutputPin(boolean persist, String name) {
		super(persist, name);
	}

	public ReturnInformationOutputPin(Vertex vertex) {
		super(vertex);
	}

	protected abstract ReturnInformationInputPin<O> getReturnInformationInputPin();
}
