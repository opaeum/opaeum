package net.sf.nakeduml.util;

import java.util.Collection;

public class AbstractSignal {
	public void send(Object from, Object to){}
	public void send(AbstractEntity from, AbstractEntity to){}
	public void send(AbstractEntity from, Collection<? extends AbstractEntity> to){}
}
