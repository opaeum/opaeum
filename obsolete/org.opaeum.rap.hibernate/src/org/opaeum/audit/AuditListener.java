package org.opaeum.audit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.internal.Versioning;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class AuditListener extends EventDispatcher implements PostInsertEventListener,PostLoadEventListener,PostUpdateEventListener,
		FlushEventListener,Integrator{
	private static final long serialVersionUID = -233067098331332700L;
	private static final Map<EventSource,AuditWorkUnit> entries = Collections.synchronizedMap(new HashMap<EventSource,AuditWorkUnit>());
	@Override
	public void onPostUpdate(PostUpdateEvent event){
		Object entity = event.getEntity();
		EntityPersister persister = event.getPersister();
		EventSource session = event.getSession();
		if(entity instanceof IPersistentObject && IntrospectionUtil.getOriginalClass(entity).isAnnotationPresent(AuditMe.class)){
			int[] d = persister.findDirty(event.getState(), event.getOldState(), entity, session);
			if(d != null && d.length > 0){
				Number n = (Number) Versioning.getVersion(event.getState(), persister);
				getWorkUnitForSession(session).logPropertyChanges(event.getOldState(), event.getState(), d, (IPersistentObject) entity,
						persister.getPropertyNames(), n.intValue());
			}
		}
	}
	@Override
	public void onPostInsert(PostInsertEvent event){
		super.onPostInsert(event);
		Object entity = event.getEntity();
		EntityPersister persister = event.getPersister();
		EventSource session = event.getSession();
		if(entity instanceof IPersistentObject && IntrospectionUtil.getOriginalClass(entity).isAnnotationPresent(AuditMe.class)){
			Number n = (Number) Versioning.getVersion(event.getState(), persister);
			getWorkUnitForSession(session).logInsertedProperties(event.getState(), persister.getPropertyNames(), (IPersistentObject) entity,
					n.intValue());
		}
	}
	private AuditWorkUnit getWorkUnitForSession(EventSource session){
		AuditWorkUnit map = entries.get(session);
		if(map == null){
			map = new AuditWorkUnit(session);
			entries.put(session, map);
		}
		return map;
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		super.onFlush(event);
		final EventSource source = event.getSession();
		AuditWorkUnit auditWorkUnit = entries.get(source);
		if(auditWorkUnit != null){
			auditWorkUnit.flush();
			entries.remove(source);
		}
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		super.onPostLoad(event);
		if(event.getEntity() instanceof AuditEntry){
			AuditEntry ae = (AuditEntry) event.getEntity();
			ae.setOriginal((IPersistentObject) event.getSession().load(ae.getOriginalClass(), ae.getOriginalId()));
		}else if(event.getEntity() instanceof PropertyChange){
			PropertyChange<?> c = (PropertyChange<?>) event.getEntity();
			c.resolve(event.getSession());
		}
	}
	@Override
	public void integrate(Configuration configuration,SessionFactoryImplementor sessionFactory,SessionFactoryServiceRegistry serviceRegistry){
		System.out.println();
		// TODO ensure that this is registered as a PostLoadListener
	}
	@Override
	public void integrate(MetadataImplementor metadata,SessionFactoryImplementor sessionFactory,SessionFactoryServiceRegistry serviceRegistry){
		System.out.println();
	}
	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory,SessionFactoryServiceRegistry serviceRegistry){
	}
}
