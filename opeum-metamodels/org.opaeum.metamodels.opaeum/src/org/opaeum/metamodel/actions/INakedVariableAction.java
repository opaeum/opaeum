package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedActivityVariable;

public interface INakedVariableAction extends INakedAction {
	INakedActivityVariable getVariable();
	void setVariable(INakedActivityVariable v);
}
