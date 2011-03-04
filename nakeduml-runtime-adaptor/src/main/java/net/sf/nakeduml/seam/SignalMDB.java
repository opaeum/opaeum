package net.sf.nakeduml.seam;


//TODO Weld
//@Name("signalMDB")
//@MessageDriven(name = "SignalMDB", activationConfig = {
//		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SignalQueue") })
//@TransactionManagement(TransactionManagementType.BEAN)
public class SignalMDB /*implements MessageListener*/ {
//	@Resource
//	UserTransaction userTransaction;
//
//	@Override
//	public void onMessage(Message message) {
//		ObjectMessage obj = (ObjectMessage) message;
//		try {
//			SignalToDispatch signalToDispatch = (SignalToDispatch) obj.getObject();
//			signalToDispatch.prepareForDelivery((EntityManager) Component.getInstance("entityManager"));
//			ActiveObject target = signalToDispatch.getTarget();
//			if (target instanceof AbstractEntity) {
//				processInTransaction(signalToDispatch, target);
//			} else {
//				target.processSignal(signalToDispatch.getSignal());
//			}
//			if (target instanceof AbstractUser) {
//				// TODO for all entities with an e-mail address, send the signal
//				// to their e-mail inbox
//			}
//		} catch (JMSException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private void processInTransaction(SignalToDispatch signalToDispatch, ActiveObject target) {
//		try {
//			userTransaction.begin();
//			target.processSignal(signalToDispatch.getSignal());
//			userTransaction.commit();
//		} catch (RuntimeException e) {
//			throw e;
//		} catch (NotSupportedException e) {
//			throw new RuntimeException(e);
//		} catch (SystemException e) {
//			throw new RuntimeException(e);
//		} catch (RollbackException e) {
//			throw new RuntimeException(e);
//		} catch (HeuristicMixedException e) {
//			throw new RuntimeException(e);
//		} catch (HeuristicRollbackException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
