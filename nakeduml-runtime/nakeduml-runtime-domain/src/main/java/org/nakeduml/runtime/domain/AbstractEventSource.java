package org.nakeduml.runtime.domain;

import java.util.Set;

public interface AbstractEventSource extends AbstractEntity{
	Set<Object> getOutgoingEvents();
}
