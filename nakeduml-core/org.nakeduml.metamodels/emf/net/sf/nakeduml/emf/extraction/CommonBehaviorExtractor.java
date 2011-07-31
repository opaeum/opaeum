package net.sf.nakeduml.emf.extraction;

import java.util.List;

import net.sf.nakeduml.metamodel.bpm.internal.NakedDeadlineImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedChangeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.ReceiveSignalEvent;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.nakeduml.runtime.domain.TimeUnit;

public abstract class CommonBehaviorExtractor extends AbstractExtractorFromEmf{
	protected List<INakedTypedElement> getEnvironment(Element node){
		Behavior activity = getActivity(node);
		// TODO add variables from containing StructuredNodes
		return getEnvironment(activity);
	}
	protected Activity getActivity(Element node){
		// MUST return activity
		Element e = node;
		while(!(e instanceof Activity) && e != null){
			e = e.getOwner();
		}
		return (Activity) e;
	}
	// Precondition: the behaviour must have been populated
	protected INakedTrigger buildTrigger(Behavior behaviour,Trigger t){
		Event event = t.getEvent();
		INakedTrigger trigger = new NakedTriggerImpl();
		initialize(trigger, t, t.getOwner());
		if(event instanceof SignalEvent){
			SignalEvent se = (SignalEvent) event;
			trigger.setEvent(getNakedPeer(se.getSignal()));
		}else if(event instanceof ReceiveSignalEvent){
			ReceiveSignalEvent se = (ReceiveSignalEvent) event;
			trigger.setEvent(getNakedPeer(se.getSignal()));
		}else if(event instanceof CallEvent){
			CallEvent ce = (CallEvent) event;
			trigger.setEvent(getNakedPeer(ce.getOperation()));
		}else if(event instanceof ReceiveOperationEvent){
			ReceiveOperationEvent ce = (ReceiveOperationEvent) event;
			trigger.setEvent(getNakedPeer(ce.getOperation()));
		}else if(event instanceof ChangeEvent){
			// NB!!! Deviation from UML2 metamodel:
			// Change Events are stored with a special id under its trigger,
			// effectively duplicating it in each trigger it is used.
			// The action/transition owning the trigger thus provides the behvioral context of the change expression
			String id = getEventId(t);
			NakedChangeEventImpl nakedTimeEvent = new NakedChangeEventImpl();
			ChangeEvent ce = (ChangeEvent) event;
			nakedTimeEvent.initialize(id, ce.getName(), true);
			super.initialize(nakedTimeEvent, ce, t);
			INakedValueSpecification change = getValueSpecification(nakedTimeEvent, ce.getChangeExpression(), OclUsageType.DEF);
			if(change != null){
				change.setType(getOclLibrary().lookupStandardType("Boolean"));
				nakedTimeEvent.setChangeExpression(change);
				change.setOwnerElement(nakedTimeEvent);
			}
			trigger.setEvent(nakedTimeEvent);
		}else if(event instanceof TimeEvent){
			TimeEvent emfTimeEvent = ((TimeEvent) event);
			String id = null;
			if(isDeadline(emfTimeEvent)){
				// NB!!! Deviation from UML2 metamodel:
				// Deadlines have been stored previously under its DefinedResponsisibility
				trigger.setEvent(getNakedPeer(emfTimeEvent));
			}else{
				// NB!!! Deviation from UML2 metamodel:
				// Normal Time Events are stored with a special id under its trigger,
				// effectively duplicating it in each trigger it is used.
				// The action/transition owning the trigger thus provides the behavioral context of the when expression
				id = getEventId(t);
				NakedTimeEventImpl nakedTimeEvent = new NakedTimeEventImpl();
				nakedTimeEvent.initialize(id, emfTimeEvent.getName(), true);
				super.initialize(nakedTimeEvent, emfTimeEvent, t);
				initTimeEvent(emfTimeEvent, nakedTimeEvent);
				trigger.setEvent(nakedTimeEvent);
			}
		}
		return trigger;
	}
	protected INakedClassifier getNearestContext(Behavior b){
		if(b != null && b.getContext() != null){
			return (INakedClassifier) getNakedPeer(b.getContext());
		}else{
			return null;
		}
	}
	protected INakedBehavior getOwnedBehavior(INakedElementOwner state,Behavior b){
		INakedBehavior nakedPeer = (INakedBehavior) getNakedPeer(b);
		if(nakedPeer != null){
			nakedPeer.setOwnerElement(state);
		}
		return nakedPeer;
	}
}
