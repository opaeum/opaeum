package org.nakeduml.environment.adaptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.XAConnection;
import javax.jms.XAConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;

import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public class SignalDispatcher implements ISignalDispatcher{
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
	private boolean isRegistered;
	public boolean isRegistered(){
		return isRegistered;
	}
	public void register(){
		try{
			if(transaction.isActive()){
				transaction.registerSynchronization(new MySync());
				isRegistered = true;
			}
		}catch(SystemException e){
			throw new RuntimeException(e);
		}
	}
	public void sendSignal(Object source,ActiveObject target,AbstractSignal signal){
		signalsToDispatch.add(new SignalToDispatch(source, target, signal));
	}
	public void sendSignal(Object source,Collection<? extends ActiveObject> targets,AbstractSignal signal){
		for(ActiveObject target:targets){
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}
	public void reset(){
		this.signalsToDispatch.clear();
	}
	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type){
		List<SignalToDispatch> result = getSignalsOfType(type);
		return result.isEmpty() ? null : result.get(0);
	}
	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type){
		List<SignalToDispatch> result = new ArrayList<SignalToDispatch>();
		for(SignalToDispatch signalToDispatch:this.signalsToDispatch){
			if(type.isInstance(signalToDispatch.getSignal())){
				result.add(signalToDispatch);
			}
		}
		return result;
	}
	class MySync implements Synchronization{

		@Override
		public void afterCompletion(int arg0){
		}
		@Override
		public void beforeCompletion(){
			isRegistered = false;
			try{
				// TODO currently happening outside of a transaction, move to inside a
				// transaction but AFTER flush				// TODO make configurable
				// NB!! Seam component management did not work in the
				// afterCompletion context - the session was null
				InitialContext initialContext = new InitialContext();
				Queue queue = (Queue) initialContext.lookup("queue/SignalQueue");
				XAConnectionFactory cf = (XAConnectionFactory) initialContext.lookup("/ConnectionFactory");
				XAConnection connection = cf.createXAConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue);
				for(SignalToDispatch s:signalsToDispatch){
					s.prepareForDispatch();
					producer.send(session.createObjectMessage(s));
				}
				session.close();
				connection.close();
				reset();
			}catch(JMSException e){
				throw new RuntimeException(e);
			}catch(NamingException e){
				throw new RuntimeException(e);
			}
		}
	}
	@Override
	public void deliverAllPendingSignals(){
		for(SignalToDispatch s:this.signalsToDispatch){
			s.getTarget().processSignal(s.getSignal());
		}
	}
	@Override
	public void deliverPendingSignalsOfType(Class<? extends AbstractSignal> type){
		for(SignalToDispatch s:getSignalsOfType(type)){
			s.getTarget().processSignal(s.getSignal());
		}
	}
}
