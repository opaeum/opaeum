package org.nakeduml.environment.adaptor;

import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;

public class CdiEnvironment extends Environment {
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (clazz == ISignalDispatcher.class) {
			SignalDispatcher d = (SignalDispatcher) Component.INSTANCE.getInstance(SignalDispatcher.class);
			if (!d.isRegistered()) {
				d.register();
			}
			return (T) d;
		} else {
			return Component.INSTANCE.getInstance(clazz);
		}
	}
}
