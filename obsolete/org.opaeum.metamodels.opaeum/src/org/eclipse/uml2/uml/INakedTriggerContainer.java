package org.eclipse.uml2.uml;

import java.util.Set;

public interface INakedTriggerContainer extends INakedBehavior {

	Set<INakedEvent> getEventsInScopeForClassAsBehavior();

}
