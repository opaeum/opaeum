package org.nakeduml.runtime.environment;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IEnum;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.event.IEventHandler;

public abstract class JavaMetaInfoMap{
	private Collection<Class<?>> allClasses = new HashSet<Class<?>>();
	private Map<String,Class<?>> uuidClassMap = new HashMap<String,Class<?>>();
	private Map<Class<?>,String> classUuidMap = new HashMap<Class<?>,String>();
	private Map<Class<?>,Map<Class<?>,Object>> secondaryClassMap = new HashMap<Class<?>,Map<Class<?>,Object>>();
	private Map<String,Class<? extends IEventHandler>> eventHandlersByUuid = new HashMap<String,Class<? extends IEventHandler>>();
	public void importMetaInfo(JavaMetaInfoMap other){
		classUuidMap.putAll(other.classUuidMap);
		allClasses.addAll(other.allClasses);
		uuidClassMap.putAll(other.uuidClassMap);
		secondaryClassMap.putAll(other.secondaryClassMap);
		eventHandlersByUuid.putAll(other.eventHandlersByUuid);
	}
	public Collection<Class<?>> getAllClasses(){
		return allClasses;
	}
	@SuppressWarnings("unchecked")
	public <T> T newInstance(String uuid){
		return (T)IntrospectionUtil.newInstance(getClass(uuid));
	}
	protected void putMethod(Class<? extends Object> c,String uuid,int nakedUmlId){
		// Will only be called from the declaring model, so this nakedUmlId
		// won't be unique, but does serve to identifiy the handler
		Method[] declaredMethods = c.getDeclaredMethods();
		for(Method method:declaredMethods){
			if(method.isAnnotationPresent(NumlMetaInfo.class)){
				if(uuid.equals(method.getAnnotation(NumlMetaInfo.class).uuid())){
					try{
						String handlerName = c.getName().toLowerCase() + "." + NameConverter.capitalize(method.getName()) + "Handler" + nakedUmlId;
						Class<? extends IEventHandler> mi = IntrospectionUtil.classForName(handlerName);
						this.eventHandlersByUuid.put(uuid, mi);
						allClasses.add(mi);
					}catch(RuntimeException e){
					}
					break;
				}
			}
		}
	}
	public static void main(String[] args){
		System.out.println("asdfasdfasdfasdfasdfa".hashCode());
	}
	public void putEventHandler(Class<? extends IEventHandler> handler,String uuid){
		eventHandlersByUuid.put(uuid, handler);
	}
	protected void putClass(Class<? extends Object> c,String uuid){
		if(ISignal.class.isAssignableFrom(c)){
			Class<? extends IEventHandler> handler = IntrospectionUtil.classForName(c.getName() + "Handler");
			putEventHandler(handler, uuid);
		}else if(IPersistentObject.class.isAssignableFrom(c)){
			// TODO datagenerator
		}else if(IEnum.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "Resolver", true);
		}
		if(IProcessObject.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "StateResolver", true);
		}
		allClasses.add(c);
		classUuidMap.put(c, uuid);
		uuidClassMap.put(uuid, c);
	}
	@SuppressWarnings("unchecked")
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
	public Class<?> getClass(String eventTargetClassId){
		return uuidClassMap.get(eventTargetClassId);
	}
	public String getUuidFor(Class<? extends Object> c){
		NumlMetaInfo annotation = c.getAnnotation(NumlMetaInfo.class);
		if(annotation != null){
			return annotation.uuid();
		}else{
			String uuid = classUuidMap.get(c);
			if(uuid == null){
				// Try direct interfaces first
				Class<?>[] interfaces = c.getInterfaces();
				for(Class<?> class1:interfaces){
					uuid = getUuidFor(class1);
					if(uuid != null){
						break;
					}
				}
				if(uuid == null && c.getSuperclass() != null && c.getSuperclass() != Object.class){
					Class<?> o = c.getSuperclass();
					uuid = getUuidFor(o);
				}
				if(uuid != null){
					classUuidMap.put(c, uuid);
				}
			}
			return uuid;
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
