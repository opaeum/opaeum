package org.opaeum.demo.demo1;


import java.lang.reflect.Field;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.ejb.HibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.jpa.AbstractJpaEnvironment;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opeum.demo1.util.Demo1JavaMetaInfoMap;

public class Demo1JpaEnvironment extends AbstractJpaEnvironment{
	public static Demo1JpaEnvironment getInstance(){
		setContextClassLoader();
		defaultImplementation = Demo1JpaEnvironment.class;
		return (Demo1JpaEnvironment) Environment.getInstance();
	}
	@Override
	public JavaMetaInfoMap getMetaInfoMap(){
		if(metaInfoMap == null){
			metaInfoMap = new Demo1JavaMetaInfoMap();
		}
		return metaInfoMap;
	}
	protected EntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory == null){
			createSchemas();
			setContextClassLoader();
			entityManagerFactory = Persistence.createEntityManagerFactory(getProperty(HIBERNATE_CONFIG_NAME));
		}
		return entityManagerFactory;
	}
	private static void setContextClassLoader() {
		Thread currentThread = Thread.currentThread();
		Field field;
		try {
			field = Thread.class.getDeclaredField("contextClassLoader");
			field.setAccessible(true);
			ClassLoader classLoader = Demo1JpaEnvironment.class.getClassLoader();
			classLoader.loadClass(HibernatePersistence.class.getName());
			field.set(currentThread,classLoader);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
