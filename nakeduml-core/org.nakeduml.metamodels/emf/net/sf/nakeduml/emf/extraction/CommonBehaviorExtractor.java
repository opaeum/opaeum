package net.sf.nakeduml.emf.extraction;

import java.util.List;


import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedChangeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTriggerImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.ReceiveSignalEvent;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.nakeduml.runtime.domain.TimeUnit;

public abstract class CommonBehaviorExtractor extends AbstractExtractorFromEmf {
	protected List<INakedTypedElement> getEnvironment(Element node) {
		Behavior activity = getActivity(node);
		// TODO add variables from containing StructuredNodes
		return getEnvironment(activity);
	}

	protected Activity getActivity(Element node) {
		// MUST return activity
		Element e = node;
		while (!(e instanceof Activity) && e != null) {
			e = e.getOwner();
		}
		return (Activity) e;
	}

	// Precondition: the behaviour must have been populated
	protected INakedTrigger buildTrigger(Behavior behaviour, Trigger t) {
		Event event = t.getEvent();
		INakedTrigger trigger=new NakedTriggerImpl();
		initialize(trigger, t, behaviour);
		if (event instanceof SignalEvent) {
			SignalEvent se = (SignalEvent) event;
			trigger.setEvent(getNakedPeer(se.getSignal()));
		} else if (event instanceof ReceiveSignalEvent) {
			ReceiveSignalEvent se = (ReceiveSignalEvent) event;
			trigger.setEvent(getNakedPeer(se.getSignal()));
		} else if (event instanceof CallEvent) {
			CallEvent ce = (CallEvent) event;
			trigger.setEvent(getNakedPeer(ce.getOperation()));
		} else if (event instanceof ReceiveOperationEvent) {
			ReceiveOperationEvent ce = (ReceiveOperationEvent) event;
			trigger.setEvent(getNakedPeer(ce.getOperation()));
		} else if (event instanceof ChangeEvent) {
			INakedBehavior context = (INakedBehavior) getNakedPeer(behaviour);
			// NB!!! TimeEvents are stored under a special id to
			// duplicate it in each context it is used.
			String id = getId(event)+getId(behaviour);
			NakedChangeEventImpl nakedTimeEvent = (NakedChangeEventImpl) workspace.getModelElement(id);
			if (nakedTimeEvent == null) {
				nakedTimeEvent = new NakedChangeEventImpl();
				ChangeEvent ce = (ChangeEvent) event;
				nakedTimeEvent.initialize(id, ce.getName(),true);
				// NB!!! Deviation from UML2 metamodel:
				// We have to make the behaviour the owner to allow for the
				// expression to be implemented correctly
				// Without a behaviour context the expression is contextless and
				// of limited use.
				super.initialize(nakedTimeEvent, ce, behaviour);
				INakedValueSpecification change = getValueSpecification(nakedTimeEvent, ce.getChangeExpression(), OclUsageType.DEF);
				if (change != null) {
					change.setType(getOclLibrary().lookupStandardType("Boolean"));
					nakedTimeEvent.setChangeExpression(change);
					change.setOwnerElement(nakedTimeEvent);
				}
			}
			trigger.setEvent(nakedTimeEvent);
		} else if (event instanceof TimeEvent) {
			INakedBehavior context = (INakedBehavior) getNakedPeer(behaviour);
			// NB!!! TimeEvents are stored under a special id to
			// duplicate it in each context it is used.
			String id = getId(event)+getId(behaviour);
			NakedTimeEventImpl nakedTimeEvent = (NakedTimeEventImpl) workspace.getModelElement(id);
			if (nakedTimeEvent == null) {
				nakedTimeEvent = new NakedTimeEventImpl();
				TimeEvent emfTimeEvent = ((TimeEvent) event);
				nakedTimeEvent.initialize(id, emfTimeEvent.getName(),true);
				// NB!!! Deviation from UML2 metamodel:
				// We have to make the behaviour the owner to allow for the
				// expression to be implemented correctly
				// Without a behaviour context the expression is contextless and
				// of limited use.
				super.initialize(nakedTimeEvent, emfTimeEvent, behaviour);
				INakedValueSpecification when = getValueSpecification(nakedTimeEvent, emfTimeEvent.getWhen(), OclUsageType.DEF);
				if (when != null) {
					when.setType((INakedClassifier) getNakedPeer(emfTimeEvent.getWhen().getType()));
					nakedTimeEvent.setWhen(when);
					when.setOwnerElement(nakedTimeEvent);
				}
				nakedTimeEvent.setRelative(emfTimeEvent.isRelative());
				nakedTimeEvent.setTimeUnit(resolveTimeUnit(emfTimeEvent));
			}
			trigger.setEvent(nakedTimeEvent);
		}
		return trigger;
	}

	private TimeUnit resolveTimeUnit(TimeEvent emfTimeEvent) {
		if (emfTimeEvent.getName() != null) {
			if (emfTimeEvent.getName().toLowerCase().contains("month")) {
				return TimeUnit.CALENDAR_MONTH;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("week")) {
				return TimeUnit.CALENDAR_WEEK;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("business") && emfTimeEvent.getName().toLowerCase().contains("day")) {
				return TimeUnit.BUSINESS_DAY;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("business") && emfTimeEvent.getName().toLowerCase().contains("hour")) {
				return TimeUnit.BUSINESS_HOUR;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("business") && emfTimeEvent.getName().toLowerCase().contains("minute")) {
				return TimeUnit.BUSINESS_MINUTE;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("day")) {
				return TimeUnit.CALENDAR_DAY;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("hour")) {
				return TimeUnit.ACTUAL_HOUR;
			}
			if (emfTimeEvent.getName().toLowerCase().contains("minute")) {
				return TimeUnit.ACTUAL_MINUTE;
			}
		}
		return TimeUnit.BUSINESS_DAY;
	}

	protected INakedClassifier getNearestContext(Behavior b) {
		if (b != null && b.getContext() != null) {
			return (INakedClassifier) getNakedPeer(b.getContext());
		} else {
			return null;
		}
	}

	protected INakedBehavior getOwnedBehavior(INakedElementOwner state, Behavior b) {
		INakedBehavior nakedPeer = (INakedBehavior) getNakedPeer(b);
		if (nakedPeer != null) {
			nakedPeer.setOwnerElement(state);
		}
		return nakedPeer;
	}
}
