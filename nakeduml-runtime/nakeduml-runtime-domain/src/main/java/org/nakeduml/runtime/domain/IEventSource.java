package org.nakeduml.runtime.domain;

import java.util.Set;

public interface IEventSource extends IPersistentObject{
	Set<Object> getOutgoingEvents();
}
