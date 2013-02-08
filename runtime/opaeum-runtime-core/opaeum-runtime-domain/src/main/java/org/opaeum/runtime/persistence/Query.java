package org.opaeum.runtime.persistence;

import java.util.Collection;
import java.util.List;

import org.opaeum.runtime.domain.IPersistentObject;

public interface Query{
	int executeUpdate();
	List<IPersistentObject> executeQuery();
	void setParameter(String name, Object value);
}
