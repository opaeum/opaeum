package net.sf.nakeduml.metamodel.core;

import java.util.Collection;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import nl.klasse.octopus.model.IInterface;

public interface INakedInterface extends ICompositionParticipant,IInterface{
	boolean isResponsibility();
	void setIsResponsibility(boolean b);
	void addImplementingClassifier(INakedClassifier c);
	Collection<INakedClassifier> getImplementingClassifiers();
	void removeImplementingClassifier(INakedClassifier implementingClassifier);
	Collection<INakedReception> getOwnedReception();
}
