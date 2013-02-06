package org.opaeum.audit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.hibernate.HibernateException;
import org.hibernate.classic.Lifecycle;
import org.hibernate.ejb.event.EJB3FlushEntityEventListener;
import org.hibernate.ejb.event.EntityCallbackHandler;
import org.hibernate.engine.internal.Versioning;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.FlushEntityEvent;
import org.hibernate.event.spi.FlushEntityEventListener;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.opaeum.hibernate.domain.AbstractInterfaceValue;
import org.opaeum.hibernate.domain.CascadingInterfaceValue;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditListener extends EventDispatcher implements PostInsertEventListener,PostLoadEventListener,PostUpdateEventListener,
		FlushEventListener{
	private static final long serialVersionUID = -233067098331332700L;
	private static final Logger log = LoggerFactory.getLogger(AuditListener.class);
	private static LinkedBlockingQueue<AuditWorkUnit> queue = new LinkedBlockingQueue<AuditWorkUnit>();
	static{
		// HAck!! No event to trap session closure
		new Thread(AuditListener.class.getName() + "::Audit thread"){
			@Override
			public void run(){
				while(true){
					AuditWorkUnit wu = null;
					try{
						wu = queue.poll(10, TimeUnit.SECONDS);
						if(wu != null){
							wu.flush();
						}
					}catch(Throwable t){
						logger.error(t.getMessage(), t);
						if(wu != null){
							logger.error("AUDIT SQL DUMP");
							logger.error(wu.getAuditEntryInsert().toString());
							logger.error(wu.getPropertyChangeInsert().toString());
						}
					}
				}
			}
		}.start();
	}
	@Override
	public void onPostUpdate(PostUpdateEvent event){
		super.onPostUpdate(event);
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
		return lazyGetAttachment(session).getAuditWorkUnit();
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		super.onFlush(event);
		final EventSource source = event.getSession();
		if(source.getPersistenceContext().hasNonReadOnlyEntities()){
			try{
				queue.put(getWorkUnitForSession(source));
			}catch(InterruptedException e){
				throw new RuntimeException(e);
			}
			lazyGetAttachment(source).clearAuditWorkUnit();
		}
	}

	@Override
	public void onPostLoad(PostLoadEvent event){
		super.onPostLoad(event);
		// NB!!! Don't touch this code - copied from hibernate
		if(event.getPersister().implementsLifecycle()){
			// log.debug( "calling onLoad()" );
			((Lifecycle) event.getEntity()).onLoad(event.getSession(), event.getId());
		}
		if(event.getEntity() instanceof AuditEntry){
			AuditEntry ae = (AuditEntry) event.getEntity();
			ae.setSession(event.getSession());
		}else if(event.getEntity() instanceof PropertyChange){
			PropertyChange<?> c = (PropertyChange<?>) event.getEntity();
			c.resolve(event.getSession());
		}
	}
}
