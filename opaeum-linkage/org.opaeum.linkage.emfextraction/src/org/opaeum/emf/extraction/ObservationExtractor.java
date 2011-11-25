package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.ObservedPoint;
import org.opaeum.metamodel.commonbehaviors.internal.NakedDurationObservationImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeObservationImpl;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		ActivityEdgeExtractor.class,TransitionExtractor.class
},after = {
		ActivityEdgeExtractor.class,TransitionExtractor.class
})
public class ObservationExtractor extends CommonBehaviorExtractor{
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof DurationObservation){
			DurationObservation dob = (DurationObservation) e;
			if(dob.getEvents().size() == 2 && dob.getFirstEvents().size() == 2){
				return super.createElementFor(e, peerClass);
			}else{
				// TODO validation
				return null;
			}
		}
		return super.createElementFor(e, peerClass);
	}
	@VisitBefore
	public void visitTimeObservation(TimeObservation eto,NakedTimeObservationImpl nto){
		nto.setObservedElement(getNakedPeer(eto.getEvent()));
		boolean firstEvent = eto.isFirstEvent();
		if(firstEvent){
			nto.setObservedPoint(ObservedPoint.ENTRY);
		}else{
			nto.setObservedPoint(ObservedPoint.ENTRY);
		}
	}
	@VisitBefore
	public void visitDurationObservation(DurationObservation eto,NakedDurationObservationImpl nto){
		nto.setFromObservedElement(getNakedPeer(eto.getEvents().get(0)));
		if(eto.getFirstEvents().get(0)){
			nto.setFromObservedPoint(ObservedPoint.ENTRY);
		}else{
			nto.setFromObservedPoint(ObservedPoint.ENTRY);
		}
		nto.setToObservedElement(getNakedPeer(eto.getEvents().get(1)));
		if(eto.getFirstEvents().get(1)){
			nto.setToObservedPoint(ObservedPoint.ENTRY);
		}else{
			nto.setToObservedPoint(ObservedPoint.ENTRY);
		}
	}
}
