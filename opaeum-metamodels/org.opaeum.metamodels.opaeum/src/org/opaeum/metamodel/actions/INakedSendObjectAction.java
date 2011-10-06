package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;

public interface INakedSendObjectAction extends INakedInvocationAction{
	INakedInputPin getRequest();
}
