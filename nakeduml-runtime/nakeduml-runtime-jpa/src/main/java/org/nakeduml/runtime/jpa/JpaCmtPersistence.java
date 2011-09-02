package org.nakeduml.runtime.jpa;

import javax.persistence.EntityManager;

import org.nakeduml.runtime.persistence.CmtPersistence;

public class JpaCmtPersistence extends AbstractJpaPersistence implements CmtPersistence{
	EntityManager entityManager;
	public JpaCmtPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
}
