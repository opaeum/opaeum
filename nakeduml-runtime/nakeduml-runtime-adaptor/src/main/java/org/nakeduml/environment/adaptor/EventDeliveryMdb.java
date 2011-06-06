package org.nakeduml.environment.adaptor;

import javax.inject.Inject;

import org.hibernate.Session;
import org.nakeduml.event.AbstractNakedUmlEvent;
import org.nakeduml.runtime.domain.IPersistentObject;

public class EventDeliveryMdb extends AbstractSignalMdb<AbstractNakedUmlEvent>{
	@Inject
	Session hibernateSession;
	@Override
	protected void deliverMessage(AbstractNakedUmlEvent std) throws Exception{
		hibernateSession.clear();
		transaction.begin();
		IPersistentObject eventSource = (IPersistentObject) hibernateSession.load(std.getEventSourceClass(), std.getEventSourceId());
		std.invokeCallback(eventSource);
		std=(AbstractNakedUmlEvent) hibernateSession.merge(std);
		hibernateSession.delete(std);
		hibernateSession.flush();
		transaction.commit();
	}

	@Override
	protected String getQueueName(){
		return "queue/EventDeliveryQueue";
	}
}
