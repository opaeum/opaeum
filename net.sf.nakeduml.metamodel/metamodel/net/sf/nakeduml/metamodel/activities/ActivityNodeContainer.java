package net.sf.nakeduml.metamodel.activities;

import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface ActivityNodeContainer extends INakedElement{
	Collection<INakedActivityVariable> getVariables();

	Collection<INakedActivityEdge> getActivityEdges();

	Collection<INakedActivityNode> getActivityNodes();

	Collection<INakedActivityNode> getStartNodes();
}
