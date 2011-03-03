package net.sf.nakeduml.jbpm;

import org.drools.persistence.PersistenceContextManager;
import org.drools.persistence.TransactionManager;
import org.drools.persistence.map.EnvironmentBuilder;
import org.hibernate.Session;

public class HibernateEnvironmentBuilder implements EnvironmentBuilder {

	private Session session;
	
	public HibernateEnvironmentBuilder(Session hibernateSession) {
		this.session = hibernateSession;
	}

	@Override
	public PersistenceContextManager getPersistenceContextManager() {
		return new HibernateProcessPersistenceContextManager(session);
	}

	@Override
	public TransactionManager getTransactionManager() {
		return null;
	}

}
