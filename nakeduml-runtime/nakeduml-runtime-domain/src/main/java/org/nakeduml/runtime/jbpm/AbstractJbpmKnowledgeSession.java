package org.nakeduml.runtime.jbpm;

import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentFactory;
import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.marshalling.impl.ClassObjectMarshallingStrategyAcceptor;
import org.drools.marshalling.impl.SerializablePlaceholderResolverStrategy;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.nakeduml.runtime.environment.Environment;

public abstract class AbstractJbpmKnowledgeSession{
	private static AbstractJbpmKnowledgeBase knowledgeBase;
	protected StatefulKnowledgeSession knowledgeSession;
	public AbstractJbpmKnowledgeSession(){
		super();
	}
	protected static AbstractJbpmKnowledgeBase getJbpmKnowledgeBase(){
		if(knowledgeBase == null){
			knowledgeBase = (AbstractJbpmKnowledgeBase) Environment.instantiateImplementation(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION);
		}
		return knowledgeBase;
	}
	public StatefulKnowledgeSession getKnowledgeSession(){
		if(this.knowledgeSession == null){
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}
	protected StatefulKnowledgeSession createKnowledgeSession(){
		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
		Properties properties = new Properties();
		properties.setProperty("drools.processInstanceManagerFactory", "org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
		properties.setProperty("drools.workItemManagerFactory", "org.drools.persistence.jpa.processinstance.JPAWorkItemManagerFactory");
		properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
		SessionConfiguration config = new SessionConfiguration(properties);
		
		final org.drools.runtime.Environment environment = EnvironmentFactory.newEnvironment();
		environment.set(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER, getPersistenceContextManager(environment));
		environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[]{
				getPlaceholderResolverStrategy(environment),new SerializablePlaceholderResolverStrategy(ClassObjectMarshallingStrategyAcceptor.DEFAULT)
		});
		return kbase.newStatefulKnowledgeSession(config, environment);
	}
	protected abstract ObjectMarshallingStrategy getPlaceholderResolverStrategy(org.drools.runtime.Environment environment);
	protected abstract ProcessPersistenceContextManager getPersistenceContextManager(org.drools.runtime.Environment environment);
}