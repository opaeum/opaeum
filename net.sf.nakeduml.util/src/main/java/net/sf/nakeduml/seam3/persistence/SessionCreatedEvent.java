package net.sf.nakeduml.seam3.persistence;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;

import org.jboss.seam.persistence.hibernate.SeamManagedHibernateSessionCreated;

public class SessionCreatedEvent {

	public void createdDefault(@Observes @Default SeamManagedHibernateSessionCreated session) {
		session.getSession().enableFilter("noDeletedObjects");
	}
	
}
