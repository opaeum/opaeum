package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedElement;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;

public class NakedImportedElementImpl extends NakedElementImpl implements INakedElement, IImportedElement {
	private static final long serialVersionUID = 1630029513308357488L;
	IModelElement element;

	@Override
	public String getMetaClass() {
		return "import";
	}

	@Override
	public String getName() {
		if (getElement() == null) {
			return "dummy";
		} else {
			String n = getElement().getName();
			if (n == null) {
				return "dummy";
			}
			return n;
		}
	}

	public IModelElement getElement() {
		return element;
	}

	public boolean isReference() {
		return false;
	}

	public void setElement(IModelElement referenced) {
		this.element = referenced;
	}
}
