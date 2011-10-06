package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedOperation;

public interface INakedCallEvent extends INakedMessageEvent{
	INakedOperation getOperation();
}
