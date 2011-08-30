package org.nakeduml.runtime.domain;

import java.util.Map;

import org.nakeduml.runtime.event.IEventHandler;

public interface IEventGenerator extends IPersistentObject{
	Map<Object,IEventHandler> getOutgoingEvents();
	Map<Object,String> getCancelledEvents();
}
