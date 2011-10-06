package org.opeum.environment.adaptor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.Environment;
import org.hibernate.Session;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeSession;

public class HibernateJbpmKnowledgeSession extends AbstractJbpmKnowledgeSession{
	protected JPAPlaceholderResolverStrategy getPlaceholderResolverStrategy(final org.drools.runtime.Environment environment){
		return new JPAPlaceholderResolverStrategy(environment){
			@Override
			public Object read(ObjectInputStream is) throws IOException,ClassNotFoundException{
				String canonicalName = is.readUTF();
				Object id = is.readObject();
				Class<?> clazz = Class.forName(canonicalName);
				Object obj = getHibernateSession().get(clazz, (Serializable) id);
				return obj;
			}
			public void write(ObjectOutputStream os,Object object) throws IOException{
				os.writeUTF(IntrospectionUtil.getOriginalClass(object).getCanonicalName());
				os.writeObject(getClassIdValue(object));
			}
		};
	}
	protected ProcessPersistenceContextManager getPersistenceContextManager(Environment env){
		return new HibernateProcessPersistenceContextManager(getHibernateSession());
	}
	public Session getHibernateSession(){
		final Session session = org.opeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
		return session;
	}
}
