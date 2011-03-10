package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

public interface IActionWithTarget extends INakedAction {
	INakedInputPin getTarget();
	INakedClassifier getExpectedTargetType();
	ITargetElement getTargetElement();
}
