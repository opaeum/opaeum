package org.opeum.metamodel.actions;
import java.util.List;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedInputPin;
public interface INakedInvocationAction extends INakedAction,IActionWithTargetElement {
	List<INakedInputPin> getArguments();
	void setArguments(List<INakedInputPin> arguments);
}
