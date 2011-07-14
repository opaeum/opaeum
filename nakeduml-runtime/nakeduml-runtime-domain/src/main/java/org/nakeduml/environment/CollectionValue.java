package org.nakeduml.environment;

import java.util.Collection;

import org.hibernate.Session;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class CollectionValue extends Value{
	private static final long serialVersionUID = 531640008870617688L;
	Collection<Value> values;
	public CollectionValue(Collection<Value> c){
		this.values=c;
	}
	public Object getValue(Session session){
		Collection<Object> c = IntrospectionUtil.newInstance(values.getClass());
		for(Value v:values){
			c.add(v.getValue(session));
		}
		return c;
	}
	@Override
	public Class<?> getValueClass(){
		return values.getClass();
	}
}