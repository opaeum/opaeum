package org.opaeum.runtime.jpa;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.opaeum.hibernate.domain.HibernateUmtPersistence;
import org.opaeum.runtime.environment.Environment;

public class StandaloneJpaUmtPersistence extends HibernateUmtPersistence {
	public StandaloneJpaUmtPersistence(EntityManager entityManager,Environment e) {
		super((Session) entityManager.getDelegate(),e);
	}

}
