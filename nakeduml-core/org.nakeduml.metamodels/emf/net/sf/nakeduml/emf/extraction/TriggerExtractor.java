package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedCallEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedChangeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedSignalEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;

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
			String id = getEventId(emfTrigger);
			NakedEventImpl nakedEvent = (NakedEventImpl) nakedWorkspace.getModelElement(id);
			if(nakedEvent == null){
				if(event instanceof SignalEvent){
					SignalEvent se = (SignalEvent) event;
					NakedSignalEventImpl nse = new NakedSignalEventImpl();
					nse.setSignal((INakedSignal) getNakedPeer(se.getSignal()));
					nakedEvent = nse;
				}else if(event instanceof CallEvent){
					CallEvent ce = (CallEvent) event;
					NakedCallEventImpl nce = new NakedCallEventImpl();
					nce.setOperation((INakedOperation) getNakedPeer(ce.getOperation()));
					nakedEvent = nce;
				}else if(event instanceof ChangeEvent){
					ChangeEvent ce = (ChangeEvent) event;
					NakedChangeEventImpl nakedChangeEvent = new NakedChangeEventImpl();
					if(ce.getChangeExpression() instanceof OpaqueExpression){
						OpaqueExpression oe = (OpaqueExpression) ce.getChangeExpression();
						INakedValueSpecification nvs = new NakedValueSpecificationImpl(buildParsedOclString(((ChangeEvent) event).getChangeExpression(),
								oe.getLanguages(), oe.getBodies(), OclUsageType.DEF));
						super.initialize(nvs, event, event);
						nakedChangeEvent.setChangeExpression(nvs);
					}
				}else if(event instanceof TimeEvent){
					TimeEvent emfTimeEvent = ((TimeEvent) event);
					if(isDeadline(emfTimeEvent)){
						// NB!!! Deviation from UML2 metamodel:
						// Deadlines have been stored previously under its DefinedResponsisibility
						nakedEvent = (NakedEventImpl) getNakedPeer(event);
					}else{
						// NB!!! Deviation from UML2 metamodel:
						// Normal Time Events are stored with a special id under its trigger,
						// effectively duplicating it in each trigger it is used.
						// The action/transition owning the trigger thus provides the behavioral context of the when expression
						AbstractTimeEventImpl nakedTimeEvent = (AbstractTimeEventImpl) nakedWorkspace.getModelElement(id);
						if(nakedTimeEvent == null){
							nakedTimeEvent = new NakedTimeEventImpl();
							nakedTimeEvent.initialize(id, emfTimeEvent.getName(), true);
						}
						super.initialize(nakedTimeEvent, emfTimeEvent, emfTrigger);
						initTimeEvent(emfTimeEvent, nakedTimeEvent);
						nakedTrigger.setEvent(nakedTimeEvent);
					}
				}
			}
			if(nakedEvent != null && !(nakedEvent instanceof INakedDeadline)){
				nakedEvent.initialize(id, event.getName(), true);
				super.initialize(nakedEvent, event, emfTrigger);
				nakedTrigger.setEvent(nakedEvent);
			}
		}
	}
}
