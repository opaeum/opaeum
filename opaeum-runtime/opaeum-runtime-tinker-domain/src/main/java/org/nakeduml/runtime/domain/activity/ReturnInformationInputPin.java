package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ReturnInformationInputPin<O> extends InputPin<O> {

	public ReturnInformationInputPin() {
		super();
	}

	public ReturnInformationInputPin(boolean persist, String name) {
		super(persist, name);
	}

	public ReturnInformationInputPin(Vertex vertex) {
		super(vertex);
	}

	@Override
	public abstract ReplyAction<?> getAction();
	
}
