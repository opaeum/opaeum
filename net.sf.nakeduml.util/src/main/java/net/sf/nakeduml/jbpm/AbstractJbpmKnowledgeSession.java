package net.sf.nakeduml.jbpm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.ContextNotActiveException;

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
import org.jboss.seam.persistence.transaction.TransactionScoped;

@TransactionScoped
public abstract class AbstractJbpmKnowledgeSession /* implements Synchronization */ {
	private StatefulKnowledgeSession knowledgeSession;

	protected abstract Session getHibernateSession();

	protected abstract AbstractJbpmKnowledgeBase getJbpmKnowledgeBase();

	public StatefulKnowledgeSession getKnowledgeSession() {
		if (this.knowledgeSession == null) {
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}

	protected StatefulKnowledgeSession createKnowledgeSession()  {
		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
		try {
//			try {
//				if (transaction.isActive()) {
//					transaction.registerSynchronization(this);
//				}else{
//					throw new IllegalStateException("Processes must be accessed from a transactional context");
//				}
//			} catch (SystemException e) {
//				throw new RuntimeException(e);
//			}
			Properties properties = new Properties();
			properties.setProperty("drools.commandService", "org.drools.persistence.session.SingleSessionCommandService");
			properties.setProperty("drools.processInstanceManagerFactory",
					"org.drools.persistence.processinstance.JPAProcessInstanceManagerFactory");
			properties.setProperty("drools.workItemManagerFactory", "org.drools.persistence.processinstance.JPAWorkItemManagerFactory");
			properties.put("drools.processInstanceManagerFactory", "org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
			properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
			SessionConfiguration config = new SessionConfiguration(properties);
			final Environment environment = EnvironmentFactory.newEnvironment();
	        environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[]{
                    new JPAPlaceholderResolverStrategy(environment){

						@Override
						public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
					        String canonicalName = is.readUTF();
					        Object id = is.readObject();
					        Session db =(Session) environment.get(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER);
					        return db.get(Class.forName(canonicalName), (Serializable) id);
						}
                    	
                    },
                    new SerializablePlaceholderResolverStrategy( ClassObjectMarshallingStrategyAcceptor.DEFAULT  )
                     });

			environment.set(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER, getHibernateSession());
			return kbase.newStatefulKnowledgeSession(config, environment);
		} catch (ContextNotActiveException e) {
			Properties props = new Properties();
			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
			SessionConfiguration cfg = new SessionConfiguration(props);
			EnvironmentImpl env = new EnvironmentImpl();
			return kbase.newStatefulKnowledgeSession(cfg, env);
		}
	}

//	@Override
//	public void afterCompletion(int arg0) {
//		if (Contexts.isEventContextActive()) {
//			Contexts.getEventContext().remove("jbpmKnowledgeSession");
//		}
//	}
//
//	@Override
//	public void beforeCompletion() {
//	}
}
