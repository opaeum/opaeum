package org.nakeduml.tinker.emf.extraction;

import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.emf.extraction.AcceptEventActionExtractor;
import org.opaeum.emf.extraction.EmfExtractionPhase;
import org.opaeum.emf.extraction.TransitionExtractor;
import org.opaeum.emf.extraction.TriggerExtractor;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.internal.NakedEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import org.opaeum.metamodel.core.INakedElement;

@StepDependency(phase = EmfExtractionPhase.class, requires = { AcceptEventActionExtractor.class, TransitionExtractor.class }, after = { AcceptEventActionExtractor.class,
		TransitionExtractor.class }, replaces = TriggerExtractor.class)
public class TinkerTriggerExtractor extends TriggerExtractor {

	@VisitBefore
	public void buildTrigger(Trigger emfTrigger,NakedTriggerImpl nakedTrigger){
		if(emfTrigger.getEvent() != null){
			// NB!!! Deviation from UML2 metamodel:
			// Events are stored with a special id under its trigger,
			// effectively duplicating it in each trigger it is used.
			// The action/transition owning the trigger thus provides the behavioral context of any owned VAlueSpecifications
			Event event = emfTrigger.getEvent();
			String eventId = getEventId(emfTrigger);
			NakedEventImpl nakedEvent = (NakedEventImpl) nakedWorkspace.getModelElement(eventId);
			if(nakedEvent == null){
				// Try directly on the event (Could be an event NOT stored under the trigger like task events
				nakedEvent = (NakedEventImpl) getNakedPeer(event);
			}
			if(nakedEvent == null){
				nakedEvent = createNewEvent(event);
				if(nakedEvent != null){
					nakedEvent.initialize(eventId, event.getName(), true);
					super.initialize(nakedEvent, event, event.getOwner());
				}
			}
			if(nakedEvent != null){
				updateEvent(nakedEvent, emfTrigger, event);
				nakedTrigger.setEvent(nakedEvent);
			}
			System.out.println(nakedEvent.getOwnerElement());
		}
	}

}
