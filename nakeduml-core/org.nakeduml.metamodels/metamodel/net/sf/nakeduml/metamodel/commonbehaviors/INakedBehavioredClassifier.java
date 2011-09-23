package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface INakedBehavioredClassifier extends INakedClassifier{
	INakedBehavior getClassifierBehavior();
	void setClassifierBehavior(INakedBehavior behavior);
	Collection<INakedReception> getOwnedReceptions();
	Collection<? extends INakedReception> getEffectiveReceptions();
	Collection<? extends INakedBehavior> getOwnedBehaviors();
	Collection<? extends INakedBehavior> getEffectiveBehaviors();
	Collection<? extends INakedInterfaceRealization> getInterfaceRealizations();
	/**
	 * Returns a list of attributes (including navigable association ends) that are the the direct responsibility of this class to implement.
	 * It therefore includes all the attributes specified as part of the contract of all interfaces directly realized and not realized by a
	 * superclass
	 * 
	 */
	public abstract Set<INakedProperty> getDirectlyImplementedAttributes();
	/**
	 * Returns a list of operations that are the the direct responsibility of this class to implement.
	 * It therefore includes all the operations specified as part of the contract of all interfaces directly realized and not realized by a
	 * superclass
	 * 
	 */
	public abstract Set<INakedOperation> getDirectlyImplementedOperations();
	boolean hasReceptionFor(INakedSignal signal);
	Collection<? extends INakedReception> getDirectlyImplementedReceptions();
}
