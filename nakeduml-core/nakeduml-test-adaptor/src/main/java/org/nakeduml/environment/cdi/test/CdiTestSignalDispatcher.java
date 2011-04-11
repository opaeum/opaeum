package org.nakeduml.environment.cdi.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.adaptor.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public class CdiTestSignalDispatcher implements ISignalDispatcher{
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
	public synchronized void sendSignal(Object source,ActiveObject target,AbstractSignal signal){
		SignalToDispatch e = new SignalToDispatch(source, target, signal);
		signalsToDispatch.add(e);
	}
	public synchronized void sendSignal(Object source,Collection<? extends ActiveObject> targets,AbstractSignal signal){
		for(ActiveObject target:targets){
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}
	public synchronized void reset(){
		this.signalsToDispatch.clear();
	}
	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type){
		List<SignalToDispatch> result = getSignalsOfType(type);
		return result.isEmpty() ? null : result.get(0);
	}
	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type){
		List<SignalToDispatch> result = new ArrayList<SignalToDispatch>();
		for(SignalToDispatch signalToDispatch:this.signalsToDispatch){
			if(type.isInstance(signalToDispatch.getSignal())){
				result.add(signalToDispatch);
			}
		}
		return result;
	}
	@Override
	public synchronized void deliverAllPendingSignals(){
		ArrayList<SignalToDispatch> signals = new ArrayList<SignalToDispatch>(signalsToDispatch);
		signalsToDispatch.clear();
		for(SignalToDispatch signal:signals){
			ActiveObject target = signal.getTarget();
			CdiTestEnvironment.getInstance().beforeRequest(target);
			signal.prepareForDispatch();
			Session session = CdiTestEnvironment.getInstance().getComponent(Session.class);
			signal.prepareForDelivery(session);
			CdiTestEnvironment.getInstance().afterRequest(target);
			if(target instanceof AbstractEntity){
				CdiTestEnvironment.getInstance().beforeRequest(target);
				signal.getTarget().processSignal(signal.getSignal());
				if(session!=null){
					session.flush();
				}else{
					throw new IllegalStateException("Session null");
				}
				CdiTestEnvironment.getInstance().afterRequest(target);
			}else{
				signal.getTarget().processSignal(signal.getSignal());
				deliverAllPendingSignals();
			}
		}
	}
	@Override
	@Deprecated
	public synchronized void deliverPendingSignalsOfType(Class<? extends AbstractSignal> type){
		for(SignalToDispatch signal:getSignalsOfType(type)){
			signal.getTarget().processSignal(signal.getSignal());
		}
	}
}
