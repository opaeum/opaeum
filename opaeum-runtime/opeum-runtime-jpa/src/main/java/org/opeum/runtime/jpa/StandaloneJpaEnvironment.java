package org.opeum.runtime.jpa;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.opeum.hibernate.domain.EventOccurrence;
import org.opeum.runtime.domain.IActiveObject;
import org.opeum.runtime.domain.ISignal;
import org.opeum.runtime.environment.Environment;
import org.opeum.runtime.event.IEventHandler;
import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase;
import org.opeum.runtime.persistence.CmtPersistence;
import org.opeum.runtime.persistence.ConversationalPersistence;
import org.opeum.runtime.persistence.UmtPersistence;

public class StandaloneJpaEnvironment extends Environment {
	Map<Class<?>, Object> components = new HashMap<Class<?>, Object>();
	private static EntityManagerFactory entityManagerFactory;
	private StandaloneJpaUmtPersistence txPersistence;
	private StandaloneJpaCmtPersistence cmtPersistence;
	private StandaloneJpaConversationalPersistence persistence;
	private StandaloneJpaJbpmKnowledgeSession knowledgeSession;
	private EntityManager entityManager;
	private static AbstractJbpmKnowledgeBase abstractJbpmKnowledgeBase;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Class<T> getImplementationClass(T o) {
		return (Class<T>) o.getClass();
	}

	public static StandaloneJpaEnvironment getInstance() {
		defaultImplementation = StandaloneJpaEnvironment.class;
		return (StandaloneJpaEnvironment) Environment.getInstance();
	}

	private EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			Set<String> schemas = new HashSet<String>();
			for (Class<?> class1 : getMetaInfoMap().getAllClasses()) {
				if (class1.isAnnotationPresent(Table.class)) {
					schemas.add(class1.getAnnotation(Table.class).schema());
				}
			}
			schemas.remove(null);
			schemas.remove("");
			try {
				try {
					Class.forName("org.hsqldb.jdbcDriver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			
				Connection connection = DriverManager.getConnection(super.getProperty(JDBC_CONNECTION_URL),
						Environment.getInstance().getProperty(Environment.DB_USER), "");
				Statement st = connection.createStatement();
				for (String string : schemas) {
					try {
						st.executeUpdate("CREATE SCHEMA " + string + " AUTHORIZATION DBA");
						connection.commit();
					} catch (Exception e) {
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			entityManagerFactory = Persistence.createEntityManagerFactory(getProperty(HIBERNATE_CONFIG_NAME));

		}
		return entityManagerFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (components.get(clazz.getName()) != null) {
			return (T) components.get(clazz.getName());
		} else if (clazz == StatefulKnowledgeSession.class) {
			return (T) getKnowledgeSession();
		} else if (clazz == ConversationalPersistence.class) {
			return (T) getPersistence();
		} else if (clazz == CmtPersistence.class) {
			return (T) getCmtPersistence();
		} else if (clazz == UmtPersistence.class) {
			return (T) getUmtPersistence();
		} else {
			return (T) components.get(clazz);
		}
	}

	public ConversationalPersistence getPersistence() {
		if (persistence == null) {
			persistence = new StandaloneJpaConversationalPersistence(getEntityManager());
		}
		return persistence;
	}

	private EntityManager getEntityManager() {
		if(this.entityManager==null){
			entityManager=openHibernateSession();
			((Session)entityManager.getDelegate()).enableFilter("noDeletedObjects");
		}
		return entityManager;
	}

	public StandaloneJpaUmtPersistence getUmtPersistence() {
		if (txPersistence == null) {
			txPersistence = new StandaloneJpaUmtPersistence (getEntityManager());
		}
		return txPersistence;
	}

	private EntityManager openHibernateSession() {
		return getEntityManagerFactory().createEntityManager();
	}

	public CmtPersistence getCmtPersistence() {
		if (cmtPersistence == null) {
			cmtPersistence = new StandaloneJpaCmtPersistence(getEntityManager());
		}
		return cmtPersistence;
	}

	@Override
	public <T> T getComponent(Class<T> clazz, Annotation qualifiers) {
		return getComponent(clazz);
	}

	@Override
	public void reset() {
		this.entityManager=null;
		this.cmtPersistence = null;
		this.persistence = null;
		this.txPersistence = null;
		this.knowledgeSession=null;
	}

	@Override
	public void endRequestContext() {
		reset();
	}

	@Override
	public void startRequestContext() {
	}

	@Override
	public void sendSignal(IActiveObject target, ISignal s) {
		IEventHandler handler = getMetaInfoMap().getEventHandler(s.getUid());
		EventOccurrence occurrence = new EventOccurrence(target, handler);
		getCmtPersistence().persist(occurrence);
		getEventService().scheduleEvent(occurrence);
	}

	private StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			if (abstractJbpmKnowledgeBase == null) {
				abstractJbpmKnowledgeBase = createJbpmKnowledgeBase();
			}
			Properties props = new Properties();
			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
			this.knowledgeSession = new StandaloneJpaJbpmKnowledgeSession(((StandaloneJpaUmtPersistence)getUmtPersistence()).getEntityManager());
		}
		return knowledgeSession.getKnowledgeSession();
	}
}
