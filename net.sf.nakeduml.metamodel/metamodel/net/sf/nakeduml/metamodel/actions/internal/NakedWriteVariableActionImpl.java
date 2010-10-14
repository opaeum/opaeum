package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedWriteVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public abstract class NakedWriteVariableActionImpl extends NakedVariableActionImpl implements INakedWriteVariableAction {
	INakedInputPin value;

	public Set<INakedInputPin> getInput() {
		if (getValue() == null) {
			return Collections.emptySet();
		} else {
			return Collections.singleton(getValue());
		}
	}

	public INakedInputPin getValue() {
		return this.value;
	}

	public void setValue(INakedInputPin value) {
		this.value = value;
	}
}
