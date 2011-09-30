package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedAction;

public interface IActionWithTargetElement extends INakedAction {
	ITargetElement getTargetElement();
}
