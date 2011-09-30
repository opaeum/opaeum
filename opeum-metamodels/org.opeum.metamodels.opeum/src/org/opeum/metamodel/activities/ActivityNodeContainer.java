package org.opeum.metamodel.activities;

import java.util.Collection;

import org.opeum.metamodel.core.INakedElement;

public interface ActivityNodeContainer extends INakedElement{
	Collection<INakedActivityVariable> getVariables();

	Collection<INakedActivityEdge> getActivityEdges();

	Collection<INakedActivityNode> getActivityNodes();

	Collection<INakedActivityNode> getStartNodes();
}
