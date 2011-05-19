package org.nakeduml.environment;

import java.util.Collection;
import java.util.List;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public interface ISignalDispatcher {
	void deliverAllPendingSignals();

	void deliverPendingSignalsOfType(Class<? extends AbstractSignal> type);

	void sendSignal(Object source, ActiveObject target, AbstractSignal signal);

	void sendSignal(Object source, Collection<? extends ActiveObject> targets, AbstractSignal signal);

	void reset();

	SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type);

	List<? extends SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type);
}
