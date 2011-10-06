package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedInputPin;

public interface INakedRaiseExceptionAction extends INakedAction {
	void setException(INakedInputPin p);
	INakedInputPin getException();
}
