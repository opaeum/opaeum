package org.nakeduml.runtime.domain;

import java.util.Set;


public interface IEventGenerator extends IPersistentObject{
	Set<OutgoingEvent> getOutgoingEvents();
	Set<CancelledEvent> getCancelledEvents();
}
