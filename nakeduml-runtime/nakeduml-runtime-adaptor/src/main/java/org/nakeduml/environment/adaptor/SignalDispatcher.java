package org.nakeduml.environment.adaptor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.nakeduml.environment.IMessageSender;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractEventSource;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@RequestScoped
@Stateful
@Deprecated
public class SignalDispatcher implements ISignalDispatcher{
	@Inject
	IMessageSender sender;
	@Inject
	Logger logger;
	public void sendSignal(Object source,ActiveObject target,AbstractSignal signal){
		if(!(source instanceof AbstractEventSource)){
			//Only send if it does not come from a hibernate-registered event source
			SignalToDispatch std = new SignalToDispatch(source, target, signal);
			std.prepareForDispatch();
			sender.sendObjectToQueue(std,std.getQueueName());
		}
	}
	public void sendSignal(Object source,Collection<? extends ActiveObject> targets,AbstractSignal signal){
	}
	public void reset(){
	}
	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type){
		List<SignalToDispatch> result = getSignalsOfType(type);
		return result.isEmpty() ? null : result.get(0);
	}
	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type){
		return Collections.emptyList();
	}
	@Override
	public void deliverAllPendingSignals(){
	}
}
