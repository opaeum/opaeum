package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedTriggerContainer extends INakedBehavior {
	/**
	 * Returns an array containing all the operations and signals that could
	 * possibly trigger a transition in this state
	 */
	List<INakedElement> getAllMessageTriggers();
}