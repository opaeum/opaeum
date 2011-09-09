package org.nakeduml.runtime.jpa;

import javax.persistence.EntityManager;

public class StandaloneJpaUmtPersistence extends AbstractJpaUmtPersistence {

	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public StandaloneJpaUmtPersistence(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

}
