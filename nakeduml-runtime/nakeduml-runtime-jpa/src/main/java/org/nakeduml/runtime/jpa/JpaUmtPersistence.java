package org.nakeduml.runtime.jpa;

import javax.persistence.EntityManager;

import org.nakeduml.runtime.persistence.UmtPersistence;

public class JpaUmtPersistence extends AbstractJpaPersistence implements UmtPersistence{
	EntityManager entityManager;
	public JpaUmtPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	@Override
	public void beginTransaction(){
		entityManager.getTransaction().begin();
	}
	@Override
	public boolean isActive(){
		return entityManager.getTransaction().isActive();
	}
	@Override
	public void rollbackTransaction(){
		entityManager.getTransaction().rollback();
	}
	@Override
	public void commitTransaction(){
		entityManager.getTransaction().commit();
	}
	@Override
	public boolean isRolledBack(){
		return entityManager.getTransaction().getRollbackOnly();
	}
	@Override
	public void setTransactionTimeout(int i){
		
	}
}
