package org.nakeduml.environment.adaptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.MessageListener;

import org.drools.runtime.process.WorkflowProcessInstance;
import org.jboss.ejb3.annotation.Depends;
import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractProcess;

@MessageDriven(name = "HelperSignalMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/HelperSignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")
})
@TransactionManagement(value = TransactionManagementType.BEAN)
@Pool(maxSize = 10,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 1000 * 60 * 60 * 24)
//@Depends({"jboss.j2ee:service=EJB3,name=MessageRetryer,type=service","jboss.j2ee:service=EJB3,name=MessageSender,type=service"})

public class HelperSignalMdb extends AbstractSignalMdb<SignalToDispatch> implements MessageListener{
	@Override
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		//CM Hack check for explicit process failure instead
		hibernateSession.clear();
		signalToDispatch.prepareForDelivery(hibernateSession);
		Object s = signalToDispatch.getSource();
		if(s instanceof AbstractProcess){
			transaction.begin();
			WorkflowProcessInstance processInstance = ((AbstractProcess) s).getProcessInstance();
			transaction.commit();
			if(processInstance!=null){
				signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
			}
		}else{
			signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
		}
	}


	@Override
	protected String getQueueName(){
		return "queue/HelperSignalQueue";
	}

	@Override
	protected String getDlqName(){
		return "queue/HelperSignalDeadLetterQueue";
	}
}
