package org.opaeum.simulation;

import java.util.Collection;
import java.util.HashSet;

public abstract class StructInstanceSimulation <T> extends AbstractBucket{
	Collection<T> instances = new HashSet<T>();
	public T generateInstance() throws Exception{
		T result = createNewObject();
		count++;
		return result;
	}
	protected abstract void populateReferences(Object o) throws Exception;
	public abstract T createNewObject() throws Exception;
	public void populateReferences() throws Exception{
		for(T o:instances){
			populateReferences(o);
		}
	}
}
