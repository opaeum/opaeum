package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.oclengine.IOclContext;

public interface INakedOclAction extends INakedOpaqueAction{
	INakedOutputPin getReturnPin();
	IOclContext getBodyExpression();
	void setBodyExpression(IOclContext replaceSingleParsedOclString);
	INakedValueSpecification getBody();
}
