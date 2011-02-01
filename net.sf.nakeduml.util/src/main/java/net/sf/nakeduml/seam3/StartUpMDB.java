package net.sf.nakeduml.seam3;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "StartUpMDB", activationConfig = { @ActivationConfigProperty(propertyName = "DLQMaxResent", propertyValue = "0"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/StartUpTopic") })
@TransactionManagement(TransactionManagementType.BEAN)
public class StartUpMDB implements MessageListener {

	@Inject Event<NakedStartUpEvent> startUpEvent;
	@Inject NakedStartUpEvent event;

	public void onMessage(Message message) {
		startUpEvent.fire(event);
	}

}
