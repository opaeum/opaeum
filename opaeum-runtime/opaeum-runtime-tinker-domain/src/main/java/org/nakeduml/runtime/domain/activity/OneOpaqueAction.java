package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneOpaqueAction<R> extends OpaqueAction<R, SingleObjectToken<R>> {

	public OneOpaqueAction() {
		super();
	}

	public OneOpaqueAction(boolean persist, String name) {
		super(persist, name);
	}

	public OneOpaqueAction(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected void execute() {
		super.execute();
		// Place the result of the body expression on the output pin
		OneOutputPin<R> resultPin = getResultPin();
		if (resultPin != null) {
			resultPin.addIncomingToken(new SingleObjectToken<R>(resultPin.getName(), getBodyExpression()));
		}
	}

	protected abstract R getBodyExpression();

	protected abstract OneOutputPin<R> getResultPin();

	/*
	 * This will only be called if the lower multiplicity is reached, all up to
	 * upper multiplicity is consumed
	 */
	protected void transferObjectTokensToAction() {
		super.transferObjectTokensToAction();
		for (InputPin<?,?> inputPin : this.getInputPins()) {
			int tokensTransferedCount = 0;
			if (inputPin instanceof OneValuePin) {
				addToInputPinVariable(inputPin, ((OneValuePin<R>)inputPin).getValue());
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
