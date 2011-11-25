package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedTypedElement;

public interface INakedTimeObservation extends INakedTypedElement{
	INakedElement getObservedElement();
	ObservedPoint getObservedPoint();
}
