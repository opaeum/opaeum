package org.nakeduml.processmodel.util;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;
import org.nakeduml.runtime.adaptor.DataGeneratorProperty;
import org.nakeduml.seam3.persistence.DependentScopedSession;

import processmodel.humancentric.SalesDepartment;
import processmodel.humancentric.SalesDepartmentDataGenerator;

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
	private SalesDepartmentDataGenerator rootDataGenerator;


	public void start(@Observes @Started WebApplication webapp) {
		try {
			SalesDepartment theSalesDepartment = (SalesDepartment)session.createQuery("from SalesDepartment a where a.name = :name").setText("name", dataGeneratorProperty.getProperty("salesdepartment.name_0")).uniqueResult();
			transaction.begin();
			if ( theSalesDepartment == null ) {
				List<SalesDepartment> salesDepartments = rootDataGenerator.createSalesDepartment();
				for ( SalesDepartment salesdepartment : salesDepartments ) {
					session.persist(salesdepartment);
				}
				rootDataGenerator.populateSalesDepartment(salesDepartments);
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