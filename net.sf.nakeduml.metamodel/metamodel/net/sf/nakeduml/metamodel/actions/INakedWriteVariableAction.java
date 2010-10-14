package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedWriteVariableAction extends INakedVariableAction {
	INakedInputPin getValue();
	void setValue(INakedInputPin p);
}
