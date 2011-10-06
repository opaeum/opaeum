package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.activities.INakedExecutableNode;
import org.opaeum.metamodel.core.INakedElement;

public class NakedExecutableNodeImpl extends NakedActivityNodeImpl implements INakedExecutableNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020532395165872389L;
	private Collection<INakedExceptionHandler> handlers = new ArrayList<INakedExceptionHandler>();

	public Collection<INakedExceptionHandler> getHandlers() {
		return handlers;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
		if (element instanceof INakedExceptionHandler) {
			handlers.add((INakedExceptionHandler) element);
		}
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> result = super.getOwnedElements();
		result.addAll(handlers);
		return result;
	}

	public void setHandlers(Collection<INakedExceptionHandler> handlers) {
		this.handlers = handlers;
	}

	public boolean hasExceptions() {
		return getHandlers().size() > 0;
	}
}
