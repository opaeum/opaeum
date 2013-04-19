package org.eclipse.uml2.uml;
import java.util.Collection;
import java.util.List;

public interface INakedAcceptEventAction extends INakedAction {
	Collection<INakedTrigger> getTriggers();
	List<INakedOutputPin> getResult();
	List<? extends INakedTypedElement> getParameters();
	boolean requiresEventRequest();
	boolean containsTriggerType(Class<? extends INakedEvent> t);
	boolean triggeredByTimeEventsOnly();
}
