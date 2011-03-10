package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedClassifier;

public interface INakedBehavioredClassifier extends INakedClassifier{
	Collection<INakedReception> getOwnedReception();
	Collection<? extends INakedReception> getEffectiveReceptions();
	Collection<? extends INakedBehavior> getOwnedBehaviors();
	Collection<? extends INakedBehavior> getEffectiveBehaviors();

}
