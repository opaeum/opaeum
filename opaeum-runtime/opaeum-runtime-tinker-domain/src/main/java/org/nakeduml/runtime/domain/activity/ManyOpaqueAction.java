package org.nakeduml.runtime.domain.activity;

import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyInputPin;

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
			resultPin.addIncomingToken(new CollectionObjectTokenInterator<R>(resultPin.getName(), getBodyExpression().iterator()));
		}
	}

	protected abstract void addToInputPinVariable(ManyInputPin<R> inputPin, Collection<R> object);

	protected abstract Collection<R> getBodyExpression();

	protected abstract ManyOutputPin<R> getResultPin();

	protected abstract List<? extends IManyInputPin<R>> getInputPins();
	
	/*
	 * This will only be called if the lower multiplicity is reached, all up to
	 * upper multiplicity is consumed
	 */
	protected void transferObjectTokensToAction() {
		super.transferObjectTokensToAction();
		for (IManyInputPin<R> inputPin : this.getInputPins()) {
			if (inputPin instanceof ManyValuePin) {
				int elementsTransferedCount = 0;
				addToInputPinVariable(inputPin, ((ManyValuePin<R>) inputPin).getValue());
			} else {
				int elementsTransferedCount = 0;
				ManyInputPin<R> manyInputPin = (ManyInputPin<R>) inputPin;
				for (CollectionObjectToken<R> token : manyInputPin.getInTokens()) {
					if (elementsTransferedCount < inputPin.getUpperMultiplicity()) {
						if (elementsTransferedCount + token.getCollection().size() <= inputPin.getUpperMultiplicity()) {
							// transfer all elements
							elementsTransferedCount += token.getCollection().size();
							token.removeEdgeFromActivityNode();
							addToInputPinVariable(inputPin, token.getCollection());
							token.remove();
						} else {
							for (R element : token.getCollection()) {
								elementsTransferedCount += 1;
								addToInputPinVariable(inputPin, element);
							}
						}
					}
				}
			}
		}
	}
}
