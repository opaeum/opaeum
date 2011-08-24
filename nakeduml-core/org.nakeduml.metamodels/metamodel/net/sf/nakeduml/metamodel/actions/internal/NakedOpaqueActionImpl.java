package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;

public abstract class NakedOpaqueActionImpl extends NakedActionImpl implements INakedOpaqueAction{
	private static final long serialVersionUID = -8741446980774863436L;
	private List<INakedInputPin> inputValues;
	@Override
	public Collection<INakedInputPin> getInput(){
		return inputValues;
	}
	public void setInputValues(List<INakedInputPin> inputValues){
		removePins(this.inputValues);
		this.inputValues = inputValues;
	}
	@Override
	public List<INakedInputPin> getInputValues(){
		return inputValues;
	}
	public ActionType getActionType(){
		return ActionType.OPAQUE_ACTION;
	}
}
