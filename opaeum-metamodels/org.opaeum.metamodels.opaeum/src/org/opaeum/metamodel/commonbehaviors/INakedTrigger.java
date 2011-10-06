package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedElement;

public interface INakedTrigger extends INakedElement{
	String getUuid();
	boolean isHumanTrigger();
	void setEvent(INakedEvent e);
	INakedEvent getEvent();
}
