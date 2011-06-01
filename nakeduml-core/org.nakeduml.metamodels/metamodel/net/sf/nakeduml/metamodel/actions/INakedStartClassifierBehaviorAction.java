package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedStartClassifierBehaviorAction extends IActionWithTargetPin{
	INakedInputPin getObject();
}