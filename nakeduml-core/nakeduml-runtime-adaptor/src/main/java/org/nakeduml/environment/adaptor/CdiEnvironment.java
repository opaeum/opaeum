package org.nakeduml.environment.adaptor;

import org.drools.runtime.StatefulKnowledgeSession;
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
		} else if (clazz == StatefulKnowledgeSession.class) {
			return  (T) Component.INSTANCE.getInstance(JbpmKnowledgeSession.class).getKnowledgeSession();			
		} else {
			return Component.INSTANCE.getInstance(clazz);
		}
	}

	@Override
	public void reset() {
		//Yikes, good luck
	}

	@Override
	public <T>Class<T> getImplementationClass(T o){
		return null;
	}
}
