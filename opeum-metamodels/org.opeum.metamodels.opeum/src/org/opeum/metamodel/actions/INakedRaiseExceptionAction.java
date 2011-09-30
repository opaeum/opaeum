package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedRaiseExceptionAction extends INakedAction {
	void setException(INakedInputPin p);
	INakedInputPin getException();
}
