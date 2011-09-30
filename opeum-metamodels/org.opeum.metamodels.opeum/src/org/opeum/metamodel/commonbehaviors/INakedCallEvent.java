package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedOperation;

public interface INakedCallEvent extends INakedMessageEvent{
	INakedOperation getOperation();
}
