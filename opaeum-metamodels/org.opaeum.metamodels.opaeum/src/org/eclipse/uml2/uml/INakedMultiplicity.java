package org.eclipse.uml2.uml;

import nl.klasse.octopus.model.IMultiplicityKind;

public interface INakedMultiplicity extends IMultiplicityKind{
	boolean isRequired();
	boolean isOne();
	boolean isMany();
}
