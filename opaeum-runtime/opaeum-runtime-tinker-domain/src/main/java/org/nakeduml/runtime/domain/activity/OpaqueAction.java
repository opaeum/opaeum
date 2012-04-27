package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IInputPin;

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
	
	@Override
	protected abstract List<? extends IInputPin<?,?>> getInputPins();
	
	protected abstract OutputPin<R,OUT> getResultPin();

	protected abstract void addToInputPinVariable(IInputPin<?, ?> inputPin, Collection<?> elements);
	
	/*
	 * This will only be called if the lower multiplicity is reached, all up to
	 * upper multiplicity is consumed
	 */
	protected void transferObjectTokensToAction() {
		for (IInputPin<?,?> inputPin : this.getInputPins()) {
			int elementsTransferedCount = 0;
			for (ObjectToken<?> token : inputPin.getInTokens()) {
				if (elementsTransferedCount < inputPin.getUpperMultiplicity()) {
					
					if (elementsTransferedCount + token.getNumberOfElements() <= inputPin.getUpperMultiplicity()) {
						// transfer all elements
						elementsTransferedCount += token.getNumberOfElements();
						token.removeEdgeFromActivityNode();
						addToInputPinVariable(inputPin, token.getElements());
						token.remove();
					} else {
						Collection<Object> tmp = new ArrayList<Object>();
						for (Object element : token.getElements()) {
							elementsTransferedCount += 1;
							tmp.add(element);
							if (elementsTransferedCount >= inputPin.getUpperMultiplicity()) {
								break;
							}
						}
						token.getElements().removeAll(tmp);
						addToInputPinVariable(inputPin, tmp);
					}
				}
			}
		}
	}

}
