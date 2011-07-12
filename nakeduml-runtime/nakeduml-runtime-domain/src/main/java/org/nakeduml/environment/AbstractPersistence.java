package org.nakeduml.environment;

public interface AbstractPersistence{
	<T>T getReference(Class<T> t,Long id);
	<T>T find(Class<T> t,Long id);
	void persist(Object object);
	Query createQuery(String q);
}
