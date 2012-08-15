package org.eclipse.uml2.uml;


public interface INakedCallBehaviorAction extends INakedCallAction{
	void setBehavior(INakedBehavior Behavior);
	INakedBehavior getBehavior();
}