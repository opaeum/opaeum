package net.sf.nakeduml.seam3;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Startup
@Singleton
@DependsOn(value={"InitializeHibernate"})
public class StartUpEventController {

	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory cf;
	@Resource(mappedName = "jms/StartUpTopic")
	private Topic startUpTopic;

	@PostConstruct
	public void existDefaultData() throws JMSException {
		Connection connection = null;
		try {
			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(startUpTopic);
			TextMessage message = session.createTextMessage("This is an order");
			producer.send(message);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
