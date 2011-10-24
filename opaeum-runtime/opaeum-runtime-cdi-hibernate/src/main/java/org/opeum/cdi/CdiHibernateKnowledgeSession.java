package org.opeum.cdi;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.opeum.environment.adaptor.HibernateJbpmKnowledgeSession;

public class CdiHibernateKnowledgeSession extends HibernateJbpmKnowledgeSession{
	@Inject
	SessionFactory sessionFactory;
	public Session getHibernateSession(){
		return sessionFactory.getCurrentSession();
	}
	public void clear(){
		super.knowledgeSession=null;
		
	}
}
