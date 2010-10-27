package net.sf.nakeduml.util;

import java.util.Collection;

import net.sf.nakeduml.seam.SignalDispatcher;

public class AbstractSignal {
	public void send(Object from, Object to){}
	public void send(AbstractEntity from, AbstractEntity to){
		SignalDispatcher.sendSignal(from, to, this);
	}
	public void send(AbstractEntity from, Collection<? extends AbstractEntity> to){
		SignalDispatcher.sendSignal(from, to, this);
	}
}
