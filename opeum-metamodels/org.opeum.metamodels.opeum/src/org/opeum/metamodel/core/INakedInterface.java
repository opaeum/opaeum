package org.opeum.metamodel.core;

import java.util.Collection;

import nl.klasse.octopus.model.IInterface;

import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.commonbehaviors.INakedReception;

public interface INakedInterface extends ICompositionParticipant,IInterface{
	void addImplementingClassifier(INakedBehavioredClassifier c);
	Collection<INakedBehavioredClassifier> getImplementingClassifiers();
	void removeImplementingClassifier(INakedBehavioredClassifier implementingClassifier);
	Collection<INakedReception> getOwnedReceptions();
	Collection<INakedReception> getEffectiveReceptions();
}
