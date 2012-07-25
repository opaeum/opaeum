package org.eclipse.uml2.uml;
public interface INakedValuePin extends INakedInputPin, INakedElementOwner {
	INakedValueSpecification getValue();
	void setValue(INakedValueSpecification value);
}
