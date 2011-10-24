package org.opaeum.metamodel.activities;

import nl.klasse.octopus.expressions.IVariableDeclaration;

import org.opaeum.metamodel.core.INakedTypedElement;

public interface INakedActivityVariable extends INakedTypedElement, IVariableDeclaration{

	INakedActivity getActivity();
}
