package org.nakeduml.environment.adaptor;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.nakeduml.event.Retryable;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

public abstract class AbstractEventMdb<T extends Retryable>{
	@Resource
	protected UserTransaction transaction;
	@Inject
	Logger logger;
	@Inject
	protected SessionFactory sessionFactory;
	@EJB(name="MessageRetryer")
	MessageRetryer retryer;
	protected abstract void deliverMessage(T std) throws Exception;
	protected abstract String getQueueName();
	protected abstract String getDlqName();
	protected void redeliverDeadMessages(String[] args) throws Exception{
		Hashtable<Object,Object> env = new Hashtable<Object,Object>();
		if(args.length == 0){
			env.put(Context.PROVIDER_URL, "localhost:1099");
		}else{
			env.put(Context.PROVIDER_URL, args[0] + ":1099");
		}
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming.client");
		Context ctx = new InitialContext(env);
		Queue from = (Queue) ctx.lookup(getDlqName());
		Queue to = (Queue) ctx.lookup(getQueueName());
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		QueueConnection cnn = factory.createQueueConnection();
		Session session = cnn.createQueueSession(false, QueueSession.CLIENT_ACKNOWLEDGE);
		MessageConsumer createConsumer = session.createConsumer(from);
		MessageProducer createProducer = session.createProducer(to);
		Message received = null;
		cnn.start();
		while((received = createConsumer.receive(1000)) != null){
			ObjectMessage om = (ObjectMessage) received;
			createProducer.send(session.createObjectMessage(om.getObject()));
			om.acknowledge();
		}
		createConsumer.close();
		session.close();
		cnn.close();
	}
	public void onMessage(Message message){
		long start = System.currentTimeMillis();
		ObjectMessage obj = (ObjectMessage) message;
		try{
			this.processInTryBlock((T) obj.getObject());
		}catch(Exception e){
			logger.errorv("Unhandled exception in SignalMDB: {0}", e.toString());
			logger.error(e.getMessage(), e);
		}finally{
			try{
				message.acknowledge();
			}catch(Exception e2){
				logger.error(e2.getMessage(), e2);
			}
			logger.debug("Signal delivery took " + (System.currentTimeMillis() - start) + "ms");
		}
	}
	private void processInTryBlock(T std) throws Exception{
		try{
			deliverMessage(std);
		}catch(Exception e){
			try{
				transaction.rollback();
			}catch(Exception e2){
			}
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			if(ea.isResourceAllocationTimeout()){
				// NB!!! it seems that when a Resource Timeout occurs in JBoss the bean's environment becomes unstable and ejb references can't
				// acquire the necessary locks:
				// org.nakeduml.environment.adaptor.AbstractEventMdb] (Thread-6 (group:HornetQ-client-global-threads-1274855145)) null:
				// java.lang.InterruptedException
				// at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireSharedNanos(AbstractQueuedSynchronizer.java:1302) [:1.6.0_24]
				//Redeliver immediately and hope for the best
				MessageRetryer.sendMessage(getQueueName(), std);
			}else if(ea.isStaleStateException() || ea.isDeadlockException()){
				if(std.getRetryCount() < 20){
					logger.debugv("Retrying {0} because of {1}", std.getDescription(), ea.getRootCause().toString());
					retryer.retryMessage(getQueueName(), std);
				}else{
					Throwable rootCause = ea.getRootCause();
					if(rootCause instanceof SQLException && ea.getStackTrace(rootCause).contains("Call getNextException to see the cause")){
						logger.debugv("Unresolved exception found {0}", rootCause.toString());
					}
					logger.debugv("RetryCount exceeded for signal {0}", std.getDescription());
					ea.throwRootCause();
				}
			}else{
				if(ea.stringOccurs("getNodeInstancesRecursively") && ea.stringOccurs("java.lang.NullPointerException")){
					// swallow this
					logger.debugv("Process had already completed on delivery of {0}", std.getDescription());
				}else{
					Throwable rootCause = ea.getRootCause();
					logger.debug(rootCause);
					logger.debugv("Exception {0} can not be retried", rootCause.toString());
					MessageRetryer.sendMessage(getDlqName(), std);
					ea.throwRootCause();
				}
			}
		}
	}
}
