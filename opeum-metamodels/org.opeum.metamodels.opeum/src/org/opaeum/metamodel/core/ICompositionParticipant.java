package org.opeum.metamodel.core;


public interface ICompositionParticipant extends INakedClassifier{
	boolean hasComposite();
	INakedProperty getEndToComposite();
	void setEndToComposite(INakedProperty artificialProperty);
}
