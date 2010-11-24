package net.sf.nakeduml.util;

import java.io.Serializable;

public interface ActiveObject extends Serializable {
	boolean processSignal(AbstractSignal signal);
}
