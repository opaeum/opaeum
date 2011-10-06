package org.opeum.runtime.environment;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Properties;

import org.opeum.runtime.domain.IActiveObject;
import org.opeum.runtime.domain.ISignal;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.event.EventService;
import org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase;
import org.opeum.runtime.persistence.DatabaseManagementSystem;

public abstract class Environment {

	public static final String HIBERNATE_CONFIG_NAME = "opeum.hibernate.config.name";
	public static final String JBPM_KNOWLEDGE_BASE_IMPLEMENTATION = "opeum.jbpm.knowledgebase.implementation";
	public static final String ENVIRONMENT_IMPLEMENTATION = "opeum.environment.implementation";
	public static final String PROPERTIES_FILE_NAME = "opeum.env.properties";
	public static final String PERSISTENT_NAME_CLASS_MAP = "opeum.persistentname.classmap.implementation";
	public static final String DB_USER = "opeum.database.user";
	public static final String JDBC_CONNECTION_URL = "opeum.jdbc.connection.url";
	public static final String DBMS = "opeum.database.management.system";
	private static final EventService EVENT_SERVICE = new EventService();
	protected static ThreadLocal<Environment> instance = new ThreadLocal<Environment>();
	protected static JavaMetaInfoMap persistentNameClassMap;
	protected Properties properties;
	protected static Class<? extends Environment> defaultImplementation;
	private DatabaseManagementSystem dbms;

	public static Environment getInstance() {
		if (instance.get() == null) {
			if (defaultImplementation == null) {
				instance.set((Environment) instantiateImplementation(ENVIRONMENT_IMPLEMENTATION));
			} else {
				Environment newInstance = IntrospectionUtil.newInstance(defaultImplementation);
				newInstance.properties = loadProperties();
				instance.set(newInstance);
			}
		}
		return instance.get();
	}

	public static JavaMetaInfoMap getMetaInfoMap() {
		if (persistentNameClassMap == null) {
			persistentNameClassMap = (JavaMetaInfoMap) instantiateImplementation(PERSISTENT_NAME_CLASS_MAP);
		}
		return persistentNameClassMap;
	}

	public abstract <T> Class<T> getImplementationClass(T o);

	public static Object instantiateImplementation(String environmentImplementation) {
		Object newInstance;
		Properties properties = loadProperties();
		Class<?> cls = IntrospectionUtil.classForName(properties.getProperty(environmentImplementation));
		newInstance = IntrospectionUtil.newInstance(cls);
		if (newInstance instanceof Environment) {
			((Environment) newInstance).properties = properties;
		}
		return newInstance;
	}

	public DatabaseManagementSystem getDatabaseManagementSystem() {
		if (this.dbms == null) {
			this.dbms = DatabaseManagementSystem.valueOf(getProperty(DBMS));
		}
		return dbms;
	}

	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	public static Properties loadProperties() {
		Properties properties;
		try {
			properties = new Properties();
			URL resource = Thread.currentThread().getContextClassLoader().getResource("/" + PROPERTIES_FILE_NAME);
			if (resource == null) {
				resource = Thread.currentThread().getContextClassLoader().getResource(PROPERTIES_FILE_NAME);
			}
			properties.load(resource.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}

	protected AbstractJbpmKnowledgeBase createJbpmKnowledgeBase() {
		return (AbstractJbpmKnowledgeBase) instantiateImplementation(JBPM_KNOWLEDGE_BASE_IMPLEMENTATION);
	}

	public abstract <T> T getComponent(Class<T> clazz);

	public abstract <T> T getComponent(Class<T> clazz, Annotation qualifiers);

	public abstract void reset();

	public abstract void endRequestContext();

	public abstract void startRequestContext();

	public EventService getEventService() {
		return EVENT_SERVICE;
	}

	public abstract void sendSignal(IActiveObject target, ISignal s);

}
