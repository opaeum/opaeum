package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
/**
 * A common interface for both Transitions from StateMachines and ActivityEdge from Activities.
 * Both have a guard and a context from which the guard should be derived
 */
public interface GuardedFlow extends INakedElement{
	INakedValueSpecification getGuard();
	INakedClassifier getContext();
	INakedBehavior getOwningBehavior();
}
