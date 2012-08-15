package org.eclipse.uml2.uml;


public interface INakedTrigger extends INakedElement{
	String getUuid();
	boolean isHumanTrigger();
	void setEvent(INakedEvent e);
	INakedEvent getEvent();
}
