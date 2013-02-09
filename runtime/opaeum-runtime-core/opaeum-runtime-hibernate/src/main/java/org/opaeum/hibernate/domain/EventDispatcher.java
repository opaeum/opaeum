package org.opaeum.hibernate.domain;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.event.internal.AbstractFlushingEventListener;
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
import org.hibernate.proxy.HibernateProxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.ExceptionAnalyser;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.event.ChangedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDispatcher extends AbstractFlushingEventListener implements PostLoadEventListener,FlushEventListener,
		PostInsertEventListener,PostUpdateEventListener,FlushEntityEventListener,PersistEventListener{
	private static final long serialVersionUID = -8583155822068850343L;
	private static final Map<SessionFactory,Environment> opaeumEnvironmentMap = Collections
			.synchronizedMap(new HashMap<SessionFactory,Environment>());
	public static Map<EventSource,SessionAttachment> sessionAttachments = Collections
			.synchronizedMap(new HashMap<EventSource,SessionAttachment>());
	protected static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);
	static Map<ChangedEntity,ChangedEntity> recentlyChangedEntities = new HashMap<ChangedEntity,ChangedEntity>();
	static{
		// HAck!! No event to trap session closure
		new Thread(EventDispatcher.class.getName() + "::Grim reaper thread"){
			@Override
			public void run(){
				while(true){
					try{
						sleep(10000);
						synchronized(recentlyChangedEntities){
							Iterator<Entry<ChangedEntity,ChangedEntity>> iter = recentlyChangedEntities.entrySet().iterator();
							while(iter.hasNext()){
								ChangedEntity changedEntity = (ChangedEntity) iter.next();
								if(changedEntity.changedOn < System.currentTimeMillis() - (1000 * 60 * 30)){
									iter.remove();
								}
							}
						}
						EventSource[] array = sessionAttachments.keySet().toArray(new EventSource[sessionAttachments.size()]);
						for(EventSource eventSource:array){
							if(eventSource.isClosed()){
								SessionAttachment att = sessionAttachments.remove(eventSource);
								att.cleanUp();
							}else{
								SessionAttachment sessionAttachment = sessionAttachments.get(eventSource);
								if(sessionAttachment.getStartTime() < System.currentTimeMillis() - (60000 * 30) && !sessionAttachment.hasBeenLogged()){
									sessionAttachment.logged();
									logger.error("Potential session leak! Session has been open for "
											+ ((System.currentTimeMillis() - sessionAttachment.getStartTime()) / 60000) + " minutes");
									logger.error("Session opened from " + sessionAttachment.getStartingThread());
								}
							}
						}
					}catch(Throwable t){
						logger.error(t.getMessage(), t);
					}
				}
			}
		}.start();
	}
	@Override
	public void onPostUpdate(PostUpdateEvent event){
		if(event.getEntity() instanceof IPersistentObject){
			IPersistentObject entity = (IPersistentObject) event.getEntity();
			EventSource session = event.getSession();
			addChangedEntity(entity, (Long) event.getId(), session);
			int[] dirty = event.getDirtyProperties();
			Object[] newValues = new Object[dirty.length];
			Object[] newState = event.getState();
			for(int i = 0;i < dirty.length;i++){
				newValues[i] = newState[dirty[i]];
			}
			Object[] oldValues = new Object[dirty.length];
			Object[] oldState = event.getOldState();
			for(int i = 0;i < dirty.length;i++){
				oldValues[i] = oldState[dirty[i]];
			}
			registerReferencedObjects(session, oldValues);
			registerReferencedObjects(session, event.getState());
		}
	}
	private void registerReferencedObjects(EventSource session,Object[] oldState){
		for(Object object:oldState){
			if(object instanceof IPersistentObject){
				// TODO optimise: check for existence of inverse reference
				addChangedEntity((IPersistentObject) object, (Long) session.getIdentifier(object), session);
			}else if(object instanceof PersistentCollection){
				PersistentCollection pc = (PersistentCollection) object;
				if(pc.wasInitialized()){
					if(pc instanceof Map){
						Map<?,?> coll = (Map<?,?>) pc;
						for(Object referencedObject:coll.values()){
							if(referencedObject instanceof IPersistentObject){
								// TODO optimise: check for existence of inverse reference
								addChangedEntity((IPersistentObject) referencedObject, (Long) session.getIdentifier(referencedObject), session);
							}
						}
					}else{
						Collection<?> coll = (Collection<?>) pc;
						for(Object referencedObject:coll){
							if(referencedObject instanceof IPersistentObject){
								// TODO optimise: check for existence of inverse reference
								addChangedEntity((IPersistentObject) referencedObject, (Long) session.getIdentifier(referencedObject), session);
							}
						}
					}
				}
			}
		}
	}
	protected Integer getVersion(EventSource session,Object object){
		if(object instanceof HibernateProxy && ((HibernateProxy) object).getHibernateLazyInitializer().isUninitialized()){
			return Integer.MAX_VALUE;
		}else{
			EntityEntry entry = session.getPersistenceContext().getEntry(object);
			EntityPersister persister = entry.getPersister();
			int[] dirty = persister.findDirty(persister.getPropertyValues(object), entry.getLoadedState(), object, session);
			if(dirty == null || dirty.length == 0){
				return Integer.MAX_VALUE;//
			}else{
				Number version = (Number) entry.getVersion();
				int versionInt = version.intValue();
				return versionInt;
			}
		}
	}
	protected void addChangedEntity(IPersistentObject entity,Long id,EventSource session){
		ChangedEntity ce = new ChangedEntity((Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entity), id);
		if(ce.objectVersion != Integer.MAX_VALUE){// Object not read when flush occurred,only attempt objectVersion retrieval once
			ce.objectVersion = getVersion(session, entity);
		}
	}
	@Override
	public void onPostInsert(PostInsertEvent event){
		maybeRegisterEventGenerator(event.getEntity(), event.getSession());
		registerReferencedObjects(event.getSession(), event.getState());
	}
	protected AbstractPersistence getPersistence(EventSource session,Object o){
		return lazyGetAttachment(session, o).getPersistence();
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		maybeRegisterEventGenerator(event.getEntity(), event.getSession());
		try{
			Field declaredField = event.getPersister().getMappedClass().getDeclaredField("persistence");
			if(declaredField != null){
				declaredField.setAccessible(true);
				declaredField.set(event.getEntity(), getPersistence(event.getSession(), event.getEntity()));
			}
		}catch(NoSuchFieldException e){
		}catch(RuntimeException re){
			throw re;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private void maybeRegisterEventGenerator(Object entity,EventSource session){
		if(entity instanceof IEventGenerator){
			SessionAttachment sessionAttachment = lazyGetAttachment(session, entity);
			sessionAttachment.addEventGenerator((IEventGenerator) entity);
		}
	}
	protected SessionAttachment lazyGetAttachment(EventSource session,Object o){
		SessionAttachment sessionAttachment = sessionAttachments.get(session);
		if(sessionAttachment == null){
			sessionAttachment = new SessionAttachment(session, lazyGetOpaeumEnvironment(session, o));
			sessionAttachments.put(session, sessionAttachment);
		}
		return sessionAttachment;
	}
	private Environment lazyGetOpaeumEnvironment(EventSource session,Object o){
		Environment environment = opaeumEnvironmentMap.get(session.getFactory());
		if(environment == null){
			NumlMetaInfo annotation = IntrospectionUtil.getOriginalClass(o.getClass()).getAnnotation(NumlMetaInfo.class);
			if(annotation != null && annotation.environment() != Environment.class){
				try{
					Field instance = annotation.environment().getDeclaredField("INSTANCE");
					environment = (Environment) instance.get(null);
					opaeumEnvironmentMap.put(session.getSessionFactory(), environment);
				}catch(Exception e){
					new ExceptionAnalyser(e).throwRootCause();
					//will break here
				}
			}
		}
		return environment;
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		final EventSource source = event.getSession();
		if(source.getPersistenceContext().hasNonReadOnlyEntities()){
			// Generate Ids, perform flush events, register newly inserted
			// objects
			performFlush(event, source);
		}
		// NB!! entities may not have changed, but events may have been
		// generated
		dispatchEventsAndSaveProcesses(event, source);
		postFlush(source);
	}
	protected void cleanup(final EventSource source){
		sessionAttachments.remove(source);
	}
	protected void dispatchEventsAndSaveProcesses(FlushEvent event,final EventSource source){
		SessionAttachment sa = sessionAttachments.get(source);
		if(sa != null){
			Set<EventOccurrence> dispatchEvents = saveEvents(source, sa.getEventGenerators(), sa);
			Set<String> cancelledEvents = deleteEvents(event, source);
			performFlush(event, source);
			scheduleEvents(sa.getEnvironment(), dispatchEvents);
			cancelEvents(sa.getEnvironment(), cancelledEvents);
			synchronized(recentlyChangedEntities){
				Set<Entry<ChangedEntity,ChangedEntity>> entrySet = sa.getChangedEntities().entrySet();
				for(Entry<ChangedEntity,ChangedEntity> entry:entrySet){
					ChangedEntity oldChange = recentlyChangedEntities.put(entry.getKey(), entry.getValue());
					if(entry.getKey().objectVersion == Integer.MAX_VALUE){
						entry.getKey().objectVersion = oldChange.objectVersion;
					}
				}
			}
			sa.onFlush();
		}
	}
	protected void performFlush(FlushEvent event,final EventSource source){
		flushEverythingToExecutions(event);
		performExecutions(source);
		if(source.getFactory().getStatistics().isStatisticsEnabled()){
			source.getFactory().getStatisticsImplementor().flush();
		}
	}
	protected void scheduleEvents(Environment env,Set<EventOccurrence> allEventOccurrences){
		for(EventOccurrence eo:allEventOccurrences){
			env.getEventService().scheduleEvent(eo);
		}
	}
	protected Set<EventOccurrence> saveEvents(final EventSource source,Set<IEventGenerator> eventGenerators,SessionAttachment sa){
		Set<EventOccurrence> allEventOccurrences = new HashSet<EventOccurrence>();
		for(IEventGenerator eg:eventGenerators){
			for(OutgoingEvent entry:eg.getOutgoingEvents()){
				if(entry.getTarget() != null && !(entry.getTarget() instanceof Collection && ((Collection<?>) entry.getTarget()).isEmpty())){
					EventOccurrence occurrence = new EventOccurrence(entry.getTarget(), entry.getHandler(), sa.getEnvironment());
					occurrence.prepareForDispatch(sa.getEnvironment());
					source.persist(occurrence);
					allEventOccurrences.add(occurrence);
				}
			}
			eg.getOutgoingEvents().clear();
		}
		return allEventOccurrences;
	}
	protected void cancelEvents(Environment env,Set<String> uuids){
		for(String uuid:uuids){
			env.getEventService().cancelEvent(uuid);
		}
	}
	protected Set<String> deleteEvents(FlushEvent event,final EventSource source){
		SessionAttachment attachment = sessionAttachments.get(event.getSession());
		if(attachment != null){
			Set<IEventGenerator> eventGenerators = attachment.getEventGenerators();
			Set<String> allCancellations = new HashSet<String>();
			Query delete = source.createQuery("delete from EventOccurrence where uuid=:uuid");
			for(IEventGenerator eg:eventGenerators){
				for(CancelledEvent entry:eg.getCancelledEvents()){
					String eventOccurrenceUuid = EventOccurrence.uuid(entry.getTarget(), entry.getEventId());
					delete.setString("uuid", eventOccurrenceUuid);
					delete.executeUpdate();
					allCancellations.add(eventOccurrenceUuid);
				}
				eg.getCancelledEvents().clear();
			}
			return allCancellations;
		}else{
			return Collections.emptySet();
		}
	}
	@SuppressWarnings("rawtypes")
	public void onPersist(PersistEvent event,Map createdAlready) throws HibernateException{
		Class<? extends Object> originalClass = IntrospectionUtil.getOriginalClass(event.getObject().getClass());
		if(originalClass.getAnnotation(NumlMetaInfo.class) != null){
		}
		onPersist(event);
	}
	@Override
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException{
		if(event.getEntity() instanceof IPersistentObject){
			EventSource session = event.getSession();
			Object[] propertyValues = event.getPropertyValues();
			doInterfaceValues(session, event.getEntity(), propertyValues);
		}
	}
	@SuppressWarnings("rawtypes")
	private void doInterfaceValues(EventSource session,Object entity,Object[] propertyValues){
		SessionAttachment sa = sessionAttachments.get(session);
		for(Object object2:propertyValues){
			if(object2 instanceof AbstractInterfaceValue){
				AbstractInterfaceValue iv = (AbstractInterfaceValue) object2;
				if(iv.hasValue() && iv.getIdentifier() == null){
					IPersistentObject value = iv.getValue(getPersistence(session, entity));
					if(iv instanceof CascadingInterfaceValue){
						String entityName = IntrospectionUtil.getOriginalClass(value).getSimpleName();
						session.persistOnFlush(entityName, value, new HashMap());
					}
					iv.setValue(value, sa.getPersistence().getMetaInfoMap());// Populate the id
				}
			}
		}
	}
	public static void main(String[] args){
		Map<ChangedEntity,ChangedEntity> set = new HashMap<ChangedEntity,ChangedEntity>();
		long start = System.currentTimeMillis();
		for(long i = 0;i < 10000000;i++){
			ChangedEntity ce = new ChangedEntity(IPersistentObject.class, i);
			set.put(ce, ce);
		}
		for(long i = 0;i < 50000;i++){
			set.get(new ChangedEntity(IPersistentObject.class, i));
		}
		Iterator<Entry<ChangedEntity,ChangedEntity>> iterator = set.entrySet().iterator();
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		System.out.println(System.currentTimeMillis() - start);
		System.exit(0);
	}
	@Override
	public void onPersist(PersistEvent event) throws HibernateException{
		EventSource session = event.getSession();
		String entityName = event.getEntityName();
		EntityPersister p = session.getEntityPersister(entityName, event.getObject());
		Object[] propertyValues = p.getPropertyValues(event.getObject());
		doInterfaceValues(session, event.getObject(), propertyValues);
	}
}
