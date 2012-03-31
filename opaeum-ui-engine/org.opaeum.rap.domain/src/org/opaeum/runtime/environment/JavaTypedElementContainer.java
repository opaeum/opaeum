package org.opaeum.runtime.environment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JavaTypedElementContainer{
	private String uuid;
	private Map<String,JavaTypedElement> typedElements = new HashMap<String,JavaTypedElement>();
	public JavaTypedElementContainer(Class<?> c){
		try{
			Method[] methods = c.getMethods();
			for(Method pd:methods){
				if(pd.getName().startsWith("get") && pd.getParameterTypes().length==0 && pd.getReturnType()!=Void.class){
					JavaTypedElement jte = new JavaTypedElement(pd);
					typedElements.put(jte.getUuid(), jte);
				}
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
				JavaTypedElement jte = new JavaTypedElement(c.getGenericParameterTypes()[i],c.getParameterTypes()[i], parameterAnnotations[i]);
				typedElements.put(jte.getUuid(), jte);
			}
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public String getUuid(){
		return uuid;
	}
	public Map<? extends String,? extends JavaTypedElement> getTypedElements(){
		return typedElements;
	}
}
