package org.opaeum.runtime.environment;

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
	public String getUuid(){
		return uuid;
	}
	public Map<String,JavaTypedElement> getTypedElements(){
		return typedElements;
	}
}
