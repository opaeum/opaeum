package org.opaeum.metamodel.activities;

public interface INakedExpansionNode extends INakedObjectNode {
	INakedExpansionRegion getExpansionRegion();
	boolean isOutputElement();
	boolean isInputElement();
}
