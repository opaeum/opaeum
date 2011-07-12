package org.nakeduml.environment.marshall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.environment.AbstractPersistence;
import org.nakeduml.environment.EnumResolver;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.JavaMetaInfoMap;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IEnum;
import org.nakeduml.runtime.domain.IPersistentObject;

public abstract class Value implements Serializable{
	// utility method for custom developed marshalling scenarios
	public static Object valueOf(Value value,AbstractPersistence persistence){
		JavaMetaInfoMap map = Environment.getMetaInfoMap();
		if(value instanceof EntityValue){
			return persistence.getReference(value.getValueClass(), ((EntityValue) value).getId());
		}else if(value instanceof HelperValue){
			return Environment.getInstance().getComponent(value.getValueClass());
		}else if(value instanceof EnumValue){
			return map.getSecondaryObject(EnumResolver.class, value.getValueClass()).fromNakedUmlId(((EnumValue) value).getEnumId());
		}else if(value instanceof CollectionValue){
			CollectionValue collectionValue = (CollectionValue) value;
			Collection<Object> r = collectionValue.instantiate();
			for(Value value2:collectionValue.getCollection()){
				r.add(valueOf(value2, persistence));
			}
			return collectionValue;
		}else if(value instanceof SerializableValue){
			return ((SerializableValue) value).getValue();
		}else{
			return null;
		}
	}
	// utility method for custom developed marshalling scenarios
	public static Value valueOf(Object value){
		JavaMetaInfoMap map = Environment.getMetaInfoMap();
		if(value instanceof IPersistentObject){
			return valueOf((IPersistentObject) value);
		}else if(value instanceof IActiveObject){
			return valueOf((IActiveObject) value);
		}else if(value instanceof Set<?>){
			return valueOfCollection(new HashSet<Value>(), (Set<?>) value);
		}else if(value instanceof List<?>){
			return valueOfCollection(new ArrayList<Value>(), (List<?>) value);
		}else if(value instanceof IEnum){
			return new EnumValue(map.getNakedUmlId(value.getClass()), map.getSecondaryObject(EnumResolver.class, value.getClass()).toNakedUmlId((IEnum) value));
		}else if(value instanceof Serializable){
			return new SerializableValue(map.getNakedUmlId(value.getClass()), (Serializable) value);
		}else{
			return null;
		}
	}
	public Class<?> getValueClass(){
		return Environment.getMetaInfoMap().getClass(getTypeId());
	}
	private static CollectionValue valueOfCollection(Collection<Value> newValue,Collection<?> oldValue){
		for(Object o:oldValue){
			newValue.add(valueOf(o));
		}
		if(oldValue.isEmpty()){
			return new CollectionValue(newValue);
		}else{
			return new CollectionValue(Environment.getMetaInfoMap().getNakedUmlId(oldValue.iterator().next().getClass()), newValue);
		}
	}
	private static EntityValue valueOf(IPersistentObject inputSource){
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((IPersistentObject) inputSource).getClass().getName() + " does not have an id");
		}
		return new EntityValue(Environment.getMetaInfoMap().getNakedUmlId(inputSource.getClass()), inputSource);
	}
	private static HelperValue valueOf(IActiveObject inputSource){
		return new HelperValue(Environment.getMetaInfoMap().getNakedUmlId(inputSource.getClass()));
	}
	private static final long serialVersionUID = 531640008870617688L;
	// necessary for custom developed marshalling scenarios
	private Integer typeId;
	protected Value(Integer typeId){
		this.typeId = typeId;
	}
	public Integer getTypeId(){
		return typeId;
	}
}