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

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.engine.EntityEntry;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEntityEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.PersistEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.CollectionType;
import org.hibernate.type.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.ExceptionAnalyser;
import org.opaeum.runtime.domain.IAnyValue;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.event.ChangedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDispatcher extends AbstractFlushingEventListener implements PostLoadEventListener,FlushEventListener,
		PostInsertEventListener,PostUpdateEventListener,FlushEntityEventListener,PersistEventListener,PreUpdateEventListener{
	private static final long serialVersionUID = -8583155822068850343L;
	private static final Map<SessionFactory,Environment> opaeumEnvironmentMap = Collections
			.synchronizedMap(new HashMap<SessionFactory,Environment>());
	public static Map<EventSource,SessionAttachment> sessionAttachments = Collections
			.synchronizedMap(new HashMap<EventSource,SessionAttachment>());
	protected static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);
	static Map<ChangedEntity,ChangedEntity> recentlyChangedEntities = Collections.synchronizedMap(new HashMap<ChangedEntity,ChangedEntity>());
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
								if(changedEntity.changedOn < System.currentTimeMillis() - (1000l * 60 * 30)){
									iter.remove();
								}
							}
						}
						EventSource[] array = sessionAttachments.keySet().toArray(new EventSource[sessionAttachments.size()]);
						for(EventSource eventSource:array){
							if(eventSource.isClosed()){
								SessionAttachment att = sessionAttachments.remove(eventSource);
								// THis is essential as there is no listener for closing a session!
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
			SessionAttachment sa = lazyGetAttachment(session, entity);
			addChangedEntity(sa, entity, (Long) event.getId(), true);
			Type[] propertyTypes = event.getPersister().getPropertyTypes();
			Object[] newState = event.getState();
			Object[] oldState = event.getOldState();
			for(int i = 0;i < propertyTypes.length;i++){
				registerReferencedObjects(sa, propertyTypes[i], oldState[i], newState[i]);
			}
		}
	}
	private void registerReferencedObjects(SessionAttachment sa,Type type,Object oldValue,Object newValue){
		if(oldValue instanceof IPersistentObject || newValue instanceof IPersistentObject){
			if(type.isDirty(oldValue, newValue, sa.session)){
				// May need to reload the inverse references,i.e. collections in the affected objects
				// TODO optimise: check for existence of inverse reference
				if(oldValue != null){
					addChangedEntity(sa, (IPersistentObject) oldValue, (Long) sa.session.getIdentifier(oldValue), false);
				}
				if(newValue != null){
					addChangedEntity(sa, (IPersistentObject) newValue, (Long) sa.session.getIdentifier(newValue), false);
				}
			}
		}else if(type instanceof CollectionType){
			// May need to reload the inverse references,i.e. many-to-many collections in the affected objects
			registerReferencedCollections(sa, oldValue, newValue, (CollectionType) type);
		}
	}
	private void registerReferencedCollections(SessionAttachment sa,Object oldValue,Object newValue,CollectionType collectionType){
		// TODO think this through
		// TODO optimise: only register changes,i.e. removed and added ones
		// TODO optimise: check for existence of inverse reference
		boolean x = false;
		if(x && collectionType.isDirty(oldValue, newValue, sa.session)){
			if(oldValue instanceof Map){
				Map<?,?> coll = (Map<?,?>) oldValue;
				for(Object referencedObject:coll.values()){
					if(referencedObject instanceof IPersistentObject){
						addChangedEntity(sa, (IPersistentObject) referencedObject, (Long) sa.session.getIdentifier(referencedObject), false);
					}
				}
			}else{
				Collection<?> coll = (Collection<?>) oldValue;
				for(Object referencedObject:coll){
					if(referencedObject instanceof IPersistentObject){
						addChangedEntity(sa, (IPersistentObject) referencedObject, (Long) sa.session.getIdentifier(referencedObject), false);
					}
				}
			}
		}
	}
	private void addChangedEntity(SessionAttachment sa,IPersistentObject entity,Long id,boolean dirtyEstablished){
		ChangedEntity ce = new ChangedEntity((Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entity), id);
		if(!sa.getChangedEntities().containsKey(ce)){
			if(entity instanceof HibernateProxy && ((HibernateProxy) entity).getHibernateLazyInitializer().isUninitialized()){
				ce.versionDidNotIncrement();
			}else{
				EntityEntry entry = sa.session.getPersistenceContext().getEntry(entity);
				if(dirtyEstablished){
					Number version = (Number) entry.getVersion();
					int versionInt = version.intValue();
					ce.objectVersion = versionInt;
				}else{
					EntityPersister persister = entry.getPersister();
					int[] dirty = persister.findDirty(persister.getPropertyValues(entity, EntityMode.POJO), entry.getLoadedState(), entity,
							sa.session);
					if(dirty == null || dirty.length == 0){
						ce.versionDidNotIncrement();
					}else{
						Number version = (Number) entry.getVersion();
						int versionInt = version.intValue();
						ce.objectVersion = versionInt;
					}
				}
				sa.addChangedEntity(ce);
			}
		}
	}
	@Override
	public void onPostInsert(PostInsertEvent event){
		maybeRegisterEventGenerator(event.getEntity(), event.getSession());
		Object[] state = event.getState();
		Type[] propertyTypes = event.getPersister().getPropertyTypes();
		SessionAttachment sa = lazyGetAttachment(event.getSession(), event.getEntity());
		for(int i = 0;i < propertyTypes.length;i++){
			registerReferencedObjects(sa, propertyTypes[i], null, state[i]);
		}
	}
	protected InternalHibernatePersistence getPersistence(EventSource session,Object o){
		return lazyGetAttachment(session, o).getPersistence();
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		maybeRegisterEventGenerator(event.getEntity(), event.getSession());
		try{
			Class<?> cls = event.getPersister().getMappedClass(EntityMode.POJO);
			while(cls != Object.class){
				Field declaredField = cls.getDeclaredField("persistence");
				if(declaredField != null){
					declaredField.setAccessible(true);
					declaredField.set(event.getEntity(), getPersistence(event.getSession(), event.getEntity()));
				}
				cls=cls.getSuperclass();
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
			Environment env = lazyGetOpaeumEnvironment(session, o);
			sessionAttachment = newSessionAttachment(session, env);
			sessionAttachments.put(session, sessionAttachment);
		}
		return sessionAttachment;
	}
	protected SessionAttachment newSessionAttachment(EventSource session,Environment env){
		return new SessionAttachment(session, env);
	}
	private Environment lazyGetOpaeumEnvironment(EventSource session,Object o){
		Environment environment = opaeumEnvironmentMap.get(session.getFactory());
		if(environment == null){
			NumlMetaInfo annotation = IntrospectionUtil.getOriginalClass(o.getClass()).getAnnotation(NumlMetaInfo.class);
			if(annotation != null && annotation.applicationIdentifier().length() > 0){
				try{
					Environment env = Environment.getEnvironment(annotation.applicationIdentifier());
					opaeumEnvironmentMap.put(session.getSessionFactory(), env);
				}catch(Exception e){
					new ExceptionAnalyser(e).throwRootCause();
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
		dispatchEventsAndRegisterChanges(event, source);
		postFlush(source);
	}
	private void dispatchEventsAndRegisterChanges(FlushEvent event,final EventSource source){
		SessionAttachment sa = sessionAttachments.get(source);
		if(sa != null){
			Set<EventOccurrence> dispatchEvents = saveEvents(source, sa.getEventGenerators(), sa);
			Set<String> cancelledEvents = deleteEvents(event, source);
			// NB!!! flush again ne!
			performFlush(event, source);
			scheduleEvents(sa.getEnvironment(), dispatchEvents);
			cancelEvents(sa.getEnvironment(), cancelledEvents);
			registerChangedEntities(sa);
			sa.onFlush();
		}
	}
	private void registerChangedEntities(SessionAttachment sa){
		Set<Entry<ChangedEntity,ChangedEntity>> entrySet = sa.getChangedEntities().entrySet();
		for(Entry<ChangedEntity,ChangedEntity> entry:entrySet){
			ChangedEntity oldChange = recentlyChangedEntities.get(entry.getKey());
			if(oldChange != null){
				if(entry.getKey().didVersionIncrement() == false){
					// SInce it was not updated in this flush get the latest object version
					entry.getKey().objectVersion = oldChange.objectVersion;
				}else if(oldChange.didVersionIncrement()){
					// Just to make sure we always have the latest valid version everywhere
					oldChange.objectVersion = entry.getKey().objectVersion = Math.max(entry.getKey().objectVersion, oldChange.objectVersion);
				}
			}else{
				recentlyChangedEntities.put(entry.getKey(), entry.getKey());
			}
		}
	}
	private void performFlush(FlushEvent event,final EventSource source){
		flushEverythingToExecutions(event);
		performExecutions(source);
		if(source.getFactory().getStatistics().isStatisticsEnabled()){
			source.getFactory().getStatisticsImplementor().flush();
		}
	}
	private void scheduleEvents(Environment env,Set<EventOccurrence> allEventOccurrences){
		for(EventOccurrence eo:allEventOccurrences){
			env.getEventService().scheduleEvent(eo);
		}
	}
	private Set<EventOccurrence> saveEvents(final EventSource source,Set<IEventGenerator> eventGenerators,SessionAttachment sa){
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
	private void cancelEvents(Environment env,Set<String> uuids){
		for(String uuid:uuids){
			env.getEventService().cancelEvent(uuid);
		}
	}
	private Set<String> deleteEvents(FlushEvent event,final EventSource source){
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
		onPersist(event);
	}
	@Override
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException{
		if(event.getEntity() instanceof IPersistentObject){
			EventSource session = event.getSession();
			Object[] propertyValues = event.getPropertyValues();
			updateAnyValuesBeforeFlush(session, event.getEntity(), propertyValues);
		}
	}
	private void updateAnyValuesBeforeFlush(EventSource session,Object entity,Object[] propertyValues){
		InternalHibernatePersistence pers = getPersistence(session, entity);
		for(Object object2:propertyValues){
			if(object2 instanceof IAnyValue){
				IAnyValue iv = (IAnyValue) object2;
				if(iv.getValue() != null){
					iv.updateBeforeFlush(pers);
				}
			}
		}
	}
	@Override
	public void onPersist(PersistEvent event) throws HibernateException{
		EventSource session = event.getSession();
		String entityName = event.getEntityName();
		EntityPersister p = session.getEntityPersister(entityName, event.getObject());
		Object[] propertyValues = p.getPropertyValues(event.getObject(), EntityMode.POJO);
		updateAnyValuesBeforeFlush(session, event.getObject(), propertyValues);
	}
	@Override
	public boolean onPreUpdate(PreUpdateEvent event){
		return false;
	}
}
