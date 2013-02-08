package org.opaeum.runtime.persistence;

import java.util.Collection;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IPersistentStringEnum;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.event.ChangedEntity;

public interface AbstractPersistence{
	<T>T getReference(Class<T> t,Long id);
	<T>T find(Class<T> t,Long id);
	<T extends IPersistentStringEnum>T getReference(Class<T> t,String id);
	<T extends IPersistentStringEnum>T find(Class<T> t,String id);
	void persist(Object object);
	
	Query createQuery(String q);
	<T> Collection<T> readAll(Class<T> c);
	void remove(IPersistentObject event);
	/**
	 * Primarily for tests
	 * @param ctx
	 */
	void refresh(IPersistentObject ... ctx);
	JavaMetaInfoMap getMetaInfoMap();
	String getApplicationId();

}
