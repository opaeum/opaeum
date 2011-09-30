package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.oclengine.IOclContext;

public interface INakedOpaqueBehavior extends INakedBehavior{
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext s);
	INakedValueSpecification getBody();
}
