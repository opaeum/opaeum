package org.eclipse.uml2.uml;

import java.util.Collection;

import nl.klasse.octopus.model.IInterface;


public interface INakedInterface extends ICompositionParticipant,IInterface{
	void addImplementingClassifier(INakedBehavioredClassifier c);
	Collection<INakedBehavioredClassifier> getImplementingClassifiers();
	void removeImplementingClassifier(INakedBehavioredClassifier implementingClassifier);
	Collection<INakedReception> getOwnedReceptions();
	Collection<INakedReception> getEffectiveReceptions();
	boolean hasReceptionFor(INakedSignal signal);
}
