package net.sf.nakeduml.metamodel.actions.internal;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedRemoveVariableValueAction;

public class NakedRemoveVariableValueActionImpl extends NakedWriteVariableActionImpl implements INakedRemoveVariableValueAction{
	@Override
	public ActionType getActionType(){
		return ActionType.REMOVE_VARIABLE_VALUE_ACTION;
	}
}
