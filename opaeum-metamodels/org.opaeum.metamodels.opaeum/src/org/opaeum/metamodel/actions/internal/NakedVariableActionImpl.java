package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

public abstract class NakedVariableActionImpl extends NakedActionImpl implements INakedVariableAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3269954323276666310L;
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
