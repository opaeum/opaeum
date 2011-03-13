package net.sf.nakeduml.metamodel.validation;

import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.model.IModelElement;

public class ErrorMap {
	public ErrorMap() {
		super();
	}

	private Map<IModelElement, Object> modelElementMap = new HashMap<IModelElement, Object>();
	/**
	 * Maps source objects (EMF, JMI) to their associated errors through the
	 * BrokenElement class
	 */
	private Map<Object, BrokenElement> errors = new HashMap<Object, BrokenElement>();

	public Map<Object, BrokenElement> getErrors() {
		return errors;
	}

	public void putError(IModelElement holder, IValidationRule rule, Object... objects) {
		getErrorListFor(holder).addMessage(rule, objects);
	}

	private BrokenElement getErrorListFor(IModelElement holder) {
		Object originalElement = getOriginalElement(holder);
		BrokenElement list = this.errors.get(originalElement);
		if (list == null) {
			list = new BrokenElement();
			this.errors.put(originalElement, list);
		}
		return list;
	}

	private Object getOriginalElement(IModelElement holder) {
		return this.modelElementMap.get(holder);
	}

	public void attachElement(IModelElement orig, IModelElement attached) {
		this.modelElementMap.put(attached, this.modelElementMap.get(orig));
	}

	public Object getSourceElement(IModelElement me) {
		return modelElementMap.get(me);
	}

	public void linkElement(IModelElement attached, Object orig) {
		this.modelElementMap.put(attached, orig);
	}

	public boolean hasBroken(IValidationRule rule, Object target) {
		BrokenElement e = errors.get(target);
		if (e == null) {
			return false;
		} else {
			return e.hasBroken(rule);
		}
	}

	public void clear() {
		this.modelElementMap.clear();
		this.errors.clear();
	}
}
