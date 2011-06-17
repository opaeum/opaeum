package org.nakeduml.hibernate.domain;

import java.io.Serializable;

import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultPersistEventListener;
import org.hibernate.event.def.DefaultSaveEventListener;
import org.nakeduml.runtime.domain.BaseAuditable;

public class PersistEventListener extends DefaultPersistEventListener{
	@Override
	protected Serializable saveWithRequestedId(Object entity,Serializable requestedId,String entityName,Object anything,EventSource source){
		if(entity instanceof BaseAuditable){
			((BaseAuditable) entity).defaultCreate();
		}
		return super.saveWithRequestedId(entity, requestedId, entityName, anything, source);
	}
	@Override
	protected Serializable saveWithGeneratedId(Object entity,String entityName,Object anything,EventSource source,
			boolean requiresImmediateIdAccess){
		if(entity instanceof BaseAuditable){
			((BaseAuditable) entity).defaultCreate();
		}
		return super.saveWithGeneratedId(entity, entityName, anything, source, requiresImmediateIdAccess);
	}

	
}
