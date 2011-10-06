package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedAction;

public interface IActionWithTargetElement extends INakedAction {
	ITargetElement getTargetElement();
}
