package org.nakeduml.runtime.jpa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.EntityManager;

import org.drools.marshalling.ObjectMarshallingStrategy;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.drools.runtime.Environment;
import org.jbpm.persistence.ProcessPersistenceContextManager;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeSession;

public class StandaloneJpaJbpmKnowledgeSession extends AbstractJbpmKnowledgeSession {
	private EntityManager entityManager;
	public StandaloneJpaJbpmKnowledgeSession(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	protected ObjectMarshallingStrategy getPlaceholderResolverStrategy(final Environment environment) {
		return new JPAPlaceholderResolverStrategy(environment) {
			@Override
			public void write(ObjectOutputStream os, Object object) throws IOException {
				os.writeUTF(IntrospectionUtil.getOriginalClass(object.getClass()).getName());
				os.writeObject(((IPersistentObject)object).getId());
			}

			@Override
			public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
				String canonicalName = is.readUTF();
				Object id = is.readObject();
				return entityManager.find(Class.forName(canonicalName), id);
			}
		};
	}

	@Override
	protected ProcessPersistenceContextManager getPersistenceContextManager(Environment env) {
		return new LightWeightJpaProcessPersistenceContextManager(entityManager);
	}
}
