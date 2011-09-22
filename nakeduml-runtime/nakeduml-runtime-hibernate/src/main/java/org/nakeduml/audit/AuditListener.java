package org.nakeduml.audit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Lifecycle;
import org.hibernate.engine.Versioning;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.Initializable;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.nakeduml.hibernate.domain.EventDispatcher;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class AuditListener extends EventDispatcher implements PostUpdateEventListener,
		FlushEventListener, Initializable {
	private static final long serialVersionUID = -233067098331332700L;
	private static final Map<EventSource, AuditWorkUnit> entries = Collections.synchronizedMap(new HashMap<EventSource, AuditWorkUnit>());

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object entity = event.getEntity();
		EntityPersister persister = event.getPersister();
		EventSource session = event.getSession();
		if (entity instanceof IPersistentObject && IntrospectionUtil.getOriginalClass(entity).isAnnotationPresent(AuditMe.class)) {
			int[] d = persister.findDirty(event.getState(), event.getOldState(), entity, session);
			if (d != null && d.length > 0) {
				Number n = (Number) Versioning.getVersion(event.getState(), persister);
				getWorkUnitForSession(session).logPropertyChanges(event.getOldState(), event.getState(), d, (IPersistentObject) entity,
						persister.getPropertyNames(), n.intValue());
			}
		}
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object entity = event.getEntity();
		EntityPersister persister = event.getPersister();
		EventSource session = event.getSession();
		if (entity instanceof IPersistentObject && IntrospectionUtil.getOriginalClass(entity).isAnnotationPresent(AuditMe.class)) {
			Number n = (Number) Versioning.getVersion(event.getState(), persister);
			getWorkUnitForSession(session).logInsertedProperties(event.getState(), persister.getPropertyNames(), (IPersistentObject) entity, n.intValue());
		}
		super.onPostInsert(event);
	}

	private AuditWorkUnit getWorkUnitForSession(EventSource session) {
		AuditWorkUnit map = entries.get(session);
		if (map == null) {
			map = new AuditWorkUnit(session);
			entries.put(session, map);
		}
		return map;
	}

	@Override
	public void onFlush(FlushEvent event) throws HibernateException {
		final EventSource source = event.getSession();
		if (source.getPersistenceContext().hasNonReadOnlyEntities()) {
			super.performFlush(event, source);
			if (source.getFactory().getStatistics().isStatisticsEnabled()) {
				source.getFactory().getStatisticsImplementor().flush();
			}
			getWorkUnitForSession(source).flush();
		}
		dispatchEventsAndSaveProcesses(event, source);
		postFlush(source);
		entries.remove(source);
	}


	@Override
	public void initialize(Configuration cfg) {
		cfg.getEventListeners().getPostLoadEventListeners()[1] = this;
		// hahahahahahahahaha

	}

	@Override
	public void onPostLoad(PostLoadEvent event) {
		super.onPostLoad(event);
		//NB!!! Don't touch this code - copied from hibernate
		if ( event.getPersister().implementsLifecycle( event.getSession().getEntityMode() ) ) {
			//log.debug( "calling onLoad()" );
			( ( Lifecycle ) event.getEntity() ).onLoad( event.getSession(), event.getId() );
		}

		if (event.getEntity() instanceof AuditEntry) {
			AuditEntry ae = (AuditEntry) event.getEntity();
			ae.setOriginal((IPersistentObject) event.getSession().load(ae.getOriginalClass(), ae.getOriginalId()));
		} else if (event.getEntity() instanceof PropertyChange) {
			PropertyChange<?> c = (PropertyChange<?>) event.getEntity();
			c.resolve(event.getSession());
		}
	}

}