package net.sf.nakeduml.metamodel.actions;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
public interface INakedInvocationAction extends INakedAction,IActionWithTargetElement {
	List<INakedInputPin> getArguments();
	void setArguments(List<INakedInputPin> arguments);
}
