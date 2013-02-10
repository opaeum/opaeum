package org.opaeum.runtime.environment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessObjectBase;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.event.IEventHandler;

public abstract class JavaMetaInfoMap{
	private Collection<Class<?>> allClasses = new HashSet<Class<?>>();
	private Map<String,Class<?>> uuidClassMap = new HashMap<String,Class<?>>();
	private Map<Class<?>,String> classUuidMap = new HashMap<Class<?>,String>();
	private Map<Class<?>,Map<Class<?>,Object>> secondaryClassMap = new HashMap<Class<?>,Map<Class<?>,Object>>();
	private Map<String,Class<? extends IEventHandler>> eventHandlersByUuid = new HashMap<String,Class<? extends IEventHandler>>();
	private Map<String,JavaTypedElementContainer> typedElementContainers = new HashMap<String,JavaTypedElementContainer>();
	private Map<String,JavaTypedElement> typedElements = new HashMap<String,JavaTypedElement>();
	public void importMetaInfo(JavaMetaInfoMap other){
		classUuidMap.putAll(other.classUuidMap);
		allClasses.addAll(other.allClasses);
		uuidClassMap.putAll(other.uuidClassMap);
		secondaryClassMap.putAll(other.secondaryClassMap);
		eventHandlersByUuid.putAll(other.eventHandlersByUuid);
		typedElementContainers.putAll(other.typedElementContainers);
		typedElements.putAll(other.typedElements);
	}
	public Collection<Class<?>> getAllClasses(){
		return allClasses;
	}
	public Collection<Class<?>> getClassesByAnnotation(Class<? extends Annotation> a){
		Collection<Class<?>> result = new HashSet<Class<?>>();
		for(Class<?> c:getAllClasses()){
			if(c.isAnnotationPresent(a)){
				result.add(c);
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public <T>T newInstance(String uuid){
		return (T) IntrospectionUtil.newInstance(getClass(uuid));
	}
	public JavaTypedElement getTypedElement(String uuid){
		return typedElements.get(uuid);
	}
	@SuppressWarnings("unchecked")
	protected void putMethod(Class<? extends Object> c,String uuid,long nakedUmlId){
		// Will only be called from the declaring model, so this nakedUmlId
		// won't be unique, but does serve to identifiy the handler
		Method[] declaredMethods = c.getDeclaredMethods();
		for(Method method:declaredMethods){
			if(method.isAnnotationPresent(NumlMetaInfo.class)){
				if(uuid.equals(method.getAnnotation(NumlMetaInfo.class).uuid())){
					try{
						String handlerName = c.getName().toLowerCase() + "." + NameConverter.capitalize(method.getName()) + "Handler" + nakedUmlId;
						Class<? extends IEventHandler> mi = (Class<? extends IEventHandler>) c.getClassLoader().loadClass(handlerName);
						this.eventHandlersByUuid.put(uuid, mi);
						JavaTypedElementContainer con = new JavaTypedElementContainer(mi);
						this.typedElementContainers.put(uuid, con);
						putTypedElements(con);
						allClasses.add(mi);
					}catch(ClassNotFoundException e){
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
	@SuppressWarnings("unchecked")
	protected void putClass(Class<? extends Object> c,String uuid){
		JavaTypedElementContainer jtec = new JavaTypedElementContainer(c);
		typedElementContainers.put(uuid, jtec);
		putTypedElements(jtec);
		if(ISignal.class.isAssignableFrom(c)){
			Class<? extends IEventHandler> handler = null;
			try{
				handler = (Class<? extends IEventHandler>) c.getClassLoader().loadClass(c.getName() + "Handler");
			}catch(ClassNotFoundException e){
				throw new RuntimeException(e);
			}
			putEventHandler(handler, uuid);
		}else if(IPersistentObject.class.isAssignableFrom(c)){
		}else if(IEnum.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "Resolver", true);
		}
		if(IProcessObjectBase.class.isAssignableFrom(c)){
			addSecondaryClass(EnumResolver.class, c, "StateResolver", true);
			addSecondaryClass(IToken.class, c, "Token", false);
		}
		allClasses.add(c);
		classUuidMap.put(c, uuid);
		uuidClassMap.put(uuid, c);
		uuidClassMap.put(c.getName(), c);
	}
	private void putTypedElements(JavaTypedElementContainer jtec){
		for(Entry<String,JavaTypedElement> entry:jtec.getTypedElements().entrySet()){
			JavaTypedElement javaTypedElement = typedElements.get(entry.getKey());
			if(javaTypedElement == null || !javaTypedElement.getDeclaringClass().isInterface()){
				typedElements.put(entry.getKey(), entry.getValue());
			}
		}
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
	public void addSecondaryClass(Class<?> secondaryClassSuperclass,Class<? extends Object> c,String string,boolean singleton){
		try{
			Class<?> secondaryClass = c.getClassLoader().loadClass(c.getName() + string);
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
	public JavaTypedElementContainer getTypedElementContainer(String id){
		return typedElementContainers.get(id);
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public Class<? extends IToken> getTokenClass(String classIdentifier){
		Map<Class<?>,Object> map = secondaryClassMap.get(IToken.class);
		if(map == null){
			throw new IllegalArgumentException("Unsupported secondary class: " + IToken.class.getName());
		}else{
			return (Class<? extends IToken>) map.get(classIdentifier);
		}
	}
}
