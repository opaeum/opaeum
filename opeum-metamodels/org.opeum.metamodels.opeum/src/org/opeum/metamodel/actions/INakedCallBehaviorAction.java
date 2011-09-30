package org.opeum.metamodel.actions;

import org.opeum.metamodel.commonbehaviors.INakedBehavior;

public interface INakedCallBehaviorAction extends INakedCallAction{
	void setBehavior(INakedBehavior Behavior);
	INakedBehavior getBehavior();
}