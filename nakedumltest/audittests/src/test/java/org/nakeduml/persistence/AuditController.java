package org.nakeduml.persistence;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import org.hibernate.Session;

import audittest.org.nakeduml.audit.God;

@TransactionAttribute
public class AuditController {

	@Inject Session session;
	
	public God captureGod(String name) {
		God god = new God();
		god.setName(name);
		session.persist(god);
		return god;
	}
	
	public void updateGodName(God god, String name) {
		god.setName(name);
		session.flush();
	}

	public void deleteGod(God god) {
		god.markDeleted();
		session.flush();
	}
	
}
