package org.nakeduml.runtime.domain;

import java.util.Set;


public interface IEventGenerator{
	Set<OutgoingEvent> getOutgoingEvents();
	Set<CancelledEvent> getCancelledEvents();
}
