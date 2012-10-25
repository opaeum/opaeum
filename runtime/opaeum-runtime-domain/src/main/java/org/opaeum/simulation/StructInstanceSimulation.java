package org.opaeum.simulation;

import java.util.Collection;
import java.util.HashSet;

public abstract class StructInstanceSimulation extends AbstractBucket{
	Collection<Object> instances = new HashSet<Object>();
	public Object generateInstance(){
		Object result = createNewObject();
		count++;
		return result;
	}
	protected abstract void populateReferences(Object o);
	public abstract Object createNewObject();
	public void populateReferences(){
		for(Object o:instances){
			populateReferences(o);
		}
	}
}
