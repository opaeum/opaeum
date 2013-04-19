package org.opaeum.jbpmtestintegration.test;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.ApplicationDataGenerator;

import org.hibernate.Session;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.opaeum.runtime.adaptor.DataGeneratorProperty;
import org.opaeum.seam3.persistence.DependentScopedSession;

public class StartDataGeneration {

	@DependentScopedSession
	@Inject
	Session session;
	@Inject
	@DefaultTransaction
	SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private ApplicationDataGenerator applicationDataGenerator;

	public void start(@Observes @Started WebApplication webapp) {
		try {
			transaction.begin();
			Application application = (Application) session.createQuery("from Application a where a.name = :name")
					.setText("name", dataGeneratorProperty.getProperty("application.name_0")).uniqueResult();
			if (application == null) {
				List<Application> applicationS = applicationDataGenerator.createApplication();
				for (Application god : applicationS) {
					session.persist(god);
				}
				applicationDataGenerator.populateApplication(applicationS);
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
