package net.sf.nakeduml.seam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.persistence.EntityManager;
import javax.transaction.Synchronization;

import net.sf.nakeduml.util.AbstractSignal;
import net.sf.nakeduml.util.ActiveObject;

import org.hibernate.Session;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;

@Name("signalDispatcher")
@Scope(ScopeType.EVENT)
public class SignalDispatcher implements Synchronization {
	@In(create = true)
	private EntityManager entityManager;
	@In(create = true)
	private QueueSession queueSession;
	@In(create = true)
	QueueSender signalQueueSender;
	static SignalDispatcher mockInstance = null;
	static Map<EntityManager, SignalDispatcher> synchronization = new HashMap<EntityManager, SignalDispatcher>();
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();

	public static SignalDispatcher getInstance() {
		if (Contexts.isEventContextActive()) {
			SignalDispatcher d = (SignalDispatcher) Component.getInstance("signalDispatcher");
			if (!synchronization.containsKey(d.getEntityManager())) {
				((Session) d.getEntityManager().getDelegate()).getTransaction().registerSynchronization(d);
				synchronization.put(d.getEntityManager(), d);
			}
			return d;
		} else {
			if (mockInstance == null) {
				mockInstance = new SignalDispatcher();
			}
			return mockInstance;
		}
	}

	public QueueSession getQueueSession() {
		return queueSession;
	}

	public QueueSender getSignalQueueSender() {
		return signalQueueSender;
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

	@Override
	public void afterCompletion(int arg0) {
	}

	@Override
	public void beforeCompletion() {
		synchronization.remove(getEntityManager());
		try {
			for (SignalToDispatch s : signalsToDispatch) {
				s.prepareForDispatch();
				signalQueueSender.send(queueSession.createObjectMessage(s));
			}
//			signalQueueSender.close();
			reset();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
