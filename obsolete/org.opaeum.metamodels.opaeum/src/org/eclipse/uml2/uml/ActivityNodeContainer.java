package org.eclipse.uml2.uml;

import java.util.Collection;
import java.util.Set;


public interface ActivityNodeContainer extends INakedElement,PreAndPostConstrained,INakedObservantElement{
	Collection<INakedActivityVariable> getVariables();
	Collection<INakedActivityEdge> getActivityEdges();
	Collection<INakedActivityNode> getActivityNodes();
	Collection<INakedActivityNode> getStartNodes();
	INakedActivity getActivity();
}
