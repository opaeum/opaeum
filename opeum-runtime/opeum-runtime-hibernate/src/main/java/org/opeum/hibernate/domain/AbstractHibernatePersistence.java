package org.opeum.hibernate.domain;

import java.util.Collection;

import org.hibernate.Session;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.persistence.AbstractPersistence;
import org.opeum.runtime.persistence.Query;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernatePersistence implements AbstractPersistence{
	protected Session session;
	public AbstractHibernatePersistence(Session session){
		super();
		this.session = session;
	}
	public void refresh(IPersistentObject...ps){
		for(IPersistentObject p:ps){
			session.refresh(p);
		}
	}
	@Override
	public <T>T getReference(Class<T> t,Long id){
		return (T) session.load(t, id);
	}
	@Override
	public <T>T find(Class<T> t,Long id){
		return (T) session.get(t, id);
	}
	@Override
	public void persist(Object object){
		session.persist(object);
	}
	@Override
	public Query createQuery(String q){
		org.hibernate.Query createQuery = session.createQuery(q);
		return new HibernateQuery(createQuery);
	}
	@Override
	public <T>Collection<T> readAll(Class<T> c){
		return session.createCriteria(c).list();
	}
	@Override
	public void remove(IPersistentObject o){
		session.delete(o);
	}
}
