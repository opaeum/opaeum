package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.core.INakedClassifier;

public interface IActionWithTargetPin extends IActionWithTargetElement{
	INakedInputPin getTarget();

	INakedClassifier getExpectedTargetType();

}
