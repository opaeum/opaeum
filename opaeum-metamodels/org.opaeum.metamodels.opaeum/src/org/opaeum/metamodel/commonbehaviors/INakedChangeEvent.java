package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedValueSpecification;

public interface INakedChangeEvent extends INakedTriggerEvent{
	INakedValueSpecification getChangeExpression();
}
