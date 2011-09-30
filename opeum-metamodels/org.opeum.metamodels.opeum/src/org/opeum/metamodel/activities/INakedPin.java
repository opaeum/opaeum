package org.opeum.metamodel.activities;

import org.opeum.metamodel.core.INakedTypedElement;

public interface INakedPin extends INakedObjectNode {
	INakedTypedElement getLinkedTypedElement();
	void setLinkedTypedElement(INakedTypedElement  p);
	INakedAction getAction();
}
