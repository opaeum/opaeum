package net.sf.nakeduml.metamodel.actions.internal;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedAddVariableValueAction;

public class NakedAddVariableValueActionImpl extends NakedWriteVariableActionImpl implements INakedAddVariableValueAction{
	private boolean replaceAll;
	@Override
	public ActionType getActionType(){
		return ActionType.ADD_VARIABLE_VALUE_ACTION;
	}
	@Override
	public boolean isReplaceAll(){
		return replaceAll;
	}
	@Override
	public void setReplaceAll(boolean b){
		this.replaceAll = b;
	}
}
