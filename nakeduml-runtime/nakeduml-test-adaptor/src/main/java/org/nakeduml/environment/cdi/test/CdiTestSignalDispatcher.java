package org.nakeduml.environment.cdi.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.adaptor.SignalToDispatch;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IActiveObject;

public class CdiTestSignalDispatcher implements ISignalDispatcher{
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
	public void sendSignal(Object source,IActiveObject target,AbstractSignal signal){
		SignalToDispatch e = new SignalToDispatch(source, target, signal);
		signalsToDispatch.add(e);
	}
	public void sendSignal(Object source,Collection<? extends IActiveObject> targets,AbstractSignal signal){
		for(IActiveObject target:targets){
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}
	public void reset(){
		this.signalsToDispatch.clear();
	}
	public org.nakeduml.environment.SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type){
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
	public void deliverAllPendingSignals(){
		ArrayList<SignalToDispatch> signals = new ArrayList<SignalToDispatch>(signalsToDispatch);
		signalsToDispatch.clear();
		for(org.nakeduml.environment.SignalToDispatch signal:signals){
			IActiveObject target = signal.getTarget();
			CdiTestEnvironment.getInstance().beforeRequest(target);
			Session session = CdiTestEnvironment.getInstance().getComponent(Session.class);
			signal.prepareForDelivery(session);
			if(target instanceof IPersistentObject){
				signal.getTarget().processSignal(signal.getSignal());
				session.flush();
			}else{
				// TODO seperate TXManagement from request scoping
				signal.getTarget().processSignal(signal.getSignal());
			}
			CdiTestEnvironment.getInstance().afterRequest(target);
		}
	}
	@Override
	@Deprecated
	public synchronized void deliverPendingSignalsOfType(Class<? extends AbstractSignal> type){
		for(org.nakeduml.environment.SignalToDispatch signal:getSignalsOfType(type)){
			signal.getTarget().processSignal(signal.getSignal());
		}
	}
	public void prepareSignalsForDispatch(){
		ArrayList<SignalToDispatch> signals = new ArrayList<SignalToDispatch>(signalsToDispatch);
		for(org.nakeduml.environment.SignalToDispatch signal:signals){
			signal.prepareForDispatch();
		}
	}
}
