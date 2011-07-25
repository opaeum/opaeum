package net.sf.nakeduml.metamodel.core;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;

public interface INakedValueSpecification extends INakedElement {
	String getUuid();
	boolean isLiteral();
	boolean isOclValue();
	boolean isValidOclValue();
	boolean isInstanceValue();
	boolean booleanValue();
	String stringValue();
	double realValue();
	int intValue();
	boolean isUnlimited();
	IOclContext getOclValue();
	INakedInstanceSpecification getInstance();
	boolean isElementReference();
	void setValue(Object object);
	Object getValue();
	IClassifier getType();
	void setType(IClassifier c);
}
