package org.nakeduml.runtime.event;

import java.util.Collection;
import java.util.Date;

import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public interface IEventHandler{
	String getEventUuid();
	void unmarshall(Collection<PropertyValue> propertyValues,AbstractPersistence session);
	Collection<PropertyValue> marshall();
	boolean handleOn(Object target);
	String getQueueName();
	int getConsumerPoolSize();
	Date scheduleNextOccurrence();
	Date getFirstOccurrenceScheduledFor();
}
