package org.opaeum.audit;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.classic.Lifecycle;
import org.hibernate.engine.Versioning;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.opaeum.hibernate.domain.EventDispatcher;
import org.opaeum.hibernate.domain.SessionAttachment;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditListener extends EventDispatcher implements PostInsertEventListener,PostLoadEventListener,PostUpdateEventListener,
		FlushEventListener{
	private static final long serialVersionUID = -233067098331332700L;
	private static final Logger log = LoggerFactory.getLogger(AuditListener.class);
	private static LinkedBlockingQueue<AuditWorkUnit> queue = new LinkedBlockingQueue<AuditWorkUnit>();
	static{
		// HAck!! No event to trap session closure
		Thread t = new Thread(AuditListener.class.getName() + "::Audit thread"){
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
		};
		t.setDaemon(true);
		t.start();
	}
	protected SessionAttachment newSessionAttachment(EventSource session,Environment env){
		return new AuditSessionAttachment(session, env);
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
				getWorkUnitForSession(session, event.getEntity()).logPropertyChanges(event.getOldState(), event.getState(), d,
						(IPersistentObject) entity, persister.getPropertyNames(), n.intValue());
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
			getWorkUnitForSession(session, event.getEntity()).logInsertedProperties(event.getState(), persister.getPropertyNames(),
					(IPersistentObject) entity, n.intValue());
		}
	}
	private AuditWorkUnit getWorkUnitForSession(EventSource session,Object o){
		return ((AuditSessionAttachment) lazyGetAttachment(session, o)).getAuditWorkUnit();
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		super.onFlush(event);
		final EventSource source = event.getSession();
		if(source.getPersistenceContext().hasNonReadOnlyEntities()){
			SessionAttachment sessionAttachment = sessionAttachments.get(event.getSession());
			if(sessionAttachment != null){
				try{
					queue.put(getWorkUnitForSession(source, null));
				}catch(InterruptedException e){
					throw new RuntimeException(e);
				}
				((AuditSessionAttachment) sessionAttachment).clearAuditWorkUnit();
			}
		}
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		super.onPostLoad(event);
		// NB!!! Don't touch this code - copied from hibernate
		if(event.getPersister().implementsLifecycle(EntityMode.POJO)){
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
