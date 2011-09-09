package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;

public class NakedReadVariableActionImpl extends NakedVariableActionImpl implements INakedReadVariableAction {
	INakedOutputPin result;


	public Set<INakedInputPin> getInput() {
		return Collections.emptySet();
	}
	public Collection<INakedOutputPin> getOutput() {
		return Arrays.asList(this.getResult());
	}
	public INakedOutputPin getResult() {
		return this.result;
	}

	public void setResult(INakedOutputPin result) {
		removeOwnedElement(this.result);
		this.result = result;
	}
}
