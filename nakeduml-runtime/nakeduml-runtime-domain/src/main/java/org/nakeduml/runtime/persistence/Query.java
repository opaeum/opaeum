package org.nakeduml.runtime.persistence;

import java.util.Collection;

import org.nakeduml.runtime.domain.IPersistentObject;

public interface Query{
	int executeUpdate();
	Collection<IPersistentObject> executeQuery();
	void setParameter(String name, Object value);
}
