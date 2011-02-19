package net.sf.nakeduml.audit;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;

@Pool(maxSize = 1, value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX, timeout=340000)
@MessageDriven(name = "AuditMDB", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/AuditQueue") })
public class AuditMDB implements MessageListener {
	@Inject
	private AuditCapturer auditCapturer;
	
	public void onMessage(Message arg0) {
		ObjectMessage message = (ObjectMessage) arg0;
		try {
			AbstractWorkUnit workUnit = (AbstractWorkUnit) message.getObject();
			auditCapturer.persistAudit(workUnit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
