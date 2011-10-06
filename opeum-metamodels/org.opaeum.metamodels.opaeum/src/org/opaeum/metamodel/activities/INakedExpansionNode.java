package org.opeum.metamodel.activities;

public interface INakedExpansionNode extends INakedObjectNode {
	INakedExpansionRegion getExpansionRegion();
	boolean isOutputElement();
	boolean isInputElement();
}
