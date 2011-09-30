package org.opeum.metamodel.commonbehaviors;

import java.util.Collection;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;

public interface INakedReception extends INakedElement{
	INakedSignal getSignal();
	void setSignal(INakedSignal s);
	Collection<INakedBehavior> getMethods();
	INakedClassifier getOwner();
}
