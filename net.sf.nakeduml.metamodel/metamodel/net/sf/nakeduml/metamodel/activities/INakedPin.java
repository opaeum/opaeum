package net.sf.nakeduml.metamodel.activities;

import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public interface INakedPin extends INakedObjectNode {
	INakedTypedElement getLinkedTypedElement();
	void setLinkedTypedElement(INakedTypedElement  p);
	INakedAction getAction();
}
