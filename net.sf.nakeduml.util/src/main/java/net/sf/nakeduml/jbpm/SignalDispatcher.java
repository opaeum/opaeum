package net.sf.nakeduml.jbpm;

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

import net.sf.nakeduml.seam3.Component;
import net.sf.nakeduml.util.AbstractSignal;
import net.sf.nakeduml.util.ActiveObject;

import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.solder.beanManager.BeanManagerUnavailableException;

public class SignalDispatcher {
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	static SignalDispatcher mockInstance = null;
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
	private boolean isRegistered;

	public boolean isRegistered() {
		return isRegistered;
	}

	public static SignalDispatcher getInstance() {
		try {
			SignalDispatcher d = (SignalDispatcher) Component.INSTANCE.getInstance(SignalDispatcher.class);
			if (!d.isRegistered()) {
				d.register();
			}
			return d;
		} catch (BeanManagerUnavailableException e) {
			if (mockInstance == null) {
				mockInstance = new SignalDispatcher();
			}
			return mockInstance;
		}
	}

	public void register() {
		try {
			if (transaction.isActive()) {
				transaction.registerSynchronization(new MySync());
				isRegistered = true;
			}
		} catch (SystemException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendSignal(Object source, ActiveObject target, AbstractSignal signal) {
		signalsToDispatch.add(new SignalToDispatch(source, target, signal));
	}

	public void sendSignal(Object source, Collection<? extends ActiveObject> targets, AbstractSignal signal) {
		for (ActiveObject target : targets) {
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}

	public void reset() {
		this.signalsToDispatch.clear();
	}

	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type) {
		List<SignalToDispatch> result = getSignalsOfType(type);
		return result.isEmpty() ? null : result.get(0);
	}

	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type) {
		List<SignalToDispatch> result = new ArrayList<SignalToDispatch>();
		for (SignalToDispatch signalToDispatch : this.signalsToDispatch) {
			if (type.isInstance(signalToDispatch.getSignal())) {
				result.add(signalToDispatch);
			}
		}
		return result;
	}

	class MySync implements Synchronization {
		// TODO currently happening outside of a transaction, move to inside a
		// transaction but AFTER flush
		@Override
		public void afterCompletion(int arg0) {
			isRegistered = false;
			if (arg0 == Status.STATUS_COMMITTED) {
				try {
					// TODO make configurable
					// NB!! Seam component management did not work in the
					// afterCompletion context - the session was null
					InitialContext initialContext = new InitialContext();
					Queue queue = (Queue) initialContext.lookup("queue/SignalQueue");
					XAConnectionFactory cf = (XAConnectionFactory) initialContext.lookup("/ConnectionFactory");
					XAConnection connection = cf.createXAConnection();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session.createProducer(queue);
					for (SignalToDispatch s : signalsToDispatch) {
						s.prepareForDispatch();
						producer.send(session.createObjectMessage(s));
					}
					session.close();
					connection.close();
					reset();
				} catch (JMSException e) {
					throw new RuntimeException(e);
				} catch (NamingException e) {
					throw new RuntimeException(e);
				}
			}
		}

		@Override
		public void beforeCompletion() {
		}
	}
}
