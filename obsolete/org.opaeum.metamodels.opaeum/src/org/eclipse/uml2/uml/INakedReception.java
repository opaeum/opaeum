package org.eclipse.uml2.uml;

import java.util.Collection;


public interface INakedReception extends INakedElement{
	INakedSignal getSignal();
	void setSignal(INakedSignal s);
	Collection<INakedBehavior> getMethods();
	INakedClassifier getOwner();
}
