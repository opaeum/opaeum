package org.opaeum.runtime.environment.marshall;

import org.opaeum.runtime.domain.IPersistentObject;

public class EntityValue extends Value{
	private static final long serialVersionUID = 1907963640694864841L;
	Long id;
	public EntityValue(String typeId,IPersistentObject e){
		super(typeId);
		this.id = e.getId();
	}
	public Long getId(){
		return id;
	}

}