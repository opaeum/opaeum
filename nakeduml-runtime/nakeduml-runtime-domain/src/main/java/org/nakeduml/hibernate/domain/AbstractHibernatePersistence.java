package org.nakeduml.hibernate.domain;

import org.hibernate.Session;
import org.nakeduml.environment.AbstractPersistence;
import org.nakeduml.environment.ConversationalPersistence;
import org.nakeduml.environment.Query;

public abstract class AbstractHibernatePersistence implements AbstractPersistence{
	protected Session session;
	
	public AbstractHibernatePersistence(Session session){
		super();
		this.session = session;
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
}
