package org.nakeduml.hibernate.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.event.IEventHandler;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeBase;
import org.nakeduml.runtime.persistence.CmtPersistence;
import org.nakeduml.runtime.persistence.ConversationalPersistence;
import org.nakeduml.runtime.persistence.UmtPersistence;

public class HibernateEnvironment extends Environment{
	private StatefulKnowledgeSession knowledgeSession;
	private ConversationalPersistence persistence;
	private UmtPersistence txPersistence;
	private SessionFactory sessionFactory;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	private Map<String,Object> components = new HashMap<String,Object>();
	private Session hibernateSession;
	private CmtPersistence cmtPersistence;
	public <T>void mockComponent(Class<T> clazz,T component){
		this.components.put(clazz.getName(), component);
	}
	public static HibernateEnvironment getInstance(){
		if(!(instance.get() instanceof HibernateEnvironment)){
			instance.set(new HibernateEnvironment());
		}
		return (HibernateEnvironment) instance.get();
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
			return null;
		}
	}
	@Override
	public <T>T getComponent(Class<T> clazz,Annotation qualifiers){
		throw new IllegalArgumentException("Qualifiers is not yet supported in the domain environment");
	}
	private Session openHibernateSession(){
		if(this.sessionFactory == null){
			Configuration hibernateConfiguration = new Configuration();
			hibernateConfiguration.configure(getHibernateConfigName());
			sessionFactory = hibernateConfiguration.buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	public void reset(){
		knowledgeSession = null;
		// TODO this should not be necessary
		abstractJbpmKnowledgeBase = null;
		if(hibernateSession != null){
			hibernateSession.close();
			hibernateSession = null;
			persistence = null;
		}
		components.clear();
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
	protected AbstractJbpmKnowledgeBase createJbpmKnowledgeBase(){
		return (AbstractJbpmKnowledgeBase) instantiateImplementation(JBPM_KNOWLEDGE_BASE_IMPLEMENTATION);
	}
	protected String getHibernateConfigName(){
		return loadProperties().getProperty(HIBERNATE_CONFIG_NAME);
	}
	@Override
	public <T>Class<T> getImplementationClass(T o){
		Class<T> originalClass = IntrospectionUtil.getOriginalClass(o);
		if(originalClass == Object.class || originalClass == Proxy.class){
			// Interface
			for(Class<?> class1:o.getClass().getInterfaces()){
				if(!(class1.getName().startsWith("java") || class1.getName().startsWith("org.jboss") || class1.getName().startsWith("org.nakeduml"))){
					return (Class<T>) class1;// return most significant interface
				}
			}
		}
		return originalClass;
	}
	public ConversationalPersistence getPersistence(){
		if(persistence == null){
			persistence = new HibernateConversationalPersistence(openHibernateSession());
		}
		return persistence;
	}
	public UmtPersistence getUmtPersistence(){
		if(txPersistence == null){
			txPersistence = new HibernateUmtPersistence(openHibernateSession());
		}
		return txPersistence;
	}
	public CmtPersistence getCmtPersistence(){
		if(cmtPersistence == null){
			cmtPersistence = new HibernateCmtPersistence(openHibernateSession());
		}
		return cmtPersistence;
	}
	@Override
	public void endRequestContext(){
	}
	@Override
	public void startRequestContext(){
	}
	@Override
	public void sendSignal(IActiveObject target, ISignal s){
		IEventHandler handler = getMetaInfoMap().getEventHandler(s.getUid());
		EventOccurrence occurrence=new EventOccurrence(target, handler);
		getCmtPersistence().persist(occurrence);
		getEventService().scheduleEvent(occurrence);
		
	}
}
