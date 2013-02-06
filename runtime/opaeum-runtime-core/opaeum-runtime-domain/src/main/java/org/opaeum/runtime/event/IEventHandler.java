package org.opaeum.runtime.event;

import java.util.Collection;
import java.util.Date;

import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.persistence.AbstractPersistence;

public interface IEventHandler{
	String getHandlerUuid();
	void unmarshall(Collection<PropertyValue> propertyValues,AbstractPersistence session);
	Collection<PropertyValue> marshall(Environment env);
	boolean handleOn(Object target);
	String getQueueName();
	int getConsumerPoolSize();
	Date scheduleNextOccurrence();
	Date getFirstOccurrenceScheduledFor();
}
