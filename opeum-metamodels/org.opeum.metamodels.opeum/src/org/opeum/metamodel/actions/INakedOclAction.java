package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.oclengine.IOclContext;

public interface INakedOclAction extends INakedOpaqueAction{
	INakedOutputPin getReturnPin();
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext replaceSingleParsedOclString);
	INakedValueSpecification getBody();
}
