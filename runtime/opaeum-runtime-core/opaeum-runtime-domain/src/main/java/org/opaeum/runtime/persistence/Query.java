package org.opaeum.runtime.persistence;

import java.util.List;

import org.opaeum.runtime.domain.IPersistentObject;

public interface Query{
	int executeUpdate();
	<T extends IPersistentObject> List<T> executeQuery();
	Query setParameter(String name, Object value);
}
