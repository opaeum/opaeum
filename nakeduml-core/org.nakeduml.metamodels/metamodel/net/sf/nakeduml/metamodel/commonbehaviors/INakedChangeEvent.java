package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public interface INakedChangeEvent extends INakedTriggerEvent{
	INakedValueSpecification getChangeExpression();
}
