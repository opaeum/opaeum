package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedTypedElement;

public interface INakedDurationObservation extends INakedTypedElement{
	INakedElement getFromObservedElement();
	ObservedPoint getFromObservedPoint();
	INakedElement getToObservedElement();
	ObservedPoint getToObservedPoint();
}
