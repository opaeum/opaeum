package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.metamodel.actions.INakedVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;

public abstract class NakedVariableActionImpl extends NakedActionImpl implements INakedVariableAction {
	INakedActivityVariable variable;

	public INakedActivityVariable getVariable() {
		return this.variable;
	}

	public void setVariable(INakedActivityVariable variable) {
		this.variable = variable;
	}
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}


}
