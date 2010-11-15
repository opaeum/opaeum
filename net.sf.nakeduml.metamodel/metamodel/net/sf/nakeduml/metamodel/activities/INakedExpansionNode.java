package net.sf.nakeduml.metamodel.activities;

public interface INakedExpansionNode extends INakedObjectNode {
	INakedExpansionRegion getExpansionRegion();
	boolean isOutputElement();
	boolean isInputElement();
}
