package org.nakeduml.runtime.jpa;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.nakeduml.hibernate.domain.EventOccurrence;
import org.nakeduml.hibernate.domain.HibernateCmtPersistence;
import org.nakeduml.hibernate.domain.HibernateConversationalPersistence;
import org.nakeduml.hibernate.domain.HibernateUmtPersistence;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.environment.GenericSignalHandler;
import org.nakeduml.runtime.event.IEventHandler;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeBase;
import org.nakeduml.runtime.persistence.CmtPersistence;
import org.nakeduml.runtime.persistence.ConversationalPersistence;
import org.nakeduml.runtime.persistence.UmtPersistence;

public class JpaEnvironment extends Environment{
	Map<Class<?>,Object> components = new HashMap<Class<?>,Object>();
	private EntityManagerFactory entityManagerFactory;
	private JpaUmtPersistence txPersistence;
	private JpaCmtPersistence cmtPersistence;
	private JpaConversationalPersistence persistence;
	private StatefulKnowledgeSession knowledgeSession;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	@Override
	public <T>Class<T> getImplementationClass(T o){
		return (Class<T>) o.getClass();
	}
	private EntityManagerFactory getEntityManagerFactory(){
		if(this.entityManagerFactory == null){
			this.entityManagerFactory = Persistence.createEntityManagerFactory(getProperty(HIBERNATE_CONFIG_NAME));
		}
		return this.entityManagerFactory;
	}
	@Override
	public <T>T getComponent(Class<T> clazz){
		if(components.get(clazz.getName()) != null){
			return (T) components.get(clazz.getName());
		}else if(clazz == StatefulKnowledgeSession.class){
			return (T) getKnowledgeSession();
		}else if(clazz == ConversationalPersistence.class){
			return (T) getPersistence();
		}else if(clazz == CmtPersistence.class){
			return (T) getCmtPersistence();
		}else if(clazz == UmtPersistence.class){
			return (T) getUmtPersistence();
		}else{
			return (T) components.get(clazz);
		}
	}
	public ConversationalPersistence getPersistence(){
		if(persistence == null){
			persistence = new JpaConversationalPersistence(openHibernateSession());
		}
		return persistence;
	}
	public UmtPersistence getUmtPersistence(){
		if(txPersistence == null){
			txPersistence = new JpaUmtPersistence(openHibernateSession());
		}
		return txPersistence;
	}
	private EntityManager openHibernateSession(){
		return getEntityManagerFactory().createEntityManager();
	}
	public CmtPersistence getCmtPersistence(){
		if(cmtPersistence == null){
			cmtPersistence = new JpaCmtPersistence(openHibernateSession());
		}
		return cmtPersistence;
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		return getComponent(clazz);
	}
	@Override
	public void reset(){
	}
	@Override
	public void endRequestContext(){
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public void sendSignal(IActiveObject target,ISignal s){
		IEventHandler handler = getMetaInfoMap().getEventHandler(s.getUid());
		EventOccurrence occurrence = new EventOccurrence(target, handler);
		getCmtPersistence().persist(occurrence);
		getEventService().scheduleEvent(occurrence);
	}
	private StatefulKnowledgeSession getKnowledgeSession(){
		if(this.knowledgeSession == null){
			if(this.abstractJbpmKnowledgeBase == null){
				this.abstractJbpmKnowledgeBase = createJbpmKnowledgeBase();
			}
			Properties props = new Properties();
			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
			SessionConfiguration cfg = new SessionConfiguration(props);
			EnvironmentImpl env = new EnvironmentImpl();
			this.knowledgeSession = abstractJbpmKnowledgeBase.getKnowledgeBase().newStatefulKnowledgeSession(cfg, env);
		}
		return knowledgeSession;
	}
}
