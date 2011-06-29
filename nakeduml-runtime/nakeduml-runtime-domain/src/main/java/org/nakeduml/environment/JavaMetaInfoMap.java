package org.nakeduml.environment;

import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IEnum;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public abstract class JavaMetaInfoMap{
	Collection<Class<?>> allClasses = new HashSet<Class<?>>();
	Map<String,Class<?>> persistentNameMap = new HashMap<String,Class<?>>();
	Map<Integer,Class<?>> nakedUmlIdMap = new HashMap<Integer,Class<?>>();
	Map<Integer,Class<?>> nakedUmlIdAuditMap = new HashMap<Integer,Class<?>>();
	Map<Integer,PropertyDescriptor> propertyMap = new HashMap<Integer,PropertyDescriptor>();
	Map<Integer,MethodDescriptor> methodMap = new HashMap<Integer,MethodDescriptor>();
	Map<Integer,Enum<?>> enumNakedUmlIdMap = new HashMap<Integer,Enum<?>>();
	Map<String,Enum<?>> enumPersistentNameMap = new HashMap<String,Enum<?>>();
	public Collection<Class<?>> getAllClasses(){
		return allClasses;
	}
	protected void putClass(Class<? extends Object> c){
		if(AbstractSignal.class.isAssignableFrom(c)){
			addSecondaryClass(c, "Mdb");
		}else if(IPersistentObject.class.isAssignableFrom(c)){
			addSecondaryClass(c, "_Audit");
		}
		allClasses.add(c);
		NumlMetaInfo classMetaInfo = c.getAnnotation(NumlMetaInfo.class);
		if(classMetaInfo != null){
			// Need to check as imported library classes may not have this annotation
			persistentNameMap.put(classMetaInfo.persistentName(), c);
			nakedUmlIdMap.put(c.getAnnotation(NumlMetaInfo.class).nakedUmlId(), c);
			for(PropertyDescriptor p:IntrospectionUtil.getProperties(c)){
				Method readMethod = p.getReadMethod();
				if(readMethod != null){
					NumlMetaInfo metaInfo = readMethod.getAnnotation(NumlMetaInfo.class);
					if(metaInfo != null){
						propertyMap.put(metaInfo.nakedUmlId(), p);
					}
				}
			}
			for(MethodDescriptor m:IntrospectionUtil.getMethods(c)){
				NumlMetaInfo metaInfo = m.getMethod().getAnnotation(NumlMetaInfo.class);
				if(metaInfo != null){
					methodMap.put(metaInfo.nakedUmlId(), m);
				}
			}
			if(c.isEnum()){
				for(Enum en:IntrospectionUtil.getEnumLiterals((Class<? extends Enum>) c)){
					NumlMetaInfo metaInfo = IntrospectionUtil.getDeclaredField(c, en.name()).getAnnotation(NumlMetaInfo.class);
					if(metaInfo != null){
						enumNakedUmlIdMap.put(metaInfo.nakedUmlId(), en);
						enumPersistentNameMap.put(metaInfo.persistentName(), en);
					}else if(en instanceof IEnum){
						IEnum ae = (IEnum) en;
						enumNakedUmlIdMap.put(ae.getNakedUmlId(), en);
						enumPersistentNameMap.put(ae.getPersistentName(), en);
					}
				}
			}
		}
	}
	private void addSecondaryClass(Class<? extends Object> c,String string){
		try{
			allClasses.add(Class.forName(c.getName() + string));
		}catch(ClassNotFoundException e){
		}
	}
	public Class<?> getClass(String qualifiedPersistentName){
		return persistentNameMap.get(qualifiedPersistentName);
	}
	public Class<?> getClass(Integer id){
		return nakedUmlIdMap.get(id);
	}
	public PropertyDescriptor getProperty(Integer id){
		return propertyMap.get(id);
	}
	public MethodDescriptor getMethod(Integer id){
		return methodMap.get(id);
	}
	public Enum<?> getEnum(Integer enumId){
		return enumNakedUmlIdMap.get(enumId);
	}
}
