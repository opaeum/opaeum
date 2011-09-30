package org.opeum.metamodel.actions;
import java.util.Collection;
import java.util.List;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.commonbehaviors.INakedEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedTypedElement;
public interface INakedAcceptEventAction extends INakedAction {
	Collection<INakedTrigger> getTriggers();
	List<INakedOutputPin> getResult();
	List<? extends INakedTypedElement> getParameters();
	boolean requiresEventRequest();
	boolean containsTriggerType(Class<? extends INakedEvent> t);
	boolean triggeredByTimeEventsOnly();
}
