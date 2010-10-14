package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedOutputPin;

public interface INakedReadVariableAction extends INakedVariableAction {
	INakedOutputPin getResult();
	void setResult(INakedOutputPin result);
}
