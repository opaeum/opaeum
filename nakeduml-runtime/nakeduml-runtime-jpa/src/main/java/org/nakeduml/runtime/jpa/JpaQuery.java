package org.nakeduml.runtime.jpa;

import java.util.Collection;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.persistence.Query;

public class JpaQuery implements Query{
	javax.persistence.Query query;
	public JpaQuery(javax.persistence.Query query){
		super();
		this.query = query;
	}
	@Override
	public int executeUpdate(){
		return query.executeUpdate();
	}
	@Override
	public Collection<IPersistentObject> executeQuery(){
		return query.getResultList();
	}
	@Override
	public void setParameter(String name,Object value){
		query.setParameter(name, value);
	}
}
