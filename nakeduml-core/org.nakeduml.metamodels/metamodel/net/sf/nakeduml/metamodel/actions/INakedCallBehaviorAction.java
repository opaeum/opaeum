package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;

public interface INakedCallBehaviorAction extends INakedCallAction{
	void setBehavior(INakedBehavior Behavior);
	INakedBehavior getBehavior();
}