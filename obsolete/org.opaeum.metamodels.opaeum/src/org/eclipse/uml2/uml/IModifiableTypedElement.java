package org.eclipse.uml2.uml;


public interface IModifiableTypedElement extends INakedTypedElement{

	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);

	void setIsOrdered(boolean b);

	void setIsUnique(boolean b);
}
