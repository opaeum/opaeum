package org.opeum.metamodel.core;


public interface INakedConstraint extends INakedElement{

	INakedValueSpecification getSpecification();
	void setSpecification(INakedValueSpecification a);
	INakedElement getConstrainedElement();
	void setConstrainedElement(INakedElement e);
}
