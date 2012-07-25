package org.eclipse.uml2.uml;


public interface INakedDurationObservation extends INakedTypedElement{
	INakedElement getFromObservedElement();
	ObservedPoint getFromObservedPoint();
	INakedElement getToObservedElement();
	ObservedPoint getToObservedPoint();
}
