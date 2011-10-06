package org.opaeum.metamodel.actions;

import java.util.List;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedInputPin;

public interface INakedReplyAction extends INakedAction{
	INakedInputPin getReturnInfo();
	INakedAcceptCallAction getCause();
	List<INakedInputPin> getReplyValues();
}
