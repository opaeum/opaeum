package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedExceptionHandler;
import org.eclipse.uml2.uml.INakedExecutableNode;

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
