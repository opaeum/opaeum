package org.opaeum.runtime.persistence;

import java.util.List;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IPersistentStringEnum;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.organization.IParticipantBase;
import org.opaeum.runtime.organization.IPersonNode;

public interface AbstractPersistence{
	<T>T getReference(Class<T> t,Long id);
	void persist(Object object);
	void remove(IPersistentObject event);
	<T>T find(Class<T> t,Long id);
	<T extends IPersistentStringEnum>T getReference(Class<T> t,String id);
	<T extends IPersistentStringEnum>T find(Class<T> t,String id);
	boolean contains(IPersistentObject p);
	Query createQuery(String q);
	<T> List<T> readAll(Class<T> c);
	/**
	 * Primarily for tests
	 * @param ctx
	 */
	void refresh(IPersistentObject ... ctx);
	JavaMetaInfoMap getMetaInfoMap();
	String getApplicationId();
	void setFilterDeletedObjects(boolean v);
	boolean isFilterDeletedObjects();
	boolean isOpen();
	IPersonNode getCurrentUser();
	IParticipantBase getCurrentRole();
}
