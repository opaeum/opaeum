package org.opaeum.hibernate.domain;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.Status;
import org.hibernate.event.internal.DefaultFlushEntityEventListener;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;

public class FlushEntityListener extends DefaultFlushEntityEventListener{
	private static final long serialVersionUID = -5868638409624191832L;
	@Override
	protected boolean invokeInterceptor(SessionImplementor session,Object entity,EntityEntry entry,Object[] values,EntityPersister persister){
		boolean isDirty = false;
		if(entry.getStatus() != Status.DELETED){
			if(entity instanceof ProcessInstanceInfo){
				ProcessInstanceInfo processInstanceInfo = (ProcessInstanceInfo) entity;
				processInstanceInfo.update();
			}
			isDirty = copyState(entity, persister.getPropertyTypes(), values, session.getFactory());
		}
		return super.invokeInterceptor(session, entity, entry, values, persister) || isDirty;
	}
	/**
	 * copy the entity state into the state array and return true if the state has changed
	 */
	private boolean copyState(Object entity,Type[] types,Object[] state,SessionFactory sf){
		ClassMetadata metadata = sf.getClassMetadata(entity.getClass());
		Object[] newState = metadata. getPropertyValues(entity);
		int size = newState.length;
		boolean isDirty = false;
		for(int index = 0;index < size;index++){
			if(!types[index].isEqual(state[index], newState[index])){
				isDirty = true;
				state[index] = newState[index];
			}
		}
		return isDirty;
	}
}
