package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OpaqueAction<R> extends Action {

	public OpaqueAction() {
		super();
	}

	public OpaqueAction(boolean persist, String name) {
		super(persist, name);
	}

	public OpaqueAction(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected void execute() {
		super.execute();
		// Place the result of the body expression on the output pin
		OutputPin<R> resultPin = getResultPin();
		if (resultPin != null && resultPin.mayAcceptToken()) {
			resultPin.addIncomingToken(new ObjectToken<R>(resultPin.getName(), getBodyExpression()));
		}
	}

	protected abstract R getBodyExpression();

	@Override
	protected List<? extends OutputPin<?>> getOutputPins() {
		OutputPin<R> resultPin = getResultPin();
		if (resultPin != null) {
			return Arrays.<OutputPin<?>> asList(resultPin);
		} else {
			return Collections.<OutputPin<?>> emptyList();
		}
	}

	protected abstract OutputPin<R> getResultPin();

}
