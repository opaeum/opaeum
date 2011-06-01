package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

public interface IActionWithTargetElement extends INakedAction {
	ITargetElement getTargetElement();
}
