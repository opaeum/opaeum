package org.nakeduml.runtime.domain.activity;

import java.util.Collection;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyOpaqueAction<R> extends OpaqueAction<R, CollectionObjectToken<R>> {

	public ManyOpaqueAction() {
		super();
	}

	public ManyOpaqueAction(boolean persist, String name) {
		super(persist, name);
	}

	public ManyOpaqueAction(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected void execute() {
		super.execute();
		// Place the result of the body expression on the output pin
		ManyOutputPin<R> resultPin = getResultPin();
		if (resultPin != null) {
			resultPin.addIncomingToken(new CollectionObjectTokenInterator<R>(resultPin.getName(), getBodyExpression()));
		}
	}

	
	protected abstract Collection<R> getBodyExpression();

	@Override
	protected abstract ManyOutputPin<R> getResultPin();
	
}
