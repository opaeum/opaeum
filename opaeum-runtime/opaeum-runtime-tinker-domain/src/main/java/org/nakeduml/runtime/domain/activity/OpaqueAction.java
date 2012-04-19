package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.Collection;
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
		if (resultPin != null) {
			resultPin.addIncomingToken(new ObjectTokenInterator<R>(resultPin.getName(), getBodyExpression().iterator()));
		}
	}

	protected abstract Collection<R> getBodyExpression();

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

	/*
	 * This will only be called if the lower multiplicity is reachedAll up to
	 * upper multiplicity is consumed
	 */
	protected void transferObjectTokensToAction() {
		super.transferObjectTokensToAction();
		for (InputPin<?> inputPin : this.getInputPins()) {
			int tokensTransferedCount = 0;
			if (inputPin instanceof ValuePin<?>) {
				addToInputPinVariable(inputPin, ((ValuePin<?>)inputPin).getValue());
			} else {
				for (ObjectToken<?> token : inputPin.getInTokens()) {
					if (++tokensTransferedCount <= inputPin.getUpperMultiplicity()) {
						token.removeEdgeFromActivityNode();
						addToInputPinVariable(inputPin, token.getObject());
						token.remove();
					}
				}
			}
		}
	}
}
