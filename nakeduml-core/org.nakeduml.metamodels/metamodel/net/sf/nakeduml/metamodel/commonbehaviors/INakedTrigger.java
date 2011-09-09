package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedTrigger extends INakedElement{
	String getUuid();
	boolean isHumanTrigger();
	void setEvent(INakedEvent e);
	INakedEvent getEvent();
}
