package org.opaeum.emf.extraction;

import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedCallEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedChangeEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedSignalEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		AcceptEventActionExtractor.class,TransitionExtractor.class
},after = {
		AcceptEventActionExtractor.class,TransitionExtractor.class
})
public class TriggerExtractor extends AbstractActionExtractor{
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
					super.initialize(nakedEvent, event, emfTrigger);
				}
			}
			if(nakedEvent != null){
				updateEvent(nakedEvent, emfTrigger, event);
				nakedTrigger.setEvent(nakedEvent);
			}
		}
	}
	protected void updateEvent(NakedEventImpl nakedEvent,Trigger emfTrigger,Event event){
		if(event instanceof SignalEvent){
			SignalEvent se = (SignalEvent) event;
			NakedSignalEventImpl nse = (NakedSignalEventImpl) nakedEvent;
			nse.setSignal((INakedSignal) getNakedPeer(se.getSignal()));
		}else if(event instanceof CallEvent){
			CallEvent ce = (CallEvent) event;
			NakedCallEventImpl nce = (NakedCallEventImpl) nakedEvent;
			nce.setOperation((INakedOperation) getNakedPeer(ce.getOperation()));
		}else if(event instanceof ChangeEvent){
			init(emfTrigger, (ChangeEvent)event,(NakedChangeEventImpl) nakedEvent);
		}else if(event instanceof TimeEvent){
			initTimeEvent((TimeEvent)event, (AbstractTimeEventImpl) nakedEvent);
		}
	}
	protected NakedEventImpl createNewEvent(Event event){
		if(event instanceof SignalEvent){
			return new NakedSignalEventImpl();
		}else if(event instanceof CallEvent){
			return new NakedCallEventImpl();
		}else if(event instanceof ChangeEvent){
			return new NakedChangeEventImpl();
		}else if(event instanceof TimeEvent){
			return new NakedTimeEventImpl();
		} else {
			throw new IllegalStateException("Event of type " + event.getClass().getSimpleName() + " is not supported");
		}
	}
	private void init(Trigger emfTrigger,ChangeEvent ce,NakedChangeEventImpl nakedChangeEvent){
		if(ce.getChangeExpression() instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) ce.getChangeExpression();
			// NB!! remember to start with the Valuespecification's natual id
			String id = getId(oe) + "$" + getId(emfTrigger);
			INakedValueSpecification nvs = (INakedValueSpecification) nakedWorkspace.getModelElement(id);
			if(nvs == null){
				nvs = new NakedValueSpecificationImpl();
				nvs.initialize(id, nvs.getName(), true);
				nakedWorkspace.putModelElement(nvs);
			}
			nvs.setValue(buildParsedOclString(ce.getChangeExpression(), oe.getLanguages(), oe.getBodies(), OclUsageType.DEF));
			nakedChangeEvent.setChangeExpression(nvs);
		}
	}
}
