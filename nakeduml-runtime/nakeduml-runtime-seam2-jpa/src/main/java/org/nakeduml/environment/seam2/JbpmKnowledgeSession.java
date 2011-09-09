package org.nakeduml.environment.seam2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.EntityManager;
import javax.transaction.Synchronization;

import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.transaction.UserTransaction;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeSession;
import org.nakeduml.runtime.jpa.LightWeightJpaProcessPersistenceContextManager;

@Name("jbpmKnowledgeSession")
@Scope(ScopeType.EVENT)
public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession implements Synchronization {
	@In
	UserTransaction transaction;
	@In
	private EntityManager entityManager;
	private boolean registered;

	@Override
	public void afterCompletion(int arg0) {
		if (Contexts.isEventContextActive()) {
			Contexts.getEventContext().remove("jbpmKnowledgeSession");
			Contexts.getEventContext().remove("knowledgeSession");
		}
		registered = false;
	}

	@Override
	@Factory(scope=ScopeType.EVENT, value="knowledgeSession")
	public StatefulKnowledgeSession getKnowledgeSession() {
		if (!registered) {
			transaction.registerSynchronization(this);
			registered = true;
		}
		return super.getKnowledgeSession();
	}

	@Override
	public void beforeCompletion() {
	}

	@Override
	protected ObjectMarshallingStrategy getPlaceholderResolverStrategy(final Environment environment) {
		return new JPAPlaceholderResolverStrategy(environment) {
			@Override
			public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
				String canonicalName = is.readUTF();
				Object id = is.readObject();
				return entityManager.find(Class.forName(canonicalName), id);
			}

			@Override
			public boolean accept(Object object) {
				return object instanceof IPersistentObject;
			}

			@Override
			public void write(ObjectOutputStream os, Object object) throws IOException {
				os.writeUTF(IntrospectionUtil.getOriginalClass(object).getName());
				os.writeObject(((IPersistentObject) object).getId());
			}
		};
	}

	@Override
	protected ProcessPersistenceContextManager getPersistenceContextManager(Environment env) {
		env.set(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER, entityManager);
		return new LightWeightJpaProcessPersistenceContextManager(entityManager);
	}
}
