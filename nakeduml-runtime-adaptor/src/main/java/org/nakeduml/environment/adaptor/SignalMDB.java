package org.nakeduml.environment.adaptor;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.hibernate.Session;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractUser;
import org.nakeduml.runtime.domain.ActiveObject;


@MessageDriven(name = "SignalMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SignalQueue") })
@TransactionManagement(TransactionManagementType.BEAN)
public class SignalMDB implements MessageListener {
	@Resource
	UserTransaction userTransaction;
	@Inject
	private Session session;

	@Override
	public void onMessage(Message message) {
		ObjectMessage obj = (ObjectMessage) message;
		try {
			SignalToDispatch signalToDispatch = (SignalToDispatch) obj.getObject();
			signalToDispatch.prepareForDelivery(session);
			ActiveObject target = signalToDispatch.getTarget();
			if (target instanceof AbstractEntity) {
				processInTransaction(signalToDispatch, target);
			} else {
				target.processSignal(signalToDispatch.getSignal());
			}
			if (target instanceof AbstractUser) {
				// TODO for all entities with an e-mail address, send the signal
				// to their e-mail inbox
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private void processInTransaction(SignalToDispatch signalToDispatch, ActiveObject target) {
		try {
			userTransaction.begin();
			target.processSignal(signalToDispatch.getSignal());
			userTransaction.commit();
		} catch (RuntimeException e) {
			throw e;
		} catch (NotSupportedException e) {
			throw new RuntimeException(e);
		} catch (SystemException e) {
			throw new RuntimeException(e);
		} catch (RollbackException e) {
			throw new RuntimeException(e);
		} catch (HeuristicMixedException e) {
			throw new RuntimeException(e);
		} catch (HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}
	}
}
