package org.eclipse.uml2.uml;


public interface ICompositionParticipant extends INakedClassifier,INakedComplexStructure{
	boolean hasComposite();
	INakedProperty getEndToComposite();
	void setEndToComposite(INakedProperty artificialProperty);
	boolean isFact();
}
