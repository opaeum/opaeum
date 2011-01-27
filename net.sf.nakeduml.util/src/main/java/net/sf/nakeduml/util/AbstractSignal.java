package net.sf.nakeduml.util;

import java.io.Serializable;
import java.util.Collection;

import net.sf.nakeduml.seam.SignalDispatcher;

//TODO Weld
public class AbstractSignal implements Serializable {
	public void send(Object from, Object to){}
	public void send(AbstractEntity from, ActiveObject to){
//		SignalDispatcher.getInstance().sendSignal(from, to, this);
	}
	public void send(AbstractEntity from, Collection<? extends ActiveObject> to){
//		SignalDispatcher.getInstance().sendSignal(from, to, this);
	}
}
