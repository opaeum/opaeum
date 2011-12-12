package org.opaeum.metamodel.core;

import nl.klasse.octopus.model.IClassifier;

public interface INakedTypedElement extends INakedMultiplicityElement{
	INakedClassifier getNakedBaseType();
	void setBaseType(INakedClassifier bt);
	IClassifier getType();
	void setType(IClassifier type);
	boolean isMeasure();
	boolean isDimension();
}
