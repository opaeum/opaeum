package net.sf.nakeduml.metamodel.core;

import nl.klasse.octopus.model.IMultiplicityKind;

public interface INakedMultiplicity extends IMultiplicityKind{
	boolean isRequired();
	boolean isOne();
	boolean isMany();
}
