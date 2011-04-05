package org.nakeduml.environment.adaptor;

import java.beans.MethodDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.hibernate.Session;
import org.jboss.ejb3.annotation.Pool;
import org.jboss.ejb3.annotation.defaults.PoolDefaults;
import org.jboss.logging.Logger;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractProcess;
import org.nakeduml.runtime.domain.AbstractUser;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IntrospectionUtil;

@MessageDriven(name = "SignalMDB",activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/SignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Client-acknowledge")
})
@TransactionManagement(TransactionManagementType.BEAN)
@Pool(maxSize = 20,value = PoolDefaults.POOL_IMPLEMENTATION_STRICTMAX,timeout = 3000000)
public class SignalMdb implements MessageListener{
	public static class ObjectToLock implements Serializable{
		private static final long serialVersionUID = 1111970936285421429L;
		Class<? extends AbstractEntity> clazz;
		Long id;
		public ObjectToLock(Class<? extends AbstractEntity> clazz,Long id){
			this.clazz = clazz;
			this.id = id;
		}
		public int hashCode(){
			return clazz.hashCode()*077 + id.intValue();
		}
		public boolean equals(Object o){
			if(o instanceof ObjectToLock){
				ObjectToLock p = (ObjectToLock) o;
				return p.clazz == clazz && p.id.longValue() == id.longValue();
			}else{
				return false;
			}
		}
	}
	static final Map<ObjectToLock,Object> locks = Collections.synchronizedMap(new WeakHashMap<SignalMdb.ObjectToLock,Object>());
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	@Inject
	Logger logger;
	@Inject
	private Session hibernateSession;
	@Inject
	MessageRetryer retryer;
	@Override
	public void onMessage(Message message){
		ObjectMessage obj = (ObjectMessage) message;
		try{
			final SignalToDispatch signalToDispatch = (SignalToDispatch) obj.getObject();
			if(signalToDispatch.getTarget() instanceof AbstractEntity){
				if(signalToDispatch.getSource() instanceof AbstractEntity){
					logger.debugv("{0} received from {1}[{2}]", signalToDispatch.getSource().getClass().getSimpleName(),
							((AbstractEntity) signalToDispatch.getSource()).getId());
				}
				processInTryBlock(new Work(){
					@Override
					public void doit() throws Exception{
						Object lock = new Object();
						ObjectToLock pt = signalToDispatch.getObjectToLock();
						if(pt != null){
							lock = locks.get(pt);
							if(lock == null){
								lock = new Object();
								locks.put(pt, lock);
							}
						}
						synchronized(lock){
							hibernateSession.clear();
							transaction.begin();
							signalToDispatch.prepareForDelivery(hibernateSession);
							signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
							hibernateSession.flush();
							transaction.commit();
						}
					}
				}, signalToDispatch);
			}else{
				processInTryBlock(new Work(){
					@Override
					public void doit() throws Exception{
						hibernateSession.clear();
						transaction.begin();
						signalToDispatch.prepareForDelivery(hibernateSession);
						transaction.commit();
						signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
					}
				}, signalToDispatch);
			}
			if(signalToDispatch.getTarget() instanceof AbstractUser){
				// TODO for all entities with an e-mail address, send the signal
				// to their e-mail inbox
			}
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
				hibernateSession.close();
			}catch(Exception e2){
				logger.error(e2.getMessage(), e2);
			}
		}
	}
	abstract class Work{
		abstract void doit() throws Exception;
	}
	private void processInTryBlock(Work wo,SignalToDispatch std) throws Exception{
		try{
			wo.doit();
		}catch(Exception e){
			try{
				hibernateSession.clear();
				transaction.rollback();
			}catch(Exception e2){
			}
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			if(ea.isStaleStateException() || ea.isDeadlockException()){
				// String str = "org.jbpm.persistence.processinstance.ProcessInstanceInfo#";
				// int index = stackTrace.indexOf(str);
				// if(index > 0){
				// transaction.begin();
				// StatefulKnowledgeSession ses = Environment.getInstance().getComponent(StatefulKnowledgeSession.class);
				// String s = stackTrace.substring(index + str.length());
				// String processId = s.substring(0, s.indexOf("]"));
				// ProcessInstanceInfo pii = (ProcessInstanceInfo) session.get(ProcessInstanceInfo.class, new Long(processId));
				// WorkflowProcessInstance processInstance = (WorkflowProcessInstance) pii.getProcessInstance((InternalKnowledgeRuntime)
				// ses, ses.getEnvironment());
				// AbstractProcess variable = (AbstractProcess) processInstance.getVariable("processObject");
				// System.out.println(variable);
				// transaction.commit();
				// session.clear();
				// }
				if(std.getRetryCount() < 40){// Twice the amount of concurrent mdbs
					logger.debugv("Retrying {0} because of {1}", std.getSignal().getClass().getSimpleName(), ea.getRootCause().toString());
					retryer.retryMessage("queue/SignalQueue", std);
				}else{
					Exception rootCause = ea.getRootCause();
					if(rootCause instanceof SQLException && ea.getStackTrace(rootCause).contains("Call getNextException to see the cause")){
						logger.debugv("Unresolved exception found {0}", rootCause.toString());
					}
					logger.debugv("RetryCount exceeded for signal {0}", std.getSignal().getClass().getSimpleName());
					throw rootCause;
				}
			}else{
				Exception rootCause = ea.getRootCause();
				logger.debugv("Exception {0} can not be retried", rootCause.toString());
				throw rootCause;
			}
		}
	}
}
