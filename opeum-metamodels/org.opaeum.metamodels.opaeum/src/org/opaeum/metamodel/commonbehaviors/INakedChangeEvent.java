package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedValueSpecification;

public interface INakedChangeEvent extends INakedTriggerEvent{
	INakedValueSpecification getChangeExpression();
}
