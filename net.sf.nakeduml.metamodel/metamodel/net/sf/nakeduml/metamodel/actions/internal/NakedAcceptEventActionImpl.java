package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

public class NakedAcceptEventActionImpl extends NakedActionImpl implements INakedAcceptEventAction {
	private static final long serialVersionUID = -4255852720379805141L;
	private INakedElement event;
	private List<INakedOutputPin> result;

	public INakedElement getEvent() {
		return this.event;
	}

	public void setEvent(INakedElement event) {
		this.event = event;
	}

	public Set<INakedInputPin> getInput() {
		return new HashSet<INakedInputPin>();
	}

	public ActionType getActionType() {
		if (getEvent() instanceof INakedOperation) {
			return ActionType.ACCEPT_CALL_EVENT_ACTION;
		} else if (getEvent() instanceof INakedSignal) {
			return ActionType.ACCEPT_SIGNAL_EVENT_ACTION;
		} else if (getEvent() instanceof INakedTimeEvent) {
			return ActionType.ACCEPT_TIME_EVENT_ACTION;
		} else {
			throw new RuntimeException("Only supported events are: TimeEvent, CallEvent, SignalEvent");
		}
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> out = super.getOwnedElements();
		out.addAll(getResult());
		return out;
	}

	public List<INakedOutputPin> getResult() {
		return this.result;
	}

	public void setResult(List<INakedOutputPin> result) {
		this.result = result;
	}

	public List getParameters() {
		if (getEvent() instanceof INakedOperation) {
			return ((INakedOperation) getEvent()).getArgumentParameters();
		} else if (getEvent() instanceof INakedSignal) {
			return ((INakedSignal) getEvent()).getArgumentParameters();
		}
		return Collections.EMPTY_LIST;
	}
}
