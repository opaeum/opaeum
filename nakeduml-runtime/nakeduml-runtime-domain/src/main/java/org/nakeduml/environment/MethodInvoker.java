package org.nakeduml.environment;

import java.util.Collection;

import org.nakeduml.environment.marshall.PropertyValue;

public interface MethodInvoker{
	Collection<PropertyValue> marshall();
	void unmarshall(Collection<PropertyValue> a,AbstractPersistence persistence);
	void invoke(Object target);
	String getUuid();
	String getQueueName();
}
