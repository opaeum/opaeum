package org.opeum.metamodel.actions;

import java.util.List;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedReplyAction extends INakedAction{
	INakedInputPin getReturnInfo();
	INakedAcceptCallAction getCause();
	List<INakedInputPin> getReplyValues();
}
