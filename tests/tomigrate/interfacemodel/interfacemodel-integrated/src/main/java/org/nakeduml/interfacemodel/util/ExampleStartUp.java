package org.opaeum.interfacemodel.util;

import interfacetestmodel.interfacetests.RootClass;
import interfacetestmodel.interfacetests.RootClassDataGenerator;

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
	@DependentScopedSession
	@Inject
	private Session session;
	@DefaultTransaction
	@Inject
	private SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private RootClassDataGenerator rootDataGenerator;


	public void start(@Observes @Started WebApplication webapp) {
		try {
			RootClass theRootClass = (RootClass)session.createQuery("from RootClass a where a.name = :name").setText("name", dataGeneratorProperty.getProperty("rootclass.name_0")).uniqueResult();
			transaction.begin();
			if ( theRootClass == null ) {
				List<RootClass> rootClasses = rootDataGenerator.createRootClass();
				for ( RootClass rootclass : rootClasses ) {
					session.persist(rootclass);
				}
				rootDataGenerator.populateRootClass(rootClasses);
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