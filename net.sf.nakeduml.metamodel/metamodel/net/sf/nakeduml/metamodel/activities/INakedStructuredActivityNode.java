package net.sf.nakeduml.metamodel.activities;

import java.util.Collection;

public interface INakedStructuredActivityNode extends INakedActivityNode {
	Collection<INakedActivityNode> getChildren();
	Collection<INakedActivityVariable> getVariables();
	Collection<INakedActivityNode> getStartNodes();
}
