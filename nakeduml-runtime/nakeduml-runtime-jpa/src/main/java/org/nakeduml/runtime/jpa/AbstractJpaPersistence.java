package org.nakeduml.runtime.jpa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.persistence.AbstractPersistence;
import org.nakeduml.runtime.persistence.Query;

public abstract class AbstractJpaPersistence implements AbstractPersistence{
	@Override
	public <T>T getReference(Class<T> t,Long id){
		return getEntityManager().getReference(t, id);
	}
	@Override
	public <T>T find(Class<T> t,Long id){
		return getEntityManager().find(t, id);
	}
	@Override
	public void persist(Object object){
		getEntityManager().persist(object);
	}
	@Override
	public Query createQuery(String q){
		return new JpaQuery(getEntityManager().createQuery(q));
	}
	@Override
	public <T>Collection<T> readAll(Class<T> c){
		Entity annotation = c.getAnnotation(Entity.class);
		return getEntityManager().createQuery("select o from " +annotation.name() + " o").getResultList();
	}
	@Override
	public void remove(IPersistentObject event){
		getEntityManager().remove(event);
	}
	protected abstract EntityManager getEntityManager();
}
