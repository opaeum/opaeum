package org.nakeduml.environment.adaptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;

import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.jboss.seam.transaction.TransactionScoped;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

@TransactionScoped
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
		SignalToDispatch e = new SignalToDispatch(source, target, signal);
		e.retrieveIds();// To avoid lazy init exception on send
		signalsToDispatch.add(e);
	}
	public void sendSignal(Object source,Collection<? extends ActiveObject> targets,AbstractSignal signal){
		for(ActiveObject target:targets){
			sendSignal(source, target, signal);
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
			if(arg0 == Status.STATUS_COMMITTED){
				isRegistered = false;
				Connection connection = null;
				Session session = null;
				try{
					// Has to happen outside a transaction - somehow in JBoss and HornetQ the message does not get sent inside the
					// transaction
					// TODO currently happening outside of a transaction, move to inside a
					// transaction but AFTER flush // TODO make configurable
					// NB!! Seam component management did not work in the
					// afterCompletion context - the session was null
					InitialContext initialContext = new InitialContext();
					ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
					connection = factory.createConnection();
					session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
					MessageProducer entityProducer = session.createProducer((Queue) initialContext.lookup("queue/EntitySignalQueue"));
					MessageProducer helperProducer = session.createProducer((Queue) initialContext.lookup("queue/HelperSignalQueue"));
					for(SignalToDispatch s:signalsToDispatch){
						s.prepareForDispatch();
						try{
							ObjectOutputStream os = new ObjectOutputStream(new ByteArrayOutputStream());
							os.writeObject(s);
						}catch(IOException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(s.getTarget() instanceof AbstractEntity){
							entityProducer.send(session.createObjectMessage(s));
						}else{
							helperProducer.send(session.createObjectMessage(s));
						}
					}
					helperProducer.close();
					entityProducer.close();
				}catch(JMSException e){
					throw new RuntimeException(e);
				}catch(NamingException e){
					throw new RuntimeException(e);
				}finally{
					try{
						session.close();
					}catch(Exception ignore){
					}
					try{
						connection.close();
					}catch(Exception ignore){
					}
					reset();
				}
			}
		}
		@Override
		public void beforeCompletion(){
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
