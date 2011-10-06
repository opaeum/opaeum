package org.opaeum.metamodel.core;


public interface IModifiableTypedElement extends INakedTypedElement{

	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);

	void setIsOrdered(boolean b);

	void setIsUnique(boolean b);
}
