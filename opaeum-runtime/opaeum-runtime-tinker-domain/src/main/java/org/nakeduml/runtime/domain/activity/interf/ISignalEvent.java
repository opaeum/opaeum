package org.nakeduml.runtime.domain.activity.interf;

import org.opaeum.runtime.domain.ISignal;

public interface ISignalEvent extends IEvent {

	ISignal getSignal();
	
}
