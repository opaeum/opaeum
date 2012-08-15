package org.opaeum.metamodel.commonbehaviors.internal;

import nl.klasse.octopus.model.IMultiplicityKind;

import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedTimeObservation;
import org.eclipse.uml2.uml.ObservedPoint;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;

public class NakedTimeObservationImpl extends NakedObservationImpl implements INakedTimeObservation{
	private static final long serialVersionUID = -4092737163365435368L;
	private ObservedPoint observedPoint;
	private INakedElement observedElement;
	@Override
	public IMultiplicityKind getMultiplicity(){
		return NakedMultiplicityImpl.ZERO_ONE;
	}
	public ObservedPoint getObservedPoint(){
		return observedPoint;
	}
	public void setObservedPoint(ObservedPoint observedPoint){
		this.observedPoint = observedPoint;
	}
	public INakedElement getObservedElement(){
		return observedElement;
	}
	public void setObservedElement(INakedElement observedElement){
		this.observedElement = observedElement;
	}
	@Override
	public String getMetaClass(){
		return "timeObservation";
	}
}
