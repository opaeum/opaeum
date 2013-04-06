package org.opaeum.hibernate.domain;

import java.util.Date;
import java.util.List;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.Query;

public class HibernateQuery implements Query{
	org.hibernate.Query delegate;
	public HibernateQuery(org.hibernate.Query createQuery){
		this.delegate = createQuery;
	}
	@Override
	public int executeUpdate(){
		return delegate.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IPersistentObject> executeQuery(){
		return delegate.list();
	}
	@Override
	public Query setParameter(String name,Object value){
		if(value instanceof Date){
			delegate.setDate(name, (Date) value);
		}else{
			delegate.setParameter(name, value);
		}
		return this;
	}
}
