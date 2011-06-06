package org.nakeduml.environment;

import org.nakeduml.runtime.domain.IPersistentObject;

public interface PersistentNameClassMap{
	public Class<? extends IPersistentObject> getClass(String name);
}
