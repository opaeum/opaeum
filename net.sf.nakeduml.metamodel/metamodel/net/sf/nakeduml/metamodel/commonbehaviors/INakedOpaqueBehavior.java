package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.oclengine.IOclContext;

public interface INakedOpaqueBehavior extends INakedBehavior{
	IOclContext getBodyExpression();
	INakedValueSpecification getBody();
	void setBody(INakedValueSpecification b);
}
