package org.opaeum.metamodel.activities;

import org.opaeum.metamodel.core.INakedTypedElement;

public interface INakedPin extends INakedObjectNode {
	INakedTypedElement getLinkedTypedElement();
	void setLinkedTypedElement(INakedTypedElement  p);
	INakedAction getAction();
}
