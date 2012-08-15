package org.eclipse.uml2.uml;

public interface INakedExpansionNode extends INakedObjectNode {
	INakedExpansionRegion getExpansionRegion();
	boolean isOutputElement();
	boolean isInputElement();
}
