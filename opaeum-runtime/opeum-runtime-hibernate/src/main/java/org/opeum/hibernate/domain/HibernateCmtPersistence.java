package org.opeum.hibernate.domain;

import org.hibernate.Session;
import org.opeum.runtime.persistence.CmtPersistence;

public class HibernateCmtPersistence extends AbstractHibernatePersistence implements CmtPersistence{

	public HibernateCmtPersistence(Session session){
		super(session);
	}
}