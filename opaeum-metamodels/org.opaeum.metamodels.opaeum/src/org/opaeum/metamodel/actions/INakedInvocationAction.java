package org.opaeum.metamodel.actions;
import java.util.List;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedInputPin;
public interface INakedInvocationAction extends INakedAction,IActionWithTargetElement {
	List<INakedInputPin> getArguments();
	void setArguments(List<INakedInputPin> arguments);
}
