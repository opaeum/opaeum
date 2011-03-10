package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedRaiseExceptionAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;

public class NakedRaiseExceptionActionImpl extends NakedActionImpl implements INakedRaiseExceptionAction {
	private static final long serialVersionUID = 3691990455746398683L;
	private INakedInputPin exception;

	@Override
	public Collection<INakedInputPin> getInput() {
		ArrayList<INakedInputPin> input = new ArrayList<INakedInputPin>();
		if (exception != null) {
			input.add(exception);
		}
		return input;
	}

	@Override
	public Collection<INakedOutputPin> getOutput() {
		return new ArrayList<INakedOutputPin>();
	}

	@Override
	public ActionType getActionType() {
		return ActionType.RAISE_EXCEPTION_ACTION;
	}

	@Override
	public void setException(INakedInputPin p) {
		this.exception = p;
	}

	@Override
	public INakedInputPin getException() {
		return this.exception;
	}
}
