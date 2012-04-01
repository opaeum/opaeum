package org.opaeum.metamodel.core;

import java.util.Collection;


public interface INakedConstraint extends INakedElement{

	INakedValueSpecification getSpecification();
	void setSpecification(INakedValueSpecification a);
	Collection<INakedElement> getConstrainedElements();
	void setConstrainedElements(Collection<INakedElement> e);
}
