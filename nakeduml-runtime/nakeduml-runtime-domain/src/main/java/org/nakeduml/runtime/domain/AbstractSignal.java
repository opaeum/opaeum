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
	public void send(IEventSource from,IActiveObject to){
		if(from != null){
			if(from instanceof BaseAuditable){
				// Ensure flushing - hack
				((BaseAuditable) from).defaultUpdate();
			}
			from.getOutgoingEvents().add(new SignalToDispatch(from, to, this));
		}else{
			// TODO get rid of signal dispatcher?
			Environment.getInstance().getComponent(ISignalDispatcher.class).sendSignal(null, to, this);
		}
	}
	public void send(IEventSource from,Collection<? extends IActiveObject> to){
		for(IActiveObject activeObject:to){
			send(from, activeObject);
		}
	}
	public abstract String getUid();
}
