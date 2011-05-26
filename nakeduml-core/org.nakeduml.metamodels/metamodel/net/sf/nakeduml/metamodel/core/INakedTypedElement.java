package net.sf.nakeduml.metamodel.core;

import nl.klasse.octopus.model.IClassifier;

public interface INakedTypedElement extends INakedElement, INakedMultiplicityElement{
	INakedClassifier getNakedBaseType();
	void setBaseType(INakedClassifier bt);
	IClassifier getType();
	void setType(IClassifier type);
	
}
