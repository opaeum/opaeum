package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;

public interface INakedVariableAction extends INakedAction {
	INakedActivityVariable getVariable();
	void setVariable(INakedActivityVariable v);
}
