package org.nakeduml.seam3.persistence;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.Status;
import org.hibernate.event.def.DefaultFlushEntityEventListener;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.nakeduml.runtime.domain.BaseAuditable;

public class PreUpdateListener extends DefaultFlushEntityEventListener {

	private static final long serialVersionUID = -5868638409624191832L;

	@Override
	protected boolean invokeInterceptor(SessionImplementor session, Object entity, EntityEntry entry, Object[] values, EntityPersister persister) {
		boolean isDirty = false;
		if (entry.getStatus() != Status.DELETED) {
			if (entity instanceof BaseAuditable) {
				BaseAuditable baseAuditable = (BaseAuditable) entity;
				baseAuditable.defaultUpdate();
			} else if (entity instanceof ProcessInstanceInfo) {
				ProcessInstanceInfo processInstanceInfo = (ProcessInstanceInfo) entity;
				processInstanceInfo.update();
			}
			isDirty = copyState(entity, persister.getPropertyTypes(), values, session.getFactory());
		}
		return super.invokeInterceptor(session, entity, entry, values, persister) || isDirty;
	}

	/**
	 * copy the entity state into the state array and return true if the state
	 * has changed
	 */
	private boolean copyState(Object entity, Type[] types, Object[] state, SessionFactory sf) {
		ClassMetadata metadata = sf.getClassMetadata(entity.getClass());
		Object[] newState = metadata.getPropertyValues(entity, EntityMode.POJO);
		int size = newState.length;
		boolean isDirty = false;
		for (int index = 0; index < size; index++) {
			if (!types[index].isEqual(state[index], newState[index], EntityMode.POJO)) {
				isDirty = true;
				state[index] = newState[index];
			}
		}
		return isDirty;
	}

}
