package org.opeum.runtime.persistence;

import java.util.Collection;

import org.opeum.runtime.domain.IPersistentObject;

public interface Query{
	int executeUpdate();
	Collection<IPersistentObject> executeQuery();
	void setParameter(String name, Object value);
}
