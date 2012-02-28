package org.opaeum.runtime.jpa;

import javax.persistence.EntityManager;

import org.opaeum.runtime.persistence.ConversationalPersistence;

public class StandaloneJpaConversationalPersistence extends AbstractJpaConversationalPersistence implements ConversationalPersistence{
	EntityManager entityManager;
	public StandaloneJpaConversationalPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	@Override
	public void flush(){
		if(entityManager.getTransaction()!=null && entityManager.getTransaction().isActive()){
		super.flush();
		}else{
			entityManager.getTransaction().begin();
			super.flush();
			entityManager.getTransaction().commit();
		}
	}
}
