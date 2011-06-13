package net.sf.nakeduml.metamodel.core;

import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;

public interface IModifiableTypedElement extends INakedTypedElement{

	void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl);

	void setIsOrdered(boolean b);

	void setIsUnique(boolean b);
}
