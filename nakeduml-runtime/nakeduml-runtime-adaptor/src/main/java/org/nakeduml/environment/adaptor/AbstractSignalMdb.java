package org.nakeduml.environment.adaptor;

import javax.jms.MessageListener;

import org.drools.runtime.process.WorkflowProcessInstance;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractProcess;
import org.nakeduml.runtime.domain.ActiveObject;

public abstract class AbstractSignalMdb extends AbstractEventMdb<SignalToDispatch> implements MessageListener{
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		if(signalToDispatch.targetIsEntity()){
			deliverToEntity(signalToDispatch);
		}else{
			deliverToHelper(signalToDispatch);
		}
	}
	private void deliverToHelper(SignalToDispatch signalToDispatch) throws Exception{
		// CM Hack check for explicit process failure instead
		transaction.begin();
		signalToDispatch.prepareForDelivery(sessionFactory.getCurrentSession());
		transaction.commit();
		Object s = signalToDispatch.getSource();
		if(s instanceof AbstractProcess){
			transaction.begin();
			WorkflowProcessInstance processInstance = ((AbstractProcess) s).getProcessInstance();
			transaction.commit();
			if(processInstance != null){
				signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
			}
		}else{
			signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
		}
	}
	protected void deliverToEntity(SignalToDispatch signalToDispatch) throws Exception{
		transaction.setTransactionTimeout(600);
		transaction.begin();
		signalToDispatch.prepareForDelivery(sessionFactory.getCurrentSession());
		AbstractEntity target = (AbstractEntity) signalToDispatch.getTarget();
		((ActiveObject) target).processSignal(signalToDispatch.getSignal());
		transaction.commit();
	}
}
