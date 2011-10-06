package org.opeum.metamodel.commonbehaviors;

import java.util.Set;

public interface INakedTriggerContainer extends INakedBehavior {
	/**
	 * Returns an array containing all the operations and signals that could
	 * possibly trigger a transition in this state
	 */
	Set<INakedMessageEvent> getAllMessageEvents();

	Set<INakedEvent> getAllEvents();

}
