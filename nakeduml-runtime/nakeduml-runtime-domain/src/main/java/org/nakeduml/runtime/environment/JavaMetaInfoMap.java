package org.nakeduml.runtime.environment;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.event.IEventHandler;

//TODO extractor adaptor logic into a differnt class
public abstract class JavaMetaInfoMap{
	private Collection<Class<?>> allClasses = new HashSet<Class<?>>();
	private Map<Integer,Class<?>> nakedUmlIdClassMap = new HashMap<Integer,Class<?>>();
	private Map<String,Class<?>> uuidClassMap = new HashMap<String,Class<?>>();
	private Map<Class<?>,Integer> classNakedUmlIdMap = new HashMap<Class<?>,Integer>();
	private Map<Class<?>,Map<Class<?>,Object>> secondaryClassMap = new HashMap<Class<?>,Map<Class<?>,Object>>();
	private Map<String,Class<? extends IEventHandler>> eventHandlersByUuid = new HashMap<String,Class<? extends IEventHandler>>();
	public void mergeWith(JavaMetaInfoMap other){
		allClasses.addAll(other.allClasses);
		nakedUmlIdClassMap.putAll(other.nakedUmlIdClassMap);
		uuidClassMap.putAll(other.uuidClassMap);
		classNakedUmlIdMap.putAll(other.classNakedUmlIdMap);
		secondaryClassMap.putAll(other.secondaryClassMap);
		eventHandlersByUuid.putAll(other.eventHandlersByUuid);
	}
	public Collection<Class<?>> getAllClasses(){
		return allClasses;
	}
	public Class<?> getClass(Integer id){
		return nakedUmlIdClassMap.get(id);
	}
	public Integer getNakedUmlId(Class<? extends Object> c){
		Integer integer = classNakedUmlIdMap.get(c);
		if(integer == null){
			// Try direct interfaces first
			Class<?>[] interfaces = c.getInterfaces();
			for(Class<?> class1:interfaces){
				integer = getNakedUmlId(class1);
				if(integer != null){
					break;
				}
			}
			if(integer == null){
				Class<?> o = c.getSuperclass();
				integer = getNakedUmlId(o);
			}
			if(integer != null){
				classNakedUmlIdMap.put(c, integer);
			}
		}
		return integer;
	}
	protected void putMethod(Class<? extends Object> c,String uuid,int nakedUmlId){
		Method[] declaredMethods = c.getDeclaredMethods();
		for(Method method:declaredMethods){
			if(method.isAnnotationPresent(NumlMetaInfo.class)){
				if(uuid.equals(method.getAnnotation(NumlMetaInfo.class).uuid())){
					Class<? extends IEventHandler> mi = IntrospectionUtil.classForName(c.getName().toLowerCase() + "." + NameConverter.capitalize(method.getName())
							+ "Handler" + nakedUmlId);
					this.eventHandlersByUuid.put(uuid, mi);
					allClasses.add(mi);
					try{
						allClasses.add(IntrospectionUtil.classForName(c.getName().toLowerCase() + "." + c.getSimpleName() + NameConverter.capitalize(method.getName())
								+ nakedUmlId + "InvokerMdb"));
					}catch(Exception e){
					}
					break;
				}
			}
		}
	}
	public void putEventHandler(Class<? extends IEventHandler> handler,String uuid){
		eventHandlersByUuid.put(uuid, handler);
	}
	protected void putClass(Class<? extends Object> c,String uid,int nakedUmlId){
		if(ISignal.class.isAssignableFrom(c)){
			addSecondaryClass(IEventHandler.class, c, "Handler", false);
			try{
				// Try to ensure it is in allClasses
				addSecondaryClass(Class.forName("org.nakeduml.environment.adaptor.AbstractSignalMdb"), c, "Listener", true);
			}catch(Exception e){
			}
		}else if(IPersistentObject.class.isAssignableFrom(c)){
			try{
				// Try to ensure it is in allClasses
				addSecondaryClass(Class.forName("org.nakeduml.adaptor.IDataGenerator"), c, "DataGenerator", true);
			}catch(Exception e){
			}
		}else if(IEnum.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "Resolver", true);
		}
		if(IProcessObject.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "StateResolver", true);
		}
		allClasses.add(c);
		nakedUmlIdClassMap.put(nakedUmlId, c);
		classNakedUmlIdMap.put(c, nakedUmlId);
	}
	public <T>T getSecondaryObject(Class<T> secondaryClass,Class<?> c){
		Map<Class<?>,Object> map = secondaryClassMap.get(secondaryClass);
		if(map == null){
			throw new IllegalArgumentException("Unsupported secondary class: " + secondaryClass.getName());
		}else{
			Object object = map.get(c);
			if(object instanceof Class<?>){
				return IntrospectionUtil.newInstance((Class<T>) object);
			}else{
				return (T) object;
			}
		}
	}
	private void addSecondaryClass(Class<?> secondaryClassSuperclass,Class<? extends Object> c,String string,boolean singleton){
		try{
			Class<?> secondaryClass = Class.forName(c.getName() + string);
			Map<Class<?>,Object> map = secondaryClassMap.get(secondaryClassSuperclass);
			if(map == null){
				map = new HashMap<Class<?>,Object>();
				secondaryClassMap.put(secondaryClassSuperclass, map);
			}
			if(singleton){
				map.put(c, secondaryClass.newInstance());
			}else{
				map.put(c, secondaryClass);
			}
			allClasses.add(secondaryClass);
		}catch(ClassNotFoundException e){
		}catch(InstantiationException e){
		}catch(IllegalAccessException e){
		}
	}
	public IEventHandler getEventHandler(String triggerUuid){
		Class<? extends IEventHandler> c = eventHandlersByUuid.get(triggerUuid);
		if(c == null){
			return null;
		}else{
			return IntrospectionUtil.newInstance(c);
		}
	}
}
