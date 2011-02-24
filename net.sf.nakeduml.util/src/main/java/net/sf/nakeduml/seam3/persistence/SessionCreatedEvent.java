package net.sf.nakeduml.seam3.persistence;

import javax.enterprise.event.Observes;

import org.jboss.seam.persistence.hibernate.SeamManagedHibernateSessionCreated;

public class SessionCreatedEvent {

	public void created(@Observes SeamManagedHibernateSessionCreated session) {
		session.getSession().enableFilter("noDeletedObjects");
	}
	
}
