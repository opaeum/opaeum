package net.sf.nakeduml.audit;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.XAConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.Synchronization;

import net.sf.nakeduml.util.Audited;

import org.hibernate.Transaction;
import org.hibernate.event.EventSource;

public class AuditSync implements Synchronization {

	private final AuditSyncManager manager;
	private final Transaction transaction;
	private final LinkedList<Audited> auditedEntities;
	private final Map<Pair<String, Integer>, Audited> usedIds;
	private AbstractWorkUnit abstractWorkUnit;

	public AuditSync(AuditSyncManager manager, EventSource session) {
		this.manager = manager;
		transaction = session.getTransaction();
		auditedEntities = new LinkedList<Audited>();
		usedIds = new HashMap<Pair<String, Integer>, Audited>();
		abstractWorkUnit = new AbstractWorkUnit();
	}

	public void addAudited(Audited audited) {
		Pair<String, Integer> usedIdsKey = Pair.make(audited.getClass().getSimpleName(), audited.getId().hashCode());
		Audited allReadyAudited = usedIds.get(usedIdsKey);
		if (allReadyAudited != null) {
			int i = auditedEntities.indexOf(allReadyAudited);
			auditedEntities.remove(allReadyAudited);
			auditedEntities.add(i, audited);
		} else {
			auditedEntities.offer(audited);
		}
		usedIds.put(usedIdsKey, audited);
	}

	@Override
	public void beforeCompletion() {
	}

	@Override
	public void afterCompletion(int status) {
		if (Status.STATUS_COMMITTED == status) {
			try {
				abstractWorkUnit.setAuditedEntities(auditedEntities);
				sendAuditMessage();
			} catch (NamingException e) {
				throw new RuntimeException(e);
			} catch (JMSException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		manager.remove(transaction);

	}

	private void sendAuditMessage() throws JMSException, NamingException {
		Connection connection = null;
		InitialContext initialContext = null;
		try {
			// Step 1. Create an initial context to perform the JNDI lookup.
			// initialContext = getContext();
			initialContext = new InitialContext();

			// Step 2. Perfom a lookup on the queue
			Queue queue = (Queue) initialContext.lookup("/queue/AuditQueue");

			// Step 3. Perform a lookup on the Connection Factory
			XAConnectionFactory cf = (XAConnectionFactory) initialContext.lookup("/ConnectionFactory");

			// Step 4.Create a JMS Connection
			connection = cf.createXAConnection();

			// Step 5. Create a JMS Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Step 6. Create a JMS Message Producer
			MessageProducer producer = session.createProducer(queue);
			producer.setDeliveryMode(NON_PERSISTENT);
			producer.send(session.createObjectMessage(abstractWorkUnit));

		} finally {
			// Step 12. Be sure to close our JMS resources!
			if (initialContext != null) {
				initialContext.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}


}
