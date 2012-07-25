package org.eclipse.uml2.uml;

import nl.klasse.octopus.oclengine.IOclContext;


public interface INakedOclAction extends INakedOpaqueAction{
	INakedOutputPin getReturnPin();
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext replaceSingleParsedOclString);
	INakedValueSpecification getBody();
}
