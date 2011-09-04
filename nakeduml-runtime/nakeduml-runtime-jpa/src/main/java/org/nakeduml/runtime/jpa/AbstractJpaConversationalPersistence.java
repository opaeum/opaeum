package org.nakeduml.runtime.jpa;

import org.nakeduml.runtime.persistence.ConversationalPersistence;

public abstract class AbstractJpaConversationalPersistence extends AbstractJpaPersistence implements ConversationalPersistence{

	public AbstractJpaConversationalPersistence() {
		super();
	}

	@Override
	public void close() {
		getEntityManager().close();
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

}