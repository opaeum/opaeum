package org.nakeduml.runtime.domain;

import java.io.Serializable;

public interface ActiveObject extends Serializable {
	boolean processSignal(AbstractSignal signal);
}
