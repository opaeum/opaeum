package org.nakeduml.environment;

import java.io.IOException;
import java.util.Properties;

public abstract class Environment {
	private static ThreadLocal<Environment> instance;
	private static Properties properties;

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
			try {
				properties = new Properties();
				properties.load(Thread.currentThread().getContextClassLoader().getResource("/nakeduml.env.properties").openStream());
				instance.set((Environment) Class.forName(properties.getProperty("nakeduml.environment")).newInstance());
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return instance.get();
	}

	public abstract <T> T getComponent(Class<T> clazz);
}
