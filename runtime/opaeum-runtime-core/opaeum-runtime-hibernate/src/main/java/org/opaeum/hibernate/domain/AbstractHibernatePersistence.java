package org.opaeum.hibernate.domain;

import java.util.Collection;

import org.hibernate.Session;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.Query;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernatePersistence implements AbstractPersistence{
	private Session session;
	public static int COUNT;
	public static int SESSION_COUNT;
	public AbstractHibernatePersistence(Session session){
		super();
		COUNT++;
		SESSION_COUNT++;
		this.setSession(session);
	}
	public void refresh(IPersistentObject...ps){
		for(IPersistentObject p:ps){
			getSession().refresh(p);
		}
	}
	public boolean isClosed(){
		return !session.isOpen();
	}
	@Override
	public <T>T getReference(Class<T> t,Long id){
		return (T) getSession().load(t, id);
	}
	@Override
	public <T>T find(Class<T> t,Long id){
		return (T) getSession().get(t, id);
	}
	@Override
	public void persist(Object object){
		getSession().persist(object);
	}
	@Override
	public Query createQuery(String q){
		org.hibernate.Query createQuery = getSession().createQuery(q);
		return new HibernateQuery(createQuery);
	}
	@Override
	public <T>Collection<T> readAll(Class<T> c){
		return getSession().createCriteria(c).list();
	}
	@Override
	public void remove(IPersistentObject o){
		getSession().delete(o);
	}
	protected Session getSession(){
		return session;
	}
	protected void setSession(Session session){
		this.session = session;
	}
	public void cleanUp() {
		session=null;
		SESSION_COUNT--;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		COUNT--;
	}
}
