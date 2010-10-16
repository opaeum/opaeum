package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.IParameterOwner;

public class NakedOpaqueActionImpl extends NakedCallActionImpl implements INakedOpaqueAction {
	private static final long serialVersionUID = -8741446980774863436L;
	private INakedMessageStructure asClass;

	public ActionType getActionType() {
		return ActionType.OPAQUE_ACTION;
	}

	public List<INakedInputPin> getInputValues() {
		return super.getArguments();
	}

	public void setInputValues(List<INakedInputPin> arguments) {
		super.setArguments(arguments);
	}

	public List<INakedOutputPin> getOutputValues() {
		return super.getResult();
	}

	public void setOutputValues(List<INakedOutputPin> results) {
		super.setResult(results);
	}

	@Override
	public INakedMessageStructure getMessageStructure() {
		if (asClass == null) {
			this.asClass = new OpaqueActionMessageStructureImpl(this);
		}
		return asClass;
	}

	// TODO is OpaqueACtion a callAction or a ActionWIthTarget?
	public IParameterOwner getCalledElement() {
		return null;
	}

	public boolean isProcessCall() {
		return false;
	}

	@Override
	public List<INakedPin> getPins() {
		ArrayList<INakedPin> result = new ArrayList<INakedPin>(getInputValues());
		result.addAll(getOutputValues());
		return result;
	}

	@Override
	public boolean isTask() {
		return true;
	}
}
