package org.nakeduml.audit;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
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

import org.hibernate.Transaction;
import org.hibernate.event.EventSource;
import org.jboss.seam.persistence.util.InstanceResolver;
import org.jboss.seam.solder.beanManager.BeanManagerLocator;
import org.nakeduml.runtime.domain.AuditId;
import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

public class AuditSync implements Synchronization{
	private final AuditSyncManager manager;
	private final Transaction transaction;
	private final LinkedList<Audited> auditedEntities;
	private final Map<Pair<String,AuditId>,Audited> usedIds;
	private AbstractWorkUnit abstractWorkUnit;
	private boolean isAsync;
	Instance<AuditSequencer> auditSequencerInstance;
	Instance<AuditCapturer> auditCapturerInstance;
	public AuditSync(AuditSyncManager manager,EventSource session,boolean isAsync){
		this.manager = manager;
		transaction = session.getTransaction();
		auditedEntities = new LinkedList<Audited>();
		usedIds = new HashMap<Pair<String,AuditId>,Audited>();
		abstractWorkUnit = new AbstractWorkUnit();
		this.isAsync = isAsync;
		this.auditSequencerInstance = lookupAuditSequencerInstance();
		this.auditCapturerInstance = lookupAuditCapturer();
	}
	public void addAudited(Audited audited){
		try{
			Pair<String,AuditId> usedIdsKey = Pair.make(audited.getClass().getSimpleName(), audited.getId());
			Audited alreadyAudited = usedIds.get(usedIdsKey);
			if(alreadyAudited != null){
				int i = auditedEntities.indexOf(alreadyAudited);
				auditedEntities.remove(alreadyAudited);
				audited.setRevisionType(alreadyAudited.getRevisionType());
				auditedEntities.add(i, audited);
			}else{
				auditedEntities.offer(audited);
			}
			usedIds.put(usedIdsKey, audited);
		}catch(RuntimeException re){
			throw re;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public void beforeCompletion(){
		if(!isAsync){
			AuditCapturer auditCapturer = auditCapturerInstance.get();
			abstractWorkUnit.setAuditedEntities(auditedEntities);
			auditCapturer.persistAudit(abstractWorkUnit);
			manager.remove(transaction);
		}
	}
	@Override
	public void afterCompletion(int status){
		if(isAsync && Status.STATUS_COMMITTED == status){
			try{
				abstractWorkUnit.setAuditedEntities(auditedEntities);
				abstractWorkUnit.setSequence(auditSequencerInstance.get().getAndIncrement());
				sendAuditMessage();
			}catch(Exception e){
				new ExceptionAnalyser(e).throwRootCause();
			}
		}
		manager.remove(transaction);
	}
	private void sendAuditMessage() throws JMSException,NamingException{
		Connection connection = null;
		InitialContext initialContext = null;
		try{
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
		}finally{
			// Step 12. Be sure to close our JMS resources!
			if(initialContext != null){
				initialContext.close();
			}
			if(connection != null){
				connection.close();
			}
		}
	}
	private Instance<AuditSequencer> lookupAuditSequencerInstance(){
		BeanManagerLocator locator = new BeanManagerLocator();
		BeanManager beanManager = locator.getBeanManager();
		return InstanceResolver.getInstance(AuditSequencer.class, beanManager);
	}
	private Instance<AuditCapturer> lookupAuditCapturer(){
		BeanManagerLocator locator = new BeanManagerLocator();
		BeanManager beanManager = locator.getBeanManager();
		return InstanceResolver.getInstance(AuditCapturer.class, beanManager);
	}
}
