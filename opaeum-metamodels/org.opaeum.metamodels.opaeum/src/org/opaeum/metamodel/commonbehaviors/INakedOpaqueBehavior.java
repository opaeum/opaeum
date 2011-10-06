package org.opaeum.metamodel.commonbehaviors;

import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.metamodel.core.INakedValueSpecification;

public interface INakedOpaqueBehavior extends INakedBehavior{
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext s);
	INakedValueSpecification getBody();
}
