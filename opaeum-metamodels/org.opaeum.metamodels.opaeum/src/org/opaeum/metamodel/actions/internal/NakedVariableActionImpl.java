package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedActivityVariable;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedVariableAction;
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
