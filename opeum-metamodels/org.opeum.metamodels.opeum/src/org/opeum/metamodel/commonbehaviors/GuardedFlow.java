package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedValueSpecification;
/**
 * A common interface for both Transitions from StateMachines and ActivityEdge from Activities.
 * Both have a guard and a context from which the guard should be derived
 */
public interface GuardedFlow extends INakedElement{
	Object ELSE = new Object();
	INakedValueSpecification getGuard();
	INakedClassifier getContext();
	INakedBehavior getOwningBehavior();
	INakedElement getEffectiveTarget();
	INakedElement getTarget();
	INakedElement getSource();
	boolean hasGuard();
	boolean isElse();

}
