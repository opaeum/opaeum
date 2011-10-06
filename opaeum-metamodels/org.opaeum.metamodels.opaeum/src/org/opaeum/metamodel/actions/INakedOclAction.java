package org.opaeum.metamodel.actions;

import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.core.INakedValueSpecification;

public interface INakedOclAction extends INakedOpaqueAction{
	INakedOutputPin getReturnPin();
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext replaceSingleParsedOclString);
	INakedValueSpecification getBody();
}
