package org.nakeduml.environment;

import org.nakeduml.runtime.domain.AbstractEntity;

public interface PersistentNameClassMap{
	public Class<? extends AbstractEntity> getClass(String name);
}
