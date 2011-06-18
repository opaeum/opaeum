package org.nakeduml.environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractEnum;
import org.nakeduml.runtime.domain.ActiveObject;

public abstract class Value implements Serializable{
	private static final long serialVersionUID = 531640008870617688L;
	public static Value valueOf(Object value){
		if(value instanceof AbstractEntity){
			return valueOf((AbstractEntity) value);
		}else if(value instanceof ActiveObject){
			return valueOf((ActiveObject) value);
		}else if(value instanceof Set<?>){
			return valueOfCollection(new HashSet<Value>(), (Set<?>) value);
		}else if(value instanceof List<?>){
			return valueOfCollection(new ArrayList<Value>(), (List<?>) value);
		}else if(value instanceof AbstractEnum){
			return new EnumValue((AbstractEnum) value);
		}else if(value instanceof Serializable){
			return new SerializableValue((Serializable) value);
		}else{
			return null;
		}
	}
	public abstract Class<?> getValueClass();
	private static CollectionValue valueOfCollection(Collection<Value> newValue,Collection<?> oldValue){
		for(Object o:oldValue){
			newValue.add(valueOf(o));
		}
		return new CollectionValue(newValue);
	}
	private static EntityValue valueOf(AbstractEntity inputSource){
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((AbstractEntity) inputSource).getClass().getName() + " does not have an id");
		}
		return new EntityValue(inputSource);
	}
	private static HelperValue valueOf(ActiveObject inputSource){
		return new HelperValue(inputSource);
	}
	public abstract Object getValue(Session session);
}