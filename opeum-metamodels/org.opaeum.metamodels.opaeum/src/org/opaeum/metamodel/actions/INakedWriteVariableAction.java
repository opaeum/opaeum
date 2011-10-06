package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedWriteVariableAction extends INakedVariableAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin p);
	public INakedInputPin getInsertAt();
	public void setInsertAt(INakedInputPin insertAt);
}
