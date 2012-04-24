package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OpaqueAction<R, OUT extends ObjectToken<R>> extends Action {

	public OpaqueAction() {
		super();
	}

	public OpaqueAction(boolean persist, String name) {
		super(persist, name);
	}

	public OpaqueAction(Vertex vertex) {
		super(vertex);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<? extends OutputPin<R,OUT>> getOutputPins() {
		OutputPin<R,OUT> resultPin = getResultPin();
		if (resultPin != null) {
			return Arrays.<OutputPin<R,OUT>> asList(resultPin);
		} else {
			return Collections.<OutputPin<R,OUT>> emptyList();
		}
	}
	
	protected abstract OutputPin<R,OUT> getResultPin();

}
