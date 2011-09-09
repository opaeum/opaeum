package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;

public interface INakedBehavioredClassifier extends INakedClassifier{
	INakedBehavior getClassifierBehavior();
	void setClassifierBehavior(INakedBehavior behavior);
	Collection<INakedReception> getOwnedReceptions();
	Collection<? extends INakedReception> getEffectiveReceptions();
	Collection<? extends INakedBehavior> getOwnedBehaviors();
	Collection<? extends INakedBehavior> getEffectiveBehaviors();
	Collection<? extends INakedInterfaceRealization> getInterfaceRealizations();

	boolean hasReceptionFor(INakedSignal signal);

}
