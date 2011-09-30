package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedOutputPin;

public interface INakedReadVariableAction extends INakedVariableAction {
	INakedOutputPin getResult();
	void setResult(INakedOutputPin result);
}
