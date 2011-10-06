package org.opeum.persistence;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import net.sf.opeum.util.DataGeneratorProperty;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;

import datagenerationtest.org.opeum.God;
import datagenerationtest.org.opeum.GodDataGenerator;

public class StartUpLoadData {

	@Inject
	Session session;
	@Inject
	@DefaultTransaction
	SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private GodDataGenerator godDataGenerator;
	 
	public void loadData(@Observes @Started WebApplication webapp) {
		try {
			transaction.begin();
			God thegod = (God) session.createQuery("from God a where a.name = :name")
					.setText("name", dataGeneratorProperty.getProperty("god.name_0")).uniqueResult();
			if (thegod == null) {
				List<God> gods = godDataGenerator.createGod();
				for (God god : gods) {
					session.persist(god);
				}
				godDataGenerator.populateGod(gods);
				session.flush();
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
