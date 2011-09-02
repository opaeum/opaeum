package org.nakeduml.environment.seam2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentFactory;
import org.drools.impl.EnvironmentImpl;
import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.marshalling.impl.ClassObjectMarshallingStrategyAcceptor;
import org.drools.marshalling.impl.SerializablePlaceholderResolverStrategy;
import org.drools.persistence.jpa.JpaPersistenceContext;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jboss.seam.annotations.In;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.transaction.UserTransaction;
import org.jbpm.persistence.JpaProcessPersistenceContext;
import org.jbpm.persistence.JpaProcessPersistenceContextManager;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeSession;

public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession implements Synchronization{
	@In
	UserTransaction transaction;
	@In
	private EntityManager entityManager;
	@Override
	public void afterCompletion(int arg0){
		if(Contexts.isEventContextActive()){
			Contexts.getEventContext().remove("jbpmKnowledgeSession");
		}
	}
	@Override
	public void beforeCompletion(){
	}
	@Override
	protected ObjectMarshallingStrategy getPlaceholderResolverStrategy(final Environment environment){
		return new JPAPlaceholderResolverStrategy(environment){
			@Override
			public Object read(ObjectInputStream is) throws IOException,ClassNotFoundException{
				String canonicalName = is.readUTF();
				Object id = is.readObject();
				EntityManager em = (EntityManager) environment.get(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER);
				return em.find(Class.forName(canonicalName), id);
			}
		};
	}
	@Override
	protected ProcessPersistenceContextManager getPersistenceContextManager(Environment env){
		env.set(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER, entityManager);
		return new JpaProcessPersistenceContextManager(env);
	}
}
