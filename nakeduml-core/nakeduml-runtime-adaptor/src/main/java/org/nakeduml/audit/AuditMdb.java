package org.nakeduml.audit;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;
import org.jboss.logging.Logger;
import org.nakeduml.environment.adaptor.MessageRetryer;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

@Pool(maxSize = 20,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 340000)
@MessageDriven(name = "AuditMdb",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/AuditQueue")
})
public class AuditMdb implements MessageListener{
	@Inject
	private AuditCapturer auditCapturer;
	@Inject
	private AuditSequencer auditSequencer;
	@Inject
	MessageRetryer retryer;
	@Inject
	Logger logger;
	public void onMessage(Message arg0){
		ObjectMessage message = (ObjectMessage) arg0;
		AbstractWorkUnit workUnit = null;
		try{
			workUnit = (AbstractWorkUnit) message.getObject();
			if(workUnit.getSequence() != 0 && !auditSequencer.contains(workUnit.getSequence() - 1)){
				logger.debug("AuditMessage received out of sync");
			}
			auditSequencer.put(workUnit.getSequence());
			auditCapturer.persistAudit(workUnit);
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			if(workUnit.getRetryCount() < 20 && (ea.isDeadlockException() || ea.isStaleStateException() || ea.stringOccurs("foreign key"))){
				logger.debug("Retrying AuditWorkUnit because of:", ea.getRootCause());
				workUnit.incrementRetryCount();
				retryer.retryMessage("queue/AuditQueue", workUnit);
			}else{
				logger.error("Exception could not be retried", ea.getRootCause());
			}
		}
	}
}
