package org.eclipse.uml2.uml;
import java.util.List;

public interface INakedInvocationAction extends INakedAction,IActionWithTargetElement {
	List<INakedInputPin> getArguments();
	void setArguments(List<INakedInputPin> arguments);
}
