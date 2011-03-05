package net.sf.nakeduml.seam;


//@Name("signalDispatcher")
//@Scope(ScopeType.EVENT)
public class SignalDispatcher {
//	static SignalDispatcher mockInstance = null;
//	List<SignalToDispatch> signalsToDispatch = new ArrayList<SignalToDispatch>();
//	private boolean isRegistered;
//
//	public boolean isRegistered() {
//		return isRegistered;
//	}
//
//	public static SignalDispatcher getInstance() {
//		if (Contexts.isEventContextActive()) {
//			SignalDispatcher d = (SignalDispatcher) Component.getInstance("signalDispatcher");
//			if (!d.isRegistered()) {
//				d.register();
//			}
//			return d;
//		} else {
//			if (mockInstance == null) {
//				mockInstance = new SignalDispatcher();
//			}
//			return mockInstance;
//		}
//	}
//
//	public void register() {
//		try {
//			if (Transaction.instance().isActive()) {
//				Transaction.instance().registerSynchronization(new MySync());
//				isRegistered = true;
//			}
//		} catch (SystemException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void sendSignal(Object source, ActiveObject target, AbstractSignal signal) {
//		signalsToDispatch.add(new SignalToDispatch(source, target, signal));
//	}
//
//	public void sendSignal(Object source, Collection<? extends ActiveObject> targets, AbstractSignal signal) {
//		for (ActiveObject target : targets) {
//			signalsToDispatch.add(new SignalToDispatch(source, target, signal));
//		}
//	}
//
//	public void reset() {
//		this.signalsToDispatch.clear();
//	}
//
//	public SignalToDispatch getFirstSignalOfType(Class<? extends AbstractSignal> type) {
//		List<SignalToDispatch> result = getSignalsOfType(type);
//		return result.isEmpty() ? null : result.get(0);
//	}
//
//	public List<SignalToDispatch> getSignalsOfType(Class<? extends AbstractSignal> type) {
//		List<SignalToDispatch> result = new ArrayList<SignalToDispatch>();
//		for (SignalToDispatch signalToDispatch : this.signalsToDispatch) {
//			if (type.isInstance(signalToDispatch.getSignal())) {
//				result.add(signalToDispatch);
//			}
//		}
//		return result;
//	}
//
//	class MySync implements Synchronization {
//		// TODO currently happening outside of a transaction, move to inside a
//		// transaction but AFTER flush
//		@Override
//		public void afterCompletion(int arg0) {
//			isRegistered = false;
//			if (arg0 == Status.STATUS_COMMITTED) {
//				try {
//					// TODO make configurable
//					// NB!! Seam component management did not work in the
//					// afterCompletion context - the session was null
//					InitialContext initialContext = new InitialContext();
//					Queue queue = (Queue) initialContext.lookup("queue/SignalQueue");
//					XAConnectionFactory cf = (XAConnectionFactory) initialContext.lookup("/ConnectionFactory");
//					XAConnection connection = cf.createXAConnection();
//					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//					MessageProducer producer = session.createProducer(queue);
//					for (SignalToDispatch s : signalsToDispatch) {
//						s.prepareForDispatch();
//						producer.send(session.createObjectMessage(s));
//					}
//					session.close();
//					connection.close();
//					reset();
//				} catch (JMSException e) {
//					throw new RuntimeException(e);
//				} catch (NamingException e) {
//					throw new RuntimeException(e);
//				}
//			}
//		}
//
//		@Override
//		public void beforeCompletion() {
//		}
//	}
}
