package org.opaeum.runtime.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEcoreInstantiator{
	@SuppressWarnings("serial")
	protected Map<String,Class<?>> classes = new HashMap<String,Class<?>>(){
		public java.lang.Class<?> put(String key, java.lang.Class<?> value) {
			try{
				String cn = value.getName();
				String customName = cn.substring(0,value.getName().length()-4) + "Custom";
				Class<?> cl = value.getClassLoader().loadClass(customName);
				return super.put(key, cl);
			}catch(Exception e){
				return super.put(key, value);
				
			}
			
		};
	};
	@SuppressWarnings("unchecked")
	public <T> T newInstance(String type){
		return (T) IntrospectionUtil.newInstance(classes.get(type));
	}
}
