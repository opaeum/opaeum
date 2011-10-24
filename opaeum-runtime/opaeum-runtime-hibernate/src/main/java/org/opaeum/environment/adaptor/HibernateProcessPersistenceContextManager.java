package org.opaeum.environment.adaptor;

import org.drools.persistence.PersistenceContext;
import org.hibernate.Session;
import org.jbpm.persistence.ProcessPersistenceContext;
import org.jbpm.persistence.ProcessPersistenceContextManager;

public class HibernateProcessPersistenceContextManager implements ProcessPersistenceContextManager {

	private Session session;
	
	public HibernateProcessPersistenceContextManager(Session session) {
		this.session = session;
	}

	@Override
	public void beginCommandScopedEntityManager() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void endCommandScopedEntityManager() {
	}

	@Override
	public PersistenceContext getApplicationScopedPersistenceContext() {
		return null;
	}

	@Override
	public PersistenceContext getCommandScopedPersistenceContext() {
		return null;
	}

	@Override
	public ProcessPersistenceContext getProcessPersistenceContext() {
		return new HibernateProcessPersistenceContext(session);
	}

}
