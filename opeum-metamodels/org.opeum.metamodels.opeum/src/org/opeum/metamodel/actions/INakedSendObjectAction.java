package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedSendObjectAction extends INakedInvocationAction{
	INakedInputPin getRequest();
}
