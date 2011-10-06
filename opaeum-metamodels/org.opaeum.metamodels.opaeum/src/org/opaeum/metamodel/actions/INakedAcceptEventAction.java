package org.opaeum.metamodel.actions;
import java.util.Collection;
import java.util.List;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedTypedElement;
public interface INakedAcceptEventAction extends INakedAction {
	Collection<INakedTrigger> getTriggers();
	List<INakedOutputPin> getResult();
	List<? extends INakedTypedElement> getParameters();
	boolean requiresEventRequest();
	boolean containsTriggerType(Class<? extends INakedEvent> t);
	boolean triggeredByTimeEventsOnly();
}
