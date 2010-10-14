package net.sf.nakeduml.metamodel.core;

import nl.klasse.octopus.model.IClassifier;

public interface INakedTypedElement extends INakedElement{
	INakedClassifier getNakedBaseType();
	void setBaseType(INakedClassifier bt);
	boolean isOrdered();
	boolean isUnique();
	IClassifier getType();
	void setType(IClassifier type);
	INakedMultiplicity getNakedMultiplicity();
	
}
