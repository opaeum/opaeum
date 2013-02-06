package org.opaeum.runtime.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEcoreInstantiator{
	protected Map<String,Class<?>> classes = new HashMap<String,Class<?>>();
	public <T> T newInstance(String type){
		if(!classes.containsKey(type)){
			System.out.println();
		}
		return (T) IntrospectionUtil.newInstance(classes.get(type));
	}
}
