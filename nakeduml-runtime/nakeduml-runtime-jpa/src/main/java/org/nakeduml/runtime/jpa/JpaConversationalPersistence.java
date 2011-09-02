package org.nakeduml.runtime.jpa;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.nakeduml.runtime.persistence.CmtPersistence;
import org.nakeduml.runtime.persistence.ConversationalPersistence;

public class JpaConversationalPersistence extends AbstractJpaPersistence implements ConversationalPersistence{
	EntityManager entityManager;
	public JpaConversationalPersistence(EntityManager entityManager){
		super();
		this.entityManager = entityManager;
	}
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	@Override
	public void close(){
		entityManager.close();
	}
	@Override
	public void flush(){
		entityManager.flush();
	}
}
