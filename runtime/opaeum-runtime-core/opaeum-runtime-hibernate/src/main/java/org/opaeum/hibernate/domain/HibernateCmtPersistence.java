package org.opaeum.hibernate.domain;

import org.hibernate.Session;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;

public class HibernateCmtPersistence extends AbstractHibernatePersistence implements CmtPersistence{

	public HibernateCmtPersistence(Session session,Environment environment){
		super(session,environment);
	}
}
