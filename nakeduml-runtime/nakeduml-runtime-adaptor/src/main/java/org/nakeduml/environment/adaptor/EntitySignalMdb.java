package org.nakeduml.environment.adaptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.ActiveObject;

@MessageDriven(name = "EntitySignalMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/EntitySignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")
})
@TransactionManagement(TransactionManagementType.BEAN)
@Pool(maxSize = 1,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 1000 * 60 * 60 * 24)
public class EntitySignalMdb extends AbstractSignalMdb<SignalToDispatch> implements MessageListener{
	@Override
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		getHibernateSession().clear();
		getTransaction().begin();
		signalToDispatch.prepareForDelivery(getHibernateSession());
		AbstractEntity target = (AbstractEntity) signalToDispatch.getTarget();
		((ActiveObject)target).processSignal(signalToDispatch.getSignal());
		getHibernateSession().flush();
		getTransaction().commit();
	}

	@Override
	protected String getQueueName(){
		return "queue/EntitySignalQueue";
	}
}
