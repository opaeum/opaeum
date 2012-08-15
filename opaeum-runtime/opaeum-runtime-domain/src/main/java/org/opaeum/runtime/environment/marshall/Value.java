package org.opaeum.runtime.environment.marshall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;
public abstract class Value implements Serializable{
	// utility method for custom developed marshalling scenarios
	public static Object valueOf(Value value,AbstractPersistence persistence){
		JavaMetaInfoMap map = Environment.getInstance().getMetaInfoMap();
		if(value instanceof EntityValue){
			return persistence.getReference(value.getValueClass(), ((EntityValue) value).getId());
		}else if(value instanceof HelperValue){
			return Environment.getInstance().getComponent(value.getValueClass());
		}else if(value instanceof EnumValue){
			return map.getSecondaryObject(EnumResolver.class, value.getValueClass()).fromOpaeumId(((EnumValue) value).getEnumId());
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
	@SuppressWarnings("rawtypes")
	public static Value valueOf(Object value){
		JavaMetaInfoMap map = Environment.getInstance().getMetaInfoMap();
		if(value instanceof IToken){
			return valueOf((IToken) value);
		}else if(value instanceof IPersistentObject){
			return valueOf((IPersistentObject) value);
		}else if(value instanceof IActiveObject){
			return valueOf((IActiveObject) value);
		}else if(value instanceof Set<?>){
			return valueOfCollection(new HashSet<Value>(), (Set<?>) value);
		}else if(value instanceof List<?>){
			return valueOfCollection(new ArrayList<Value>(), (List<?>) value);
		}else if(value instanceof IEnum){
			return new EnumValue(map.getUuidFor(value.getClass()), map.getSecondaryObject(EnumResolver.class, value.getClass()).toOpaeumId((IEnum) value));
		}else if(value instanceof Serializable){
			return new SerializableValue(map.getUuidFor(value.getClass()), (Serializable) value);
		}else{
			return null;
		}
	}
	public Class<?> getValueClass(){
		return Environment.getInstance().getMetaInfoMap().getClass(getTypeId());
	}
	private static CollectionValue valueOfCollection(Collection<Value> newValue,Collection<?> oldValue){
		for(Object o:oldValue){
			newValue.add(valueOf(o));
		}
		if(oldValue.isEmpty()){
			return new CollectionValue(newValue);
		}else{
			return new CollectionValue(Environment.getInstance().getMetaInfoMap().getUuidFor(oldValue.iterator().next().getClass()), newValue);
		}
	}
	private static EntityValue valueOf(IPersistentObject inputSource){
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((IPersistentObject) inputSource).getClass().getName() + " does not have an id");
		}
		return new EntityValue(Environment.getInstance().getMetaInfoMap().getUuidFor(inputSource.getClass()), inputSource);
	}
	@SuppressWarnings("rawtypes")
	private static TokenValue valueOf(IToken inputSource){
		if(inputSource.getId() == null){
			throw new IllegalStateException("entity " + ((IToken) inputSource).getClass().getName() + " does not have an id");
		}
		return new TokenValue(inputSource);
	}
	private static HelperValue valueOf(IActiveObject inputSource){
		return new HelperValue(Environment.getInstance().getMetaInfoMap().getUuidFor(inputSource.getClass()));
	}
	private static final long serialVersionUID = 531640008870617688L;
	// necessary for custom developed marshalling scenarios
	private String typeId;
	protected Value(String typeId){
		this.typeId = typeId;
	}
	public String getTypeId(){
		return typeId;
	}
}