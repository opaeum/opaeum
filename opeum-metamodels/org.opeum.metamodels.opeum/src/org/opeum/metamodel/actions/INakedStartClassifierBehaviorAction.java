package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedStartClassifierBehaviorAction extends IActionWithTargetPin{
	INakedInputPin getObject();
}