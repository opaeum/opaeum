package org.opeum.hibernate.domain;

import java.io.Serializable;

import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

public class SaveOrUpdateEventListener extends DefaultSaveOrUpdateEventListener{
	private static final long serialVersionUID = 8670277005910737640L;
	@Override
	protected Serializable saveWithRequestedId(Object entity,Serializable requestedId,String entityName,Object anything,EventSource source){
		return super.saveWithRequestedId(entity, requestedId, entityName, anything, source);
	}
	@Override
	protected Serializable saveWithGeneratedId(Object entity,String entityName,Object anything,EventSource source,boolean requiresImmediateIdAccess){
		return super.saveWithGeneratedId(entity, entityName, anything, source, requiresImmediateIdAccess);
	}
}
