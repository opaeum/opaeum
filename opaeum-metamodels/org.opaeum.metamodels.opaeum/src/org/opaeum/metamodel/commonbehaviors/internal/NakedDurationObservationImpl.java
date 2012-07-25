package org.opaeum.metamodel.commonbehaviors.internal;

import org.eclipse.uml2.uml.INakedDurationObservation;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.ObservedPoint;

public class NakedDurationObservationImpl extends NakedObservationImpl implements INakedDurationObservation{
	private static final long serialVersionUID = -4918133177125335657L;
	private INakedElement fromObservedElement;
	private INakedElement toObservedElement;
	private ObservedPoint fromObservedPoint;
	private ObservedPoint toObservedPoint;
	@Override
	public String getMetaClass(){
		return "durationObservation";
	}
	public INakedElement getFromObservedElement(){
		return fromObservedElement;
	}
	public void setFromObservedElement(INakedElement fromObservedElement){
		this.fromObservedElement = fromObservedElement;
	}
	public INakedElement getToObservedElement(){
		return toObservedElement;
	}
	public void setToObservedElement(INakedElement toObservedElement){
		this.toObservedElement = toObservedElement;
	}
	public ObservedPoint getFromObservedPoint(){
		return fromObservedPoint;
	}
	public void setFromObservedPoint(ObservedPoint fromObservedPoint){
		this.fromObservedPoint = fromObservedPoint;
	}
	public ObservedPoint getToObservedPoint(){
		return toObservedPoint;
	}
	public void setToObservedPoint(ObservedPoint toObservedPoint){
		this.toObservedPoint = toObservedPoint;
	}
}
