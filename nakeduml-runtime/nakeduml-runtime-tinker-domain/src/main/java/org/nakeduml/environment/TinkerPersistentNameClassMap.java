package org.nakeduml.environment;

import org.util.BaseTinker;

public interface TinkerPersistentNameClassMap{
	public Class<? extends BaseTinker> getClass(String name);
}
