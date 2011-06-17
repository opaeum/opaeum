package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.Collection;

import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.SignalToDispatch;

public abstract class AbstractSignal implements Serializable{
	private static final long serialVersionUID = 1217087681192581261l;
	public void send(Audited from,Audited to){
	}
	public void send(AbstractEventSource from,ActiveObject to){
		if(from != null){
			if(from instanceof BaseAuditable){
				//Ensure flushing - hack
				((BaseAuditable) from).defaultUpdate();
			}
			from.getOutgoingEvents().add(new SignalToDispatch(from, to, this));
		}
		// TODO get rid of signal dispatcher?
		Environment.getInstance().getComponent(ISignalDispatcher.class).sendSignal(from, to, this);
	}
	public void send(AbstractEventSource from,Collection<? extends ActiveObject> to){
		for(ActiveObject activeObject:to){
			send(from, activeObject);
		}
	}
	public abstract String getUid();
}
