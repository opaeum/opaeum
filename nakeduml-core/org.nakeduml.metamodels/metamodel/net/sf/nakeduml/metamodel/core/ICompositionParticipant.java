package net.sf.nakeduml.metamodel.core;

import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;

public interface ICompositionParticipant extends INakedClassifier{
	boolean hasComposite();
	INakedProperty getEndToComposite();
	void setEndToComposite(INakedProperty artificialProperty);
}
