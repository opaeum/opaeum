package net.sf.nakeduml.seam;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractProcess;
import net.sf.nakeduml.util.AbstractUser;
import net.sf.nakeduml.util.ActiveEntity;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("signalMDB")
@MessageDriven(name = "SignalMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SignalQueue") })
public class SignalMDB implements MessageListener {
	@In
	EntityManager entityManager;

	@Override
	public void onMessage(Message message) {
		ObjectMessage obj = (ObjectMessage) message;
		try {
			SignalToDispatch signalToDispatch = (SignalToDispatch) obj.getObject();
			signalToDispatch.prepareForDelivery(entityManager);
			AbstractEntity target = signalToDispatch.getTarget();
			if(target instanceof AbstractProcess){
				((AbstractProcess)target).processSignal(signalToDispatch.getSignal());
			}else if(target instanceof ActiveEntity){
				((ActiveEntity)target).processSignal(signalToDispatch.getSignal());
			}else if(target instanceof AbstractUser){
				//TODO for all entities with an e-mail address, send the signal to their e-mail inbox
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
