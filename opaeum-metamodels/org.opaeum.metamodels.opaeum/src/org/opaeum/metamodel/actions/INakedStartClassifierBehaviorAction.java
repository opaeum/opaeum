package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;

public interface INakedStartClassifierBehaviorAction extends IActionWithTargetPin{
	INakedInputPin getObject();
}