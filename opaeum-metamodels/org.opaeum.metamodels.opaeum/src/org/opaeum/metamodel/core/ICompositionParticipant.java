package org.opaeum.metamodel.core;


public interface ICompositionParticipant extends INakedClassifier,INakedComplexStructure{
	boolean hasComposite();
	INakedProperty getEndToComposite();
	void setEndToComposite(INakedProperty artificialProperty);
}
