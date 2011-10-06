package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.core.INakedOperation;

public interface INakedAcceptCallAction extends INakedAcceptEventAction{
	INakedOutputPin getReturnInfo();

	INakedOperation getOperation();
	INakedReplyAction getReplyAction();
}
