package org.nakeduml.environment;

import java.io.IOException;
import java.util.Properties;

public abstract class Environment {
	public static final String HIBERNATE_CONFIG_NAME = "nakeduml.hibernate.config.name";
	public static final String JBPM_KNOWLEDGE_BASE_IMPLEMENTATION = "nakeduml.jbpm.knowledgebase.implementation";
	public static final String ENVIRONMENT_IMPLEMENTATION = "nakeduml.environment.implementation";
	public static final String PROPERTIES_FILE_NAME = "nakeduml.env.properties";
	private static ThreadLocal<Environment> instance;

	/**
	 * For mocking purposes
	 * 
	 * @param e
	 */
	public static void setEnvironment(Environment e) {
		instance.set(e);
	}

	public static Environment getInstance() {
		if (instance.get() == null) {
			instance.set((Environment) instantiateImplementation(ENVIRONMENT_IMPLEMENTATION));
		}
		return instance.get();
	}

	public static Object instantiateImplementation(String environmentImplementation) {
		Object newInstance;
		try {
			Properties properties = loadProperties();
			newInstance = (Environment) Class.forName(properties.getProperty(environmentImplementation)).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}

	public static Properties loadProperties() {
		Properties properties;
		try {
			properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResource("/"+PROPERTIES_FILE_NAME).openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}

	public abstract <T> T getComponent(Class<T> clazz);
}
