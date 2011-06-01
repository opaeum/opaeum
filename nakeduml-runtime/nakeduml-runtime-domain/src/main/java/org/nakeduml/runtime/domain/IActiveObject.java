package org.nakeduml.runtime.domain;

import java.io.Serializable;

public interface IActiveObject extends Serializable {
	boolean processSignal(AbstractSignal signal);
}
