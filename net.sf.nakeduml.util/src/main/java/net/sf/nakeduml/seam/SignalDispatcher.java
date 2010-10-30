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

import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractSignal;

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
	@In
	EntityManager entityManager;
	@In
	private QueueSession queueSession;
	@In
	QueueSender signalQueueSender;
	static Map<EntityManager, SignalDispatcher> synchronization = new HashMap<EntityManager, SignalDispatcher>();
	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
	static List<SignalToDispatch> signalsToMock = new ArrayList<SignalToDispatch>();

	public static void sendSignal(AbstractEntity source, AbstractEntity target, AbstractSignal signal) {
		List<SignalToDispatch> signalsToDispatch = prepareSignalList();
		signalsToDispatch.add(new SignalToDispatch(source, target, signal));
	}

	public static void sendSignal(AbstractEntity source, Collection<? extends AbstractEntity> targets, AbstractSignal signal) {
		List<SignalToDispatch> signalsToDispatch = prepareSignalList();
		for (AbstractEntity target : targets) {
			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
		}
	}

	private static List<SignalToDispatch> prepareSignalList() {
		List<SignalToDispatch> signalsToDispatch = null;
		if (Contexts.isEventContextActive()) {
			SignalDispatcher d = (SignalDispatcher) Component.getInstance("signalDispatcher");
			signalsToDispatch = d.signalsToDispatch;
			if (!synchronization.containsKey(d.entityManager)) {
				((Session) d.entityManager.getDelegate()).getTransaction().registerSynchronization(d);
				synchronization.put(d.entityManager, d);
			}
		} else {
			signalsToDispatch = signalsToMock;
		}
		return signalsToDispatch;
	}

	@Override
	public void afterCompletion(int arg0) {
	}

	@Override
	public void beforeCompletion() {
		synchronization.remove(entityManager);
		try {
			for (SignalToDispatch s : signalsToDispatch) {
				s.prepareForDispatch();
				signalQueueSender.send(queueSession.createObjectMessage(s));
			}
			signalQueueSender.close();
			signalsToDispatch.clear();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
