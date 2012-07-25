package org.eclipse.uml2.uml;

import java.util.List;

public interface INakedExpansionRegion extends INakedStructuredActivityNode {
	List<INakedExpansionNode> getInputElement();
	List<INakedExpansionNode> getOutputElement();
}
