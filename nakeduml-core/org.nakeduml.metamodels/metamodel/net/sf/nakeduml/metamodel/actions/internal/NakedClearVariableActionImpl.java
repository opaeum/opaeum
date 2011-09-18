package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedClearVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;

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
