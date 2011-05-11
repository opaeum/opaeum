package org.nakeduml.hibernate.domain;

import java.io.Serializable;

import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultPersistEventListener;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.nakeduml.runtime.domain.BaseAuditable;

public class PrePersistListener extends DefaultPersistEventListener {

	private static final long serialVersionUID = -5348280857554642628L;

	public PrePersistListener() {
		super();
	}

	@Override
	protected Serializable saveWithRequestedId(Object entity, Serializable requestedId, String entityName, Object anything, EventSource source) {
		if (entity instanceof BaseAuditable) {
			BaseAuditable baseAuditable = (BaseAuditable) entity;
			baseAuditable.defaultCreate();
		} else if (entity instanceof ProcessInstanceInfo) {
			ProcessInstanceInfo processInstanceInfo = (ProcessInstanceInfo) entity;
			processInstanceInfo.update();
		}		
		return super.saveWithRequestedId(entity, requestedId, entityName, anything, source);
	}

	@Override
	protected Serializable saveWithGeneratedId(Object entity, String entityName, Object anything, EventSource source, boolean requiresImmediateIdAccess) {
		if (entity instanceof BaseAuditable) {
			BaseAuditable baseAuditable = (BaseAuditable) entity;
			baseAuditable.defaultCreate();
//		} else if (entity instanceof ProcessInstanceInfo) {
//			ProcessInstanceInfo processInstanceInfo = (ProcessInstanceInfo) entity;
//			processInstanceInfo.update();
		}		
		return super.saveWithGeneratedId(entity, entityName, anything, source, true);
	}
	
}
