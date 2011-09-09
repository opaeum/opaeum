package net.sf.nakeduml.metamodel.actions;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
public interface INakedAcceptEventAction extends INakedAction {
	Collection<INakedTrigger> getTriggers();
	List<INakedOutputPin> getResult();
	List<? extends INakedTypedElement> getParameters();
	boolean requiresEventRequest();
	boolean containsTriggerType(Class<? extends INakedEvent> t);
	boolean triggeredByTimeEventsOnly();
}
