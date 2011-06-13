package org.nakeduml.environment.adaptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.Depends;
import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;
import org.nakeduml.environment.SignalToDispatch;

@MessageDriven(name = "HelperSignalMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/HelperSignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")
})
@TransactionManagement(value = TransactionManagementType.BEAN)
@Pool(maxSize = 10,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 1000 * 60 * 60 * 24)
@Depends({"jboss.j2ee:service=EJB3,name=org.nakeduml.environment.adaptor.MessageRetryer,type=service","jboss.j2ee:service=EJB3,name=org.nakeduml.environment.adaptor.MessageSender,type=service"})

public class HelperSignalMdb extends AbstractSignalMdb<SignalToDispatch> implements MessageListener{
	@Override
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		hibernateSession.clear();
		signalToDispatch.prepareForDelivery(hibernateSession);
		signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
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
