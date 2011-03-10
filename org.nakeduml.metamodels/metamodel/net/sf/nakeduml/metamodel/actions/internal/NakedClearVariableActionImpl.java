package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedClearVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public class NakedClearVariableActionImpl extends NakedVariableActionImpl implements INakedClearVariableAction{
	@Override
	public ActionType getActionType(){
		return ActionType.CLEAR_VARIABLE_ACTION;
	}
	@Override
	public Set<INakedInputPin> getInput(){
		return Collections.emptySet();
	}
}
