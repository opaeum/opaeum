package org.nakeduml.hibernate.domain;

import org.hibernate.Session;
import org.nakeduml.runtime.persistence.ConversationalPersistence;

public class HibernateConversationalPersistence extends AbstractHibernatePersistence implements ConversationalPersistence{
	public HibernateConversationalPersistence(Session session){
		super(session);
	}
	@Override
	public void close(){
		session.close();
	}
	@Override
	public void flush(){
		session.flush();
	}
	
}
