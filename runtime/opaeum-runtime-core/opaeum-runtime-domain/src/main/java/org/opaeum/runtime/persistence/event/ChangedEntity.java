package org.opaeum.runtime.persistence.event;

import org.opaeum.runtime.domain.IPersistentObject;

public class ChangedEntity{
	public long changedOn=System.currentTimeMillis();
	public int objectVersion;

	public Class<? extends IPersistentObject> entityClass;
	public long id;
	public ChangedEntity(Class<? extends IPersistentObject> entity,Long id2){
		this.id = id2;
		this.entityClass = entity;
	}
	@Override
	public int hashCode(){
		return entityClass.hashCode() + (int)id;
	}
	@Override
	public boolean equals(Object obj){
		ChangedEntity other = (ChangedEntity) obj;
		return other.entityClass == entityClass && other.id==id;
	}
}
