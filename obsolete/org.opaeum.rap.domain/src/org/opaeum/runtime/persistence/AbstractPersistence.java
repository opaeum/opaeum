package org.opaeum.runtime.persistence;

import java.io.Serializable;
import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.IDateTimeEntry;

public interface AbstractPersistence{
	<T>T getReference(Class<T> t,Long id);
	<T>T find(Class<T> t,Serializable id);
	void persist(Object object);
	
	Query createQuery(String q);
	<T> Collection<T> readAll(Class<T> c);
	void remove(IPersistentObject event);
	/**
	 * Primarily for tests
	 * @param ctx
	 */
	void refresh(IPersistentObject ... ctx);

}
