package org.opaeum.metamodel.core;

import java.util.Collection;

import nl.klasse.octopus.model.IInterface;

import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;

public interface INakedInterface extends ICompositionParticipant,IInterface{
	void addImplementingClassifier(INakedBehavioredClassifier c);
	Collection<INakedBehavioredClassifier> getImplementingClassifiers();
	void removeImplementingClassifier(INakedBehavioredClassifier implementingClassifier);
	Collection<INakedReception> getOwnedReceptions();
	Collection<INakedReception> getEffectiveReceptions();
	boolean hasReceptionFor(INakedSignal signal);
}
