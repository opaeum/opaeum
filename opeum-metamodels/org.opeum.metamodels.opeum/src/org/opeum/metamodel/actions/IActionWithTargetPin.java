package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.core.INakedClassifier;

public interface IActionWithTargetPin extends IActionWithTargetElement{
	INakedInputPin getTarget();

	INakedClassifier getExpectedTargetType();

}
