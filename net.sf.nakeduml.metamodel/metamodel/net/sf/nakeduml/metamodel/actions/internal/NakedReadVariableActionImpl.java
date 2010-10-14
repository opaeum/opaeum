package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;

public class NakedReadVariableActionImpl extends NakedVariableActionImpl implements INakedReadVariableAction {
	INakedOutputPin result;

	public ActionType getActionType() {
		return ActionType.READ_VARIABLE_ACTION;
	}

	public Set<INakedInputPin> getInput() {
		return Collections.emptySet();
	}

	public INakedOutputPin getResult() {
		return this.result;
	}

	public void setResult(INakedOutputPin result) {
		this.result = result;
	}
}
