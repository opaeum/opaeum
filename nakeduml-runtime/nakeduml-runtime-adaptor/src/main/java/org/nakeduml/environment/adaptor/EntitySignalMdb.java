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
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.ActiveObject;

@MessageDriven(name = "EntitySignalMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/EntitySignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")})
@TransactionManagement(TransactionManagementType.BEAN)
@Pool(maxSize = 1,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 1000 * 60 * 60 * 24)
@Depends({"jboss.j2ee:service=EJB3,name=org.nakeduml.environment.adaptor.MessageRetryer,type=service","jboss.j2ee:service=EJB3,name=org.nakeduml.environment.adaptor.MessageSender,type=service"})
public class EntitySignalMdb extends AbstractSignalMdb<SignalToDispatch> implements MessageListener{
	@Override
	protected void deliverMessage(SignalToDispatch signalToDispatch) throws Exception{
		long start = System.currentTimeMillis();
		try{
			logger.errorv("deliverMessage");
			hibernateSession.clear();
			transaction.setTransactionTimeout(600);
			transaction.begin();
			signalToDispatch.prepareForDelivery(hibernateSession);
			AbstractEntity target = (AbstractEntity) signalToDispatch.getTarget();
			((ActiveObject) target).processSignal(signalToDispatch.getSignal());
			hibernateSession.flush();
			transaction.commit();
			logger.errorv("deliverMessage to");
		}catch(Exception e){
			throw e;
		}finally{
			logger.errorv("deliverMessage took {0}ms" ,System.currentTimeMillis()-start);

		}
	}
	@Override
	protected String getQueueName(){
		return "queue/EntitySignalQueue";
	}
	@Override
	protected String getDlqName(){
		return "queue/EntitySignalDeadLetterQueue";
	}
}
