package org.nakeduml.environment.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.jbpm.domain.AbstractJbpmKnowledgeBase;

public abstract class AbstractDomainEnvironment extends Environment {
	private MockTimeEventDispatcher timeEventDispatcher = new MockTimeEventDispatcher();
	private MockSignalDispatcher signalDispatcher = new MockSignalDispatcher();
	private StatefulKnowledgeSession knowledgeSession;
	private Session hibernateSession;
	private SessionFactory sessionFactory;
	private AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;
	private Map<Class<?>, Object> components=new HashMap<Class<?>, Object>();
	public <T> void mockComponent(Class<T> clazz, T component){
		this.components.put(clazz,component);
	}
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (clazz == ITimeEventDispatcher.class) {
			return (T) timeEventDispatcher;
		} else if (clazz == ISignalDispatcher.class) {
			return (T) signalDispatcher;
		} else if (clazz == StatefulKnowledgeSession.class) {
			return (T) getKnowledgeSession();
		} else if (clazz == Session.class) {
			return (T) getHibernateSession();
		}
		return (T) components.get(clazz);
	}

	private Session getHibernateSession() {
		if (this.hibernateSession == null) {
			if (this.sessionFactory == null) {
				Configuration hibernateConfiguration = new Configuration();
				hibernateConfiguration.configure(getHibernateConfigName());
				sessionFactory = hibernateConfiguration.buildSessionFactory();
			}
			this.hibernateSession = sessionFactory.openSession();
		}
		return this.hibernateSession;
	}

	private StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			if(this.abstractJbpmKnowledgeBase==null){
				this.abstractJbpmKnowledgeBase=createJbpmKnowledgeBase();
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

	protected abstract AbstractJbpmKnowledgeBase createJbpmKnowledgeBase();

	protected abstract String getHibernateConfigName();
}