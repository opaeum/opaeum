package org.opaeum.runtime.persistence;

import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;

public interface Query{
	int executeUpdate();
	Collection<IPersistentObject> executeQuery();
	void setParameter(String name, Object value);
}
