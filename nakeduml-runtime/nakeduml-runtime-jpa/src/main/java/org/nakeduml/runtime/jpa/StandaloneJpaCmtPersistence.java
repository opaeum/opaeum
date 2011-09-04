package org.nakeduml.runtime.jpa;

import javax.persistence.EntityManager;

import org.nakeduml.runtime.persistence.CmtPersistence;

public class StandaloneJpaCmtPersistence extends AbstractJpaPersistence implements CmtPersistence{
	EntityManager entityManager;
	public StandaloneJpaCmtPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
}
