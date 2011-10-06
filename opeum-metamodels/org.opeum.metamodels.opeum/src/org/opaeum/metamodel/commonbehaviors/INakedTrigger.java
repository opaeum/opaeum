package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedElement;

public interface INakedTrigger extends INakedElement{
	String getUuid();
	boolean isHumanTrigger();
	void setEvent(INakedEvent e);
	INakedEvent getEvent();
}
