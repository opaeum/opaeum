package org.opaeum.runtime.jpa;

import javax.persistence.EntityManager;

import org.opaeum.runtime.persistence.ConversationalPersistence;

public class StandaloneJpaConversationalPersistence extends AbstractJpaConversationalPersistence implements ConversationalPersistence{
	EntityManager entityManager;
	public StandaloneJpaConversationalPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
		this.entityManager.getTransaction().begin();
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	@Override
	public void flush(){
		super.flush();
		this.entityManager.getTransaction().commit();
	}
}
