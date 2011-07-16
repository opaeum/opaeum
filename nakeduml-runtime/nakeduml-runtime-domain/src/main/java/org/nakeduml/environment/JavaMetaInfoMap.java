package org.nakeduml.environment;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.Audited;
import org.nakeduml.runtime.domain.IEnum;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.IntrospectionUtil;

import com.sun.jmx.snmp.EnumRowStatus;

//TODO extractor adaptor logic into a differnt class
public abstract class JavaMetaInfoMap{
	Collection<Class<?>> allClasses = new HashSet<Class<?>>();
	Map<Integer,Class<? extends MethodInvoker>> methodInvokersByNakedUmlId = new HashMap<Integer,Class<? extends MethodInvoker>>();
	Map<Integer,Class<?>> nakedUmlIdClassMap = new HashMap<Integer,Class<?>>();
	Map<String,Class<?>> uuidClassMap = new HashMap<String,Class<?>>();
	Map<Class<?>,Integer> classNakedUmlIdMap = new HashMap<Class<?>,Integer>();
	Map<Class<?>,Map<Class<?>,Object>> secondaryClassMap = new HashMap<Class<?>,Map<Class<?>,Object>>();
	Map<String,Class<? extends MethodInvoker>> methodInvokersByUuid = new HashMap<String,Class<? extends MethodInvoker>>();
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
	// Called from ui
	public MethodInvoker getMethodInvoker(String uuid){
		return IntrospectionUtil.newInstance(methodInvokersByUuid.get(uuid));
	}
	protected void putMethod(Class<? extends Object> c,String uuid,int nakedUmlId){
		Method[] declaredMethods = c.getDeclaredMethods();
		for(Method method:declaredMethods){
			if(method.isAnnotationPresent(NumlMetaInfo.class)){
				if(uuid.equals(method.getAnnotation(NumlMetaInfo.class).uuid())){
					Class<? extends MethodInvoker> mi = IntrospectionUtil.classForName(c.getName().toLowerCase() + "." + c.getSimpleName()
							+ NameConverter.capitalize(method.getName()) + nakedUmlId + "Invoker");
					this.methodInvokersByNakedUmlId.put(nakedUmlId, mi);
					this.methodInvokersByUuid.put(uuid, mi);
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
	protected void putClass(Class<? extends Object> c,String uid,int nakedUmlId){
		if(AbstractSignal.class.isAssignableFrom(c)){
			addSecondaryClass(SignalMarshaller.class, c, "Marshaller", true);
			try{
				// Try to ensure it is in allClasses
				addSecondaryClass(Class.forName("org.nakeduml.environment.adaptor.AbstractSignalMdb"), c, "Listener", true);
			}catch(Exception e){
			}
		}else if(IPersistentObject.class.isAssignableFrom(c)){
			addSecondaryClass(Audited.class, c, "_Audit", false);
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
}
