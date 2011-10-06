package org.opeum.metamodel.commonbehaviors;

import nl.klasse.octopus.oclengine.IOclContext;

import org.opeum.metamodel.core.INakedValueSpecification;

public interface INakedOpaqueBehavior extends INakedBehavior{
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext s);
	INakedValueSpecification getBody();
}
