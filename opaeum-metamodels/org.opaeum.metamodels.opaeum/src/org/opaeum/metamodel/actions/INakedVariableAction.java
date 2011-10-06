package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityVariable;

public interface INakedVariableAction extends INakedAction {
	INakedActivityVariable getVariable();
	void setVariable(INakedActivityVariable v);
}
