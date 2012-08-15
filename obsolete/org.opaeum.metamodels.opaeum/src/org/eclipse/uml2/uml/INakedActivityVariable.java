package org.eclipse.uml2.uml;

import nl.klasse.octopus.expressions.IVariableDeclaration;


public interface INakedActivityVariable extends INakedTypedElement, IVariableDeclaration{

	INakedActivity getActivity();
}
