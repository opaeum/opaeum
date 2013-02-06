package org.opaeum.runtime.jpa;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.opaeum.hibernate.domain.HibernateCmtPersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;

public class StandaloneJpaCmtPersistence extends HibernateCmtPersistence implements CmtPersistence{
	public StandaloneJpaCmtPersistence(EntityManager entityManager,Environment e){
		super((Session) entityManager.getDelegate(),e);
	}
}
