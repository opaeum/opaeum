package org.nakeduml.environment;

import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public interface ISignalDispatcher {
	public void sendSignal(Object source, ActiveObject target, AbstractSignal signal);

	public void sendSignal(Object source, Collection<? extends ActiveObject> targets, AbstractSignal signal);

	public void reset();

	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type);

	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type);
}
