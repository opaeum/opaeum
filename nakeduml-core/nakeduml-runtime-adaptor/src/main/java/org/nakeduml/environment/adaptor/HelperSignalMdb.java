package org.nakeduml.environment.adaptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;

@MessageDriven(name = "HelperSignalMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/HelperSignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")
})
@TransactionManagement(TransactionManagementType.BEAN)
@Pool(maxSize = 10,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 1000 * 60 * 60 * 24)
public class HelperSignalMdb extends AbstractSignalMdb implements MessageListener{
	@Override
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		hibernateSession.clear();
		transaction.begin();
		signalToDispatch.prepareForDelivery(hibernateSession);
		transaction.commit();
		signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
	}
}
