package processmodel.util;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.opeum.runtime.adaptor.DataGeneratorProperty;
import org.opeum.seam3.persistence.DependentScopedSession;

import processmodel.processes.ProcessOwner;
import processmodel.processes.ProcessOwnerDataGenerator;

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
	private ProcessOwnerDataGenerator rootDataGenerator;


	public void start() {
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