package org.nakeduml.environment.adaptor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nakeduml.environment.AbstractJbpmKnowledgeBase;
import org.nakeduml.environment.Environment;
import org.nakeduml.jbpm.adaptor.HibernateEnvironmentBuilder;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public abstract class AbstractJbpmKnowledgeSession{
	private static AbstractJbpmKnowledgeBase knowledgeBase;
	protected StatefulKnowledgeSession knowledgeSession;
	@Inject
	protected SessionFactory sessionFactory;
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
		final Session session = sessionFactory.getCurrentSession();
		environment.set(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER, new HibernateEnvironmentBuilder(session).getPersistenceContextManager());
		environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[]{
				new JPAPlaceholderResolverStrategy(environment){
					@Override
					public Object read(ObjectInputStream is) throws IOException,ClassNotFoundException{
						String canonicalName = is.readUTF();
						Object id = is.readObject();
						Class<?> clazz = Class.forName(canonicalName);
						Object obj = session.get(clazz, (Serializable) id);
						return obj;
					}
					public void write(ObjectOutputStream os,Object object) throws IOException{
						os.writeUTF(IntrospectionUtil.getOriginalClass(object).getCanonicalName());
						os.writeObject(getClassIdValue(object));
					}
				},new SerializablePlaceholderResolverStrategy(ClassObjectMarshallingStrategyAcceptor.DEFAULT)});
		return kbase.newStatefulKnowledgeSession(config, environment);
	}

}