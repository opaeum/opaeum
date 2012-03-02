package org.opaeum.demo.demo1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opeum.demo1.util.Demo1JavaMetaInfoMap;
import org.opeum.demo1.util.Demo1KnowledgeBase;

public class Demo1JpaEnvironment extends StandaloneJpaEnvironment{
	public static Demo1JpaEnvironment getInstance(){
		defaultImplementation = Demo1JpaEnvironment.class;
		return (Demo1JpaEnvironment) Environment.getInstance();
	}
	private AbstractJbpmKnowledgeBase knowledgeBase;
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
			entityManagerFactory = Persistence.createEntityManagerFactory(getProperty(HIBERNATE_CONFIG_NAME));
		}
		return entityManagerFactory;
	}
	protected AbstractJbpmKnowledgeBase createJbpmKnowledgeBase(){
		if(knowledgeBase==null){
			knowledgeBase = new Demo1KnowledgeBase();
		}
		return knowledgeBase;
	}
}
