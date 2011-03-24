package org.nakeduml.environment.cdi.unittest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public class MockSignalDispatcher implements ISignalDispatcher {
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();

	public synchronized void sendSignal(Object source, ActiveObject target, AbstractSignal signal) {
		signalsToDispatch.add(new SignalToDispatch(source, target, signal));
	}

	public synchronized void sendSignal(Object source, Collection<? extends ActiveObject> targets, AbstractSignal signal) {
		for (ActiveObject target : targets) {
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}

	public synchronized void reset() {
		this.signalsToDispatch.clear();
	}

	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type) {
		List<SignalToDispatch> result = getSignalsOfType(type);
		return result.isEmpty() ? null : result.get(0);
	}

	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type) {
		List<SignalToDispatch> result = new ArrayList<SignalToDispatch>();
		for (SignalToDispatch signalToDispatch : this.signalsToDispatch) {
			if (type.isInstance(signalToDispatch.getSignal())) {
				result.add(signalToDispatch);
			}
		}
		return result;
	}

	@Override
	public synchronized void deliverAllPendingSignals() {
		ArrayList<SignalToDispatch> signals = new ArrayList<SignalToDispatch>(signalsToDispatch);
		signalsToDispatch.clear();
		for (SignalToDispatch signal : signals) {
			CdiUnitTestEnvironment.getInstance().beforeRequest(signal.getTarget());
			signal.getTarget().processSignal(signal.getSignal());
			CdiUnitTestEnvironment.getInstance().afterRequest(signal.getTarget());
		}
	}

	@Override
	@Deprecated
	public synchronized void deliverPendingSignalsOfType(Class<? extends AbstractSignal> type) {
		for (SignalToDispatch signal : getSignalsOfType(type)) {
			signal.getTarget().processSignal(signal.getSignal());
		}
	}
}
