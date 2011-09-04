package org.nakeduml.runtime.environment.marshall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CollectionValue extends Value{
	private static final long serialVersionUID = 531640008870617688L;
	Collection<Value> values;
	public CollectionValue(String typeId,Collection<Value> c){
		super(typeId);
		this.values = c;
	}
	public CollectionValue(Collection<Value> newValue){
		this("asdf", newValue);
	}
	public Collection<Value> getCollection(){
		return this.getCollection();
	}
	public Collection<Object> instantiate(){
		if(values instanceof Set){
			return new HashSet<Object>();
		}else{
			return new ArrayList<Object>();
		}
	}
}