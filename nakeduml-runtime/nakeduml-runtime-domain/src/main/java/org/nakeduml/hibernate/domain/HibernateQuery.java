package org.nakeduml.hibernate.domain;

import java.util.Collection;
import java.util.Date;

import org.nakeduml.environment.Query;
import org.nakeduml.runtime.domain.IPersistentObject;

public class HibernateQuery implements Query{
	org.hibernate.Query delegate;
	public HibernateQuery(org.hibernate.Query createQuery){
		this.delegate = createQuery;
	}
	@Override
	public int executeUpdate(){
		return delegate.executeUpdate();
	}
	@Override
	public Collection<IPersistentObject> executeQuery(){
		return delegate.list();
	}
	@Override
	public void setParameter(String name,Object value){
		if(value instanceof Date){
			delegate.setDate(name, (Date) value);
		}else{
			delegate.setParameter(name, value);
		}
	}
}
