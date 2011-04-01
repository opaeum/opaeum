package org.nakeduml.environment.adaptor;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractProcess;
import org.nakeduml.runtime.domain.AbstractUser;

@MessageDriven(name = "SignalMDB",activationConfig = {@ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "queue/SignalQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "CLIENT_ACKNOWLEDGE")})
@TransactionManagement(TransactionManagementType.BEAN)
public class SignalMDB implements MessageListener{
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	@Inject
	Logger logger;
	@Inject
	private Session session;
	@Override
	public void onMessage(Message message){
		ObjectMessage obj = (ObjectMessage) message;
		try{
			final SignalToDispatch signalToDispatch = (SignalToDispatch) obj.getObject();
			if(signalToDispatch.getTarget() instanceof AbstractEntity){
				processInTryBlock(new Work(){
					@Override
					public void doit() throws Exception{
						session.clear();
						transaction.begin();
						signalToDispatch.prepareForDelivery(session);
						signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
						session.flush();
						transaction.commit();
					}
				});
			}else{
				processInTryBlock(new Work(){
					@Override
					public void doit() throws Exception{
						session.clear();
						transaction.begin();
						signalToDispatch.prepareForDelivery(session);
						transaction.commit();
						signalToDispatch.getTarget().processSignal(signalToDispatch.getSignal());
					}
				});
			}
			if(signalToDispatch.getTarget() instanceof AbstractUser){
				// TODO for all entities with an e-mail address, send the signal
				// to their e-mail inbox
			}
			message.acknowledge();
		}catch(Exception e){
			try{
				transaction.rollback();
			}catch(Exception e2){
			}
			logger.error(e.getMessage(), e);
			try{
				message.acknowledge();
			}catch(Exception e2){
			}
		}
	}
	abstract class Work{
		public int count;
		abstract void doit() throws Exception;
	}
	private void processInTryBlock(Work wo){
		try{
			wo.count++;
			wo.doit();
		}catch(Exception e){
			CharArrayWriter w = new CharArrayWriter();
			e.printStackTrace(new PrintWriter(w));
			String stackTrace = new String(w.toCharArray());
			if(stackTrace.contains("org.hibernate.StaleObjectStateException")){
				String str = "org.jbpm.persistence.processinstance.ProcessInstanceInfo#";
				int index = stackTrace.indexOf(str);
				if(index > 0){
					StatefulKnowledgeSession ses = Environment.getInstance().getComponent(StatefulKnowledgeSession.class);
					String s = stackTrace.substring(index + str.length());
					String processId = s.substring(0, s.indexOf("]"));
					ProcessInstanceInfo pii = (ProcessInstanceInfo) session.get(ProcessInstanceInfo.class, new Long(processId));
					WorkflowProcessInstance processInstance = (WorkflowProcessInstance) pii.getProcessInstance((InternalKnowledgeRuntime) ses, ses.getEnvironment());
					AbstractProcess variable = (AbstractProcess) processInstance.getVariable("processObject");
					System.out.println(variable);
				}
			}
			try{
				session.clear();
				transaction.rollback();
			}catch(Exception e2){
			}
			if(wo.count < 5){
			}
		}
	}
}
