package org.opaeum.metamodel.commonbehaviors;

import java.util.List;

import org.opaeum.metamodel.core.INakedTypedElement;

public interface INakedMessageEvent extends INakedEvent{
	List<? extends INakedTypedElement> getEventParameters();
}
