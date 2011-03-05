package org.nakeduml.environment.adaptor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.inject.Inject;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.impl.EnvironmentFactory;
import org.drools.impl.EnvironmentImpl;
import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.marshalling.impl.ClassObjectMarshallingStrategyAcceptor;
import org.drools.marshalling.impl.SerializablePlaceholderResolverStrategy;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.jboss.seam.solder.beanManager.BeanManagerUnavailableException;
import org.nakeduml.jbpm.adaptor.HibernateEnvironmentBuilder;

public abstract class AbstractJbpmKnowledgeSession {
	private StatefulKnowledgeSession knowledgeSession;
	@Inject
	protected Session session;

	protected abstract AbstractJbpmKnowledgeBase getJbpmKnowledgeBase();

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
		final Environment environment = EnvironmentFactory.newEnvironment();
		environment.set(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER,
				new HibernateEnvironmentBuilder(session).getPersistenceContextManager());
		environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[] {
				new JPAPlaceholderResolverStrategy(environment) {
					@Override
					public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
						String canonicalName = is.readUTF();
						Object id = is.readObject();
						Session db = (Session) environment.get(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER);
						return db.get(Class.forName(canonicalName), (Serializable) id);
					}
				}, new SerializablePlaceholderResolverStrategy(ClassObjectMarshallingStrategyAcceptor.DEFAULT) });
		return kbase.newStatefulKnowledgeSession(config, environment);
	}
}
