package org.opaeum.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import org.eclipse.uml2.uml.INakedClearVariableAction;
import org.eclipse.uml2.uml.INakedInputPin;

public class NakedClearVariableActionImpl extends NakedVariableActionImpl implements INakedClearVariableAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4951462475153287793L;

	@Override
	public Set<INakedInputPin> getInput(){
		return Collections.emptySet();
	}
}
