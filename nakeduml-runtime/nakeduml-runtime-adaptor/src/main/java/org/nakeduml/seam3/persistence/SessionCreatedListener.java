package org.nakeduml.seam3.persistence;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;

import org.jboss.seam.persistence.hibernate.SeamManagedHibernateSessionCreated;

public class SessionCreatedListener {
	public void createdDefaultDependent(@Observes @Default @DependentScopedSession SeamManagedHibernateSessionCreated session) {
		session.getSession().enableFilter("noDeletedObjects");
	}
	
	public void createdDefaultTransaction(@Observes @Default @TransactionScopedSession SeamManagedHibernateSessionCreated session) {
		session.getSession().enableFilter("noDeletedObjects");
	}
	
	public void createdDefaultRequest(@Observes @Default SeamManagedHibernateSessionCreated session) {
		session.getSession().enableFilter("noDeletedObjects");
	}	
	
}
