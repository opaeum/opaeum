package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedOperation;

public interface INakedAcceptCallAction extends INakedAcceptEventAction{
	INakedOutputPin getReturnInfo();

	INakedOperation getOperation();
	INakedReplyAction getReplyAction();
}
