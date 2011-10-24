package org.opaeum.hibernate.domain;

import java.io.Serializable;

import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultPersistEventListener;

public class PersistEventListener extends DefaultPersistEventListener{
	private static final long serialVersionUID = 5529436424155095000L;
	@Override
	protected Serializable saveWithRequestedId(Object entity,Serializable requestedId,String entityName,Object anything,EventSource source){
		return super.saveWithRequestedId(entity, requestedId, entityName, anything, source);
	}
	@Override
	protected Serializable saveWithGeneratedId(Object entity,String entityName,Object anything,EventSource source,
			boolean requiresImmediateIdAccess){
		return super.saveWithGeneratedId(entity, entityName, anything, source, requiresImmediateIdAccess);
	}

	
}
