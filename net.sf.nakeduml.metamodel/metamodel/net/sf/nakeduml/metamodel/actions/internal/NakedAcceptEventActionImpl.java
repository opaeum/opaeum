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
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

public class NakedAcceptEventActionImpl extends NakedActionImpl implements INakedAcceptEventAction {
	private static final long serialVersionUID = -4255852720379805141L;
	private INakedTrigger trigger;

	public INakedTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(INakedTrigger trigger) {
		this.trigger = trigger;
	}

	private List<INakedOutputPin> result;

	public Set<INakedInputPin> getInput() {
		return new HashSet<INakedInputPin>();
	}

	public ActionType getActionType() {
		if (getTrigger() == null) {
			return null;
		} else if (getTrigger().getEvent() instanceof INakedOperation) {
			return ActionType.ACCEPT_CALL_EVENT_ACTION;
		} else if (getTrigger().getEvent() instanceof INakedSignal) {
			return ActionType.ACCEPT_SIGNAL_EVENT_ACTION;
		} else if (getTrigger().getEvent() instanceof INakedTimeEvent) {
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

	public Collection<INakedOutputPin> getOutput() {
		return this.getResult();
	}

	public void setResult(List<INakedOutputPin> result) {
		this.result = result;
	}

	public List getParameters() {
		if (getTrigger() != null) {
			if (getTrigger().getEvent() instanceof INakedOperation) {
				return ((INakedOperation) getTrigger().getEvent()).getArgumentParameters();
			} else if (getTrigger().getEvent() instanceof INakedSignal) {
				return ((INakedSignal) getTrigger().getEvent()).getArgumentParameters();
			}
		}
		return Collections.EMPTY_LIST;
	}
}
