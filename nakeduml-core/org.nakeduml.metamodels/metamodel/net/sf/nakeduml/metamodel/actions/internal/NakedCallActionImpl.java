package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import nl.klasse.octopus.model.ParameterDirectionKind;

public abstract class NakedCallActionImpl extends NakedInvocationActionImpl implements INakedCallAction {
	boolean isSynchronous;
	private List<INakedOutputPin> result = new ArrayList<INakedOutputPin>();

	public boolean isSynchronous() {
		return isSynchronous;
	}

	public void setSynchronous(boolean isSynchronous) {
		this.isSynchronous = isSynchronous;
	}

	public List<INakedOutputPin> getExceptionPins() {
		List<INakedOutputPin> exceptionPins = new ArrayList<INakedOutputPin>();
		for (INakedOutputPin pin : this.result) {
			if (pin.getLinkedTypedElement() instanceof INakedParameter) {
				INakedParameter p = (INakedParameter) pin.getLinkedTypedElement();
				if (p.isException() && p.getDirection().equals(ParameterDirectionKind.OUT)) {
					exceptionPins.add(pin);
				}
			}
		}
		return exceptionPins;
	}

	public Collection<INakedOutputPin> getOutput() {
		return getResult();
	}

	public INakedPin getReturnPin() {
		INakedPin potentialMatch = null;
		for (INakedPin pin : this.result) {
			if (pin.getLinkedTypedElement() instanceof INakedParameter) {
				INakedParameter p = (INakedParameter) pin.getLinkedTypedElement();
				if (!p.isException()) {
					if (p.isReturn()) {
						return pin;
					} else if (p.isResult()) {
						potentialMatch = pin;
					}
				}
			}
		}
		if (potentialMatch != null) {
			return potentialMatch;
		}
		if (this.result.size() ==  1 && result.get(0).getLinkedTypedElement() == null) {
			return this.result.get(0);
		}
		return null;
	}
	public List<INakedOutputPin> getResult() {
		return this.result;
	}

	public void setResult(List<INakedOutputPin> arguments) {
		this.result = arguments;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> elements = super.getOwnedElements();
		elements.addAll(this.result);
		return elements;
	}

	@Override
	public Set<INakedActivityEdge> getDefaultOutgoing() {
		Set<INakedActivityEdge> results = super.getDefaultOutgoing();
		Iterator<INakedActivityEdge> iter = results.iterator();
		while (iter.hasNext()) {
			INakedActivityEdge e = iter.next();
			if (e.getSource() instanceof INakedOutputPin && ((INakedOutputPin) e.getSource()).isException()) {
				iter.remove();
			}
		}
		return results;
	}
}
