package org.opeum.metamodel.commonbehaviors;

import java.util.List;

import org.opeum.metamodel.core.INakedTypedElement;

public interface INakedMessageEvent extends INakedEvent{
	List<? extends INakedTypedElement> getEventParameters();
}
