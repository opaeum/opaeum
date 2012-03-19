package org.opaeum.runtime.environment;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class JavaTypedElementContainer{
	private String uuid;
	private Map<String,JavaTypedElement> typedElements;
	public JavaTypedElementContainer(Class<?> c){
		try{
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(c, Object.class).getPropertyDescriptors();
			for(PropertyDescriptor pd:propertyDescriptors){
				JavaTypedElement jte = new JavaTypedElement(pd);
				typedElements.put(jte.getName(), jte);
			}
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public JavaTypedElementContainer(Method c){
		try{
			Annotation[][] parameterAnnotations = c.getParameterAnnotations();
			for(int i = 0;i < parameterAnnotations.length;i++){
				JavaTypedElement jte = new JavaTypedElement(parameterAnnotations[i]);
				typedElements.put(jte.getName(), jte);
				
			}
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public JavaTypedElementContainer(Class<?> c, Method m){
		try{
			Annotation[][] parameterAnnotations = m.getParameterAnnotations();
			for(int i = 0;i < parameterAnnotations.length;i++){
				JavaTypedElement jte = new JavaTypedElement(parameterAnnotations[i]);
				typedElements.put(jte.getName(), jte);
				
			}
			//TODO do output parameters?
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
