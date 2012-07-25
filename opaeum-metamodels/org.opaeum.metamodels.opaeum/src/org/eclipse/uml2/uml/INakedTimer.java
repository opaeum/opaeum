package org.eclipse.uml2.uml;


public interface INakedTimer extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	INakedEnumerationLiteral getTimeUnit();
	INakedClassifier getNearestClassifier();

}
