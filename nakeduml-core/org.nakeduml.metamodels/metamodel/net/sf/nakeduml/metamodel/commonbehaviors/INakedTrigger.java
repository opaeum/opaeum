package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedTrigger extends INakedElement{
	boolean isHumanTrigger();
	void setEvent(INakedElement e);
	INakedElement getEvent();
}
