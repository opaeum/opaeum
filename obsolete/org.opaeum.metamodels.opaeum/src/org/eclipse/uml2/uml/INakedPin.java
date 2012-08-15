package org.eclipse.uml2.uml;


public interface INakedPin extends INakedObjectNode {
	INakedTypedElement getLinkedTypedElement();
	void setLinkedTypedElement(INakedTypedElement  p);
	INakedAction getAction();
}
