package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedOutputPin;

public interface INakedReadVariableAction extends INakedVariableAction {
	INakedOutputPin getResult();
	void setResult(INakedOutputPin result);
}
