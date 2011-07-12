package org.nakeduml.environment.marshall;

import org.nakeduml.runtime.domain.IPersistentObject;

public class EntityValue extends Value{
	private static final long serialVersionUID = 1907963640694864841L;
	Long id;
	public EntityValue(Integer typeId,IPersistentObject e){
		super(typeId);
		this.id = e.getId();
	}
	public Long getId(){
		return id;
	}
}