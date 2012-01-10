package org.opaeum.metamodel.commonbehaviors;

import java.util.Set;

public interface INakedTriggerContainer extends INakedBehavior {

	Set<INakedEvent> getEventsInScopeForClassAsBehavior();

}
