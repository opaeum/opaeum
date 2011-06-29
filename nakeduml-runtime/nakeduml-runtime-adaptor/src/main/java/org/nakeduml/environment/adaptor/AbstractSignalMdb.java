package org.nakeduml.environment.adaptor;

import javax.jms.MessageListener;

import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.jbpm.process.instance.ProcessInstance;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IProcessObject;

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
		if(s instanceof IProcessObject){
			transaction.begin();
			ProcessInstanceInfo processInstance =(ProcessInstanceInfo) sessionFactory.getCurrentSession().get(ProcessInstanceInfo.class, ((IProcessObject) s).getProcessInstanceId());
			transaction.commit();
			if(processInstance!=null && processInstance.getState()!=ProcessInstance.STATE_ABORTED && processInstance.getState()!=ProcessInstance.STATE_COMPLETED ){
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
		IPersistentObject target = (IPersistentObject) signalToDispatch.getTarget();
		((IActiveObject) target).processSignal(signalToDispatch.getSignal());
		transaction.commit();
	}
}
