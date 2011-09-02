package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;

public interface IActionWithTargetElement extends INakedAction {
	ITargetElement getTargetElement();
}
