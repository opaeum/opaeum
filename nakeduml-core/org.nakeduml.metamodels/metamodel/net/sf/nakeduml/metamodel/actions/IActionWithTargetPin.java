package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

public interface IActionWithTargetPin extends IActionWithTargetElement{
	INakedInputPin getTarget();

	INakedClassifier getExpectedTargetType();

}
