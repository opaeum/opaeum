package entitymodel.util;

import entitymodel.bodyparts.Hand;
import entitymodel.bodyparts.HandDataGenerator;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.opaeum.runtime.adaptor.DataGeneratorProperty;
import org.opaeum.seam3.persistence.DependentScopedSession;

public class ExampleStartUp {
	@Inject
	@DependentScopedSession
	private Session session;
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private HandDataGenerator rootDataGenerator;


	public void start() {
		try {
			Hand theHand = (Hand)session.createQuery("from Hand a where a.name = :name").setText("name", dataGeneratorProperty.getProperty("hand.name_0")).uniqueResult();
			transaction.begin();
			if ( theHand == null ) {
				List<Hand> hands = rootDataGenerator.createHand();
				for ( Hand hand : hands ) {
					session.persist(hand);
				}
				rootDataGenerator.populateHand(hands);
				session.flush();
				transaction.commit();
			}
		} catch (Exception e) {
			try {
				transaction.rollback();
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
	}

}