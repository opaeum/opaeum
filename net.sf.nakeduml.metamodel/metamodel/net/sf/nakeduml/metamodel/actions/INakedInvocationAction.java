package net.sf.nakeduml.metamodel.actions;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;
public interface INakedInvocationAction extends IActionWithTarget {
	List<INakedInputPin> getArguments();
	void setArguments(List<INakedInputPin> arguments);
	INakedInputPin getTarget();
	void setTarget(INakedInputPin target);
	boolean hasTarget();
	boolean isTask();
}
