package org.opeum.metamodel.actions;

import nl.klasse.octopus.oclengine.IOclContext;

import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedValueSpecification;

public interface INakedOclAction extends INakedOpaqueAction{
	INakedOutputPin getReturnPin();
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext replaceSingleParsedOclString);
	INakedValueSpecification getBody();
}
