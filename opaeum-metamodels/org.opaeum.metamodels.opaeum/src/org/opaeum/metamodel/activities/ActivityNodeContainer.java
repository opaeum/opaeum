package org.opaeum.metamodel.activities;

import java.util.Collection;

import org.opaeum.metamodel.core.INakedElement;

public interface ActivityNodeContainer extends INakedElement{
	Collection<INakedActivityVariable> getVariables();

	Collection<INakedActivityEdge> getActivityEdges();

	Collection<INakedActivityNode> getActivityNodes();

	Collection<INakedActivityNode> getStartNodes();

	INakedActivity getActivity();
}
