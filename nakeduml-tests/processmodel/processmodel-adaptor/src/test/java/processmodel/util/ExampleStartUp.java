package processmodel.util;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.nakeduml.runtime.adaptor.DataGeneratorProperty;
import org.nakeduml.seam3.persistence.DependentScopedSession;

import processmodel.humancentric.SalesDepartment;
import processmodel.humancentric.SalesDepartmentDataGenerator;

public class ExampleStartUp {
	@Inject
	@DependentScopedSession
	private Session session;
	@DefaultTransaction
	@Inject
	private SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private SalesDepartmentDataGenerator rootDataGenerator;


	public void start() {
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