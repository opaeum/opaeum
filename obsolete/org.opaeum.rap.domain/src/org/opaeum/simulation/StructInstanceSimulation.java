package org.opaeum.simulation;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;

public abstract class StructInstanceSimulation extends AbstractBucket{
	Collection<Object> instances = new HashSet<Object>();
	public Object generateInstance(){
		Object result = null;
		try{
			result = createNewObject();
		count++;
		}catch(ParseException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	protected abstract void populateReferences(Object o) throws ParseException;
	public abstract Object createNewObject() throws ParseException;
	public void populateReferences(){
		for(Object o:instances){
			try{
				populateReferences(o);
			}catch(ParseException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
