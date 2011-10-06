package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;

public interface INakedCallBehaviorAction extends INakedCallAction{
	void setBehavior(INakedBehavior Behavior);
	INakedBehavior getBehavior();
}