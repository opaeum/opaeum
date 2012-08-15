package org.eclipse.uml2.uml;

import nl.klasse.octopus.oclengine.IOclContext;


public interface INakedOpaqueBehavior extends INakedBehavior{
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext s);
	INakedValueSpecification getBody();
}
