package org.eclipse.uml2.uml;


public interface INakedTimeObservation extends INakedTypedElement{
	INakedElement getObservedElement();
	ObservedPoint getObservedPoint();
}
