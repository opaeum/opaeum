package org.opaeum.simulation;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;

public abstract class StructInstanceSimulation extends AbstractBucket{
	Collection<Object> instances = new HashSet<Object>();
	public Object generateInstance() throws Exception{
		Object result = createNewObject();
		count++;
		return result;
	}
	protected abstract void populateReferences(Object o) throws Exception;
	public abstract Object createNewObject() throws Exception;
	public void populateReferences() throws Exception{
		for(Object o:instances){
			populateReferences(o);
		}
	}
}
