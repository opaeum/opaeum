package org.nakeduml.environment.adaptor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.inject.Inject;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentFactory;
import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.marshalling.impl.ClassObjectMarshallingStrategyAcceptor;
import org.drools.marshalling.impl.SerializablePlaceholderResolverStrategy;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.nakeduml.environment.AbstractJbpmKnowledgeBase;
import org.nakeduml.environment.Environment;
import org.nakeduml.jbpm.adaptor.HibernateEnvironmentBuilder;
import org.nakeduml.jbpm.adaptor.HibernateProcessPersistenceContext;
import org.nakeduml.jbpm.adaptor.HibernateProcessPersistenceContextManager;

public class JbpmKnowledgeSession {
	private StatefulKnowledgeSession knowledgeSession;
	@Inject
	protected Session session;

	protected AbstractJbpmKnowledgeBase getJbpmKnowledgeBase(){
		return (AbstractJbpmKnowledgeBase) Environment.instantiateImplementation(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION);
	}

	public StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}

	protected StatefulKnowledgeSession createKnowledgeSession() {
		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
		Properties properties = new Properties();
		properties.setProperty("drools.processInstanceManagerFactory",
				"org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
		properties.setProperty("drools.workItemManagerFactory", "org.drools.persistence.jpa.processinstance.JPAWorkItemManagerFactory");
		properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
		SessionConfiguration config = new SessionConfiguration(properties);
		final org.drools.runtime.Environment environment = EnvironmentFactory.newEnvironment();
		environment.set(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER,
				new HibernateEnvironmentBuilder(session).getPersistenceContextManager());
		environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[] {
				new JPAPlaceholderResolverStrategy(environment) {
					@Override
	 				public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
	 					String canonicalName = is.readUTF();
	 					Object id = is.readObject();
						HibernateProcessPersistenceContextManager db = (HibernateProcessPersistenceContextManager) environment.get(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER);
						return ((HibernateProcessPersistenceContext)db.getProcessPersistenceContext()).getSession().get(Class.forName(canonicalName), (Serializable) id);
	 				}
				}, new SerializablePlaceholderResolverStrategy(ClassObjectMarshallingStrategyAcceptor.DEFAULT) });
		return kbase.newStatefulKnowledgeSession(config, environment);
	}
}
