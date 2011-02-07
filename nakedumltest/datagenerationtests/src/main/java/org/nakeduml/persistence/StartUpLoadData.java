package org.nakeduml.persistence;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import net.sf.nakeduml.util.DataGeneratorProperty;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;

import datagenerationtest.datagenerationtests.nakeduml.Hand;
import datagenerationtest.datagenerationtests.nakeduml.HandDataGenerator;

public class StartUpLoadData {

	@Inject
	Session session;
	@Inject
	@DefaultTransaction
	SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private HandDataGenerator handDataGenerator;
	 
	public void onMessage(@Observes @Started WebApplication webapp) {
		try {
			transaction.begin();
			Hand cmApplication = (Hand) session.createQuery("from Hand a where a.name = :name")
					.setText("name", dataGeneratorProperty.getProperty("hand1.name_0")).uniqueResult();
			if (cmApplication == null) {
				List<Hand> hands;
				hands = handDataGenerator.createHand();
				for (Hand hand : hands) {
					session.persist(hand);
					handDataGenerator.populateHand(hand);
				}
				transaction.commit();
			}
		} catch (Exception e) {
			try {
				transaction.rollback();
			} catch (Exception e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		}
	}
	
}
