package org.opaeum.metamodel.activities;

import java.util.Collection;
import java.util.Set;

import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedObservantElement;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.PreAndPostConstrained;

public interface ActivityNodeContainer extends INakedElement,PreAndPostConstrained,INakedObservantElement{
	Collection<INakedActivityVariable> getVariables();
	Collection<INakedActivityEdge> getActivityEdges();
	Collection<INakedActivityNode> getActivityNodes();
	Collection<INakedActivityNode> getStartNodes();
	INakedActivity getActivity();
}
