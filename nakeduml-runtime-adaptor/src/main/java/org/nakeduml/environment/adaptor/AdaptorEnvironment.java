package org.nakeduml.environment.adaptor;

import org.nakeduml.environment.Environment;

public class AdaptorEnvironment extends Environment {
	@Override
	public <T> T getComponent(Class<T> clazz) {
		return Component.INSTANCE.getInstance(clazz);
	}
}
