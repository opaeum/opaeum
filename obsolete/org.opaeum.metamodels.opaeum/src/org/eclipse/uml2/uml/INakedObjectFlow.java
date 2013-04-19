package org.eclipse.uml2.uml;
public interface INakedObjectFlow extends INakedActivityEdge {
	INakedObjectNode getOriginatingObjectNode();
	INakedBehavior getTransformation();
	void setTransformation(INakedBehavior b);
	void setSelection(INakedBehavior b);
	INakedBehavior getSelection();
	INakedObjectNode getFedObjectNode();
}
