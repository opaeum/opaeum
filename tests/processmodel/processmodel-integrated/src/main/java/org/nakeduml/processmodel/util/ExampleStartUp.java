package org.opaeum.processmodel.util;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.opaeum.runtime.adaptor.DataGeneratorProperty;
import org.opaeum.seam3.persistence.DependentScopedSession;

import processmodel.processes.ProcessOwner;
import processmodel.processes.ProcessOwnerDataGenerator;

public class ExampleStartUp {
	@DependentScopedSession
	@Inject
	private Session session;
	@DefaultTransaction
	@Inject
	private SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private ProcessOwnerDataGenerator rootDataGenerator;


	public void start(@Started @Observes WebApplication webapp) {
		try {
			ProcessOwner theProcessOwner = (ProcessOwner)session.createQuery("from ProcessOwner a where a.name = :name").setText("name", dataGeneratorProperty.getProperty("processOwner.name_0")).uniqueResult();
			transaction.begin();
			if ( theProcessOwner == null ) {
				List<ProcessOwner> processOwners = rootDataGenerator.createProcessOwner();
				for ( ProcessOwner processowner : processOwners ) {
					session.persist(processowner);
				}
				rootDataGenerator.populateProcessOwner(processOwners);
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