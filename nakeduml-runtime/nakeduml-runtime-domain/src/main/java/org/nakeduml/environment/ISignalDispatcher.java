package org.nakeduml.environment;

import java.util.List;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

@Deprecated
public interface ISignalDispatcher{
	void deliverAllPendingSignals();
	void sendSignal(Object source,ActiveObject target,AbstractSignal signal);
	void reset();
	SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type);
	List<? extends SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type);
}
