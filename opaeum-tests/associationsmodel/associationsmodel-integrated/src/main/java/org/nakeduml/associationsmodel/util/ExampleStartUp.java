package org.opaeum.associationsmodel.util;

import assocationsmodel.root.Root;
import assocationsmodel.root.RootDataGenerator;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;
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
	private RootDataGenerator rootDataGenerator;


	public void start(@Started @Observes WebApplication webapp) {
		try {
			Root theRoot = (Root)session.createQuery("from Root a where a.name = :name").setText("name", dataGeneratorProperty.getProperty("root.name_0")).uniqueResult();
			transaction.begin();
			if ( theRoot == null ) {
				List<Root> roots = rootDataGenerator.createRoot();
				for ( Root root : roots ) {
					session.persist(root);
				}
				rootDataGenerator.populateRoot(roots);
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