package org.nakeduml.environment;

import java.util.Collection;

import org.nakeduml.environment.marshall.PropertyValue;
import org.nakeduml.runtime.domain.AbstractSignal;

public interface SignalMarshaller{
	Collection<PropertyValue> marshall(AbstractSignal s);
	AbstractSignal unmarshall(Collection<PropertyValue> vs);
}
