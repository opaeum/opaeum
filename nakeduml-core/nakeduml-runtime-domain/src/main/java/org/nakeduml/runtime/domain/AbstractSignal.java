package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.Collection;

import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;

public abstract class AbstractSignal implements Serializable {
	public void send(Object from, Object to){}
	public void send(AbstractEntity from, ActiveObject to){
		Environment.getInstance().getComponent(ISignalDispatcher.class).sendSignal(from, to, this);
	}
	public void send(AbstractEntity from, Collection<? extends ActiveObject> to){
		Environment.getInstance().getComponent(ISignalDispatcher.class).sendSignal(from, to, this);
	}
	public abstract String getUid();
}
