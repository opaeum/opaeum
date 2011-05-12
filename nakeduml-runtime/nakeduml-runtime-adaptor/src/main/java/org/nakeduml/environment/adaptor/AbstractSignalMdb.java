package org.nakeduml.environment.adaptor;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

public abstract class AbstractSignalMdb {
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	@Inject
	Logger logger;
	@Inject
	private Session hibernateSession;
	@Inject
	private
	MessageRetryer retryer;
	protected abstract void deliverMessage(SignalToDispatch std) throws Exception;
	public void onMessage(Message message){
		
		
		long start=System.currentTimeMillis();
		ObjectMessage obj = (ObjectMessage) message;
		try{
			message.acknowledge();
			this.processInTryBlock((SignalToDispatch) obj.getObject());
		}catch(Exception e){
			logger.errorv("Unhandled exception in SignalMDB: {0}", e.toString());
			logger.error(e.getMessage(), e);
		}finally{
			try{
				message.acknowledge();
			}catch(Exception e2){
				logger.error(e2.getMessage(), e2);
			}
			try{
				getHibernateSession().close();
			}catch(Exception e2){
				logger.error(e2.getMessage(), e2);
			}
			logger.debug("Signal delivery took " + (System.currentTimeMillis()-start) + "ms");
		}
	}
	private void processInTryBlock(SignalToDispatch std) throws Exception{
		try{
			deliverMessage(std);
		}catch(Exception e){
			try{
				getHibernateSession().clear();
				getTransaction().rollback();
			}catch(Exception e2){
			}
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			if(ea.isStaleStateException() || ea.isDeadlockException()){
				if(std.getRetryCount() < 20){
					logger.debugv("Retrying {0} because of {1}", std.getSignal().getClass().getSimpleName(), ea.getRootCause().toString());
					if(std.getTarget() instanceof AbstractEntity){
						getRetryer().retryMessage("queue/EntitySignalQueue", std);
					}else{
						getRetryer().retryMessage("queue/HelperSignalQueue", std);
					}
				}else{
					Throwable rootCause = ea.getRootCause();
					if(rootCause instanceof SQLException && ea.getStackTrace(rootCause).contains("Call getNextException to see the cause")){
						logger.debugv("Unresolved exception found {0}", rootCause.toString());
					}
					logger.debugv("RetryCount exceeded for signal {0}", std.getSignal().getClass().getSimpleName());
					ea.throwRootCause();
				}
			}else{
				Throwable rootCause = ea.getRootCause();
				logger.debugv("Exception {0} can not be retried", rootCause.toString());
				ea.throwRootCause();
			}
		}
	}
	protected void setTransaction(SeamTransaction transaction) {
		this.transaction = transaction;
	}
	protected SeamTransaction getTransaction() {
		return transaction;
	}
	protected void setHibernateSession(Session hibernateSession) {
		this.hibernateSession = hibernateSession;
	}
	protected Session getHibernateSession() {
		return hibernateSession;
	}
	protected void setRetryer(MessageRetryer retryer) {
		this.retryer = retryer;
	}
	protected MessageRetryer getRetryer() {
		if(retryer==null){
			retryer=Environment.getInstance().getComponent(MessageRetryer.class);
		}
		return retryer;
	}
}
