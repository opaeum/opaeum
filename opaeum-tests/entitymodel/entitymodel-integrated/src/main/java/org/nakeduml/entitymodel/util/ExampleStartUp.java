package org.opeum.entitymodel.util;

import entitymodel.bodyparts.Hand;
import entitymodel.bodyparts.HandDataGenerator;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;
import org.opeum.runtime.adaptor.DataGeneratorProperty;
import org.opeum.seam3.persistence.DependentScopedSession;

public class ExampleStartUp {
	@DependentScopedSession
	@Inject
	private Session session;
	@Inject
	@DefaultTransaction
	private SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private HandDataGenerator rootDataGenerator;


	public void start(@Observes @Started WebApplication webapp) {
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