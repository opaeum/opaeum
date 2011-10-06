package org.opaeum.metamodel.commonbehaviors;

import java.util.Collection;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;

public interface INakedReception extends INakedElement{
	INakedSignal getSignal();
	void setSignal(INakedSignal s);
	Collection<INakedBehavior> getMethods();
	INakedClassifier getOwner();
}
