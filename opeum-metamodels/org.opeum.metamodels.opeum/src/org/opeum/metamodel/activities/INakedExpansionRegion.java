package org.opeum.metamodel.activities;

import java.util.List;

public interface INakedExpansionRegion extends INakedStructuredActivityNode {
	List<INakedExpansionNode> getInputElement();
	List<INakedExpansionNode> getOutputElement();
}
