package org.opaeum.hibernate.domain;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.LockMode;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.event.spi.EventSource;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.opaeum.audit.AuditWorkUnit;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.event.ChangedEntity;

public class SessionAttachment{
	private Set<IEventGenerator> eventGenerators;
	private AuditWorkUnit auditWorkUnit;
	private EventSource session;
	private InternalHibernatePersistence persistence;
	private long startTime = System.currentTimeMillis();
	private String startingThread;
	private boolean hasBeenLogged = false;
	private Map<ChangedEntity,ChangedEntity> changedEntities = new HashMap<ChangedEntity,ChangedEntity>();
	private Environment environment;
	public SessionAttachment(EventSource session,Environment env){
		super();
		this.environment = env;
		this.session = session;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		new Exception().printStackTrace(pw);
		pw.flush();
		pw.close();
		startingThread = sw.toString();
	}
	public Collection<IPersistentObject> refreshStaleObjects(){
		Set<IPersistentObject> refreshed = new HashSet<IPersistentObject>();
		Map<ChangedEntity,Map.Entry<Object,EntityEntry>> changedEntities = findStaleObjects();
		Set<Entry<ChangedEntity,Entry<Object,EntityEntry>>> entrySet = changedEntities.entrySet();
		for(Entry<ChangedEntity,Entry<Object,EntityEntry>> entry:entrySet){
			Object entity = entry.getValue().getKey();
			session.refresh(entity);
			EntityEntry entityEntry = entry.getValue().getValue();
			EntityPersister persister = entityEntry.getPersister();
			int[] dirty = persister.findDirty(persister.getPropertyValues(entity), entityEntry.getLoadedState(), entity, session);
			if(dirty != null && dirty.length > 0){
				refreshed.add((IPersistentObject) entity);
			}
		}
		return refreshed;
	}
	public boolean containsStaleObjects(){
		Map<Object,EntityEntry> entries = session.getPersistenceContext().getEntityEntries();
		synchronized(EventDispatcher.recentlyChangedEntities){
			for(Entry<Object,EntityEntry> entry:entries.entrySet()){
				Object entity = entry.getKey();
				if(!(entity instanceof HibernateProxy && ((HibernateProxy) entity).getHibernateLazyInitializer().isUninitialized())){
					Long id = (Long) entry.getValue().getId();
					Class<? extends IPersistentObject> originalClass = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entry
							.getKey());
					ChangedEntity changedEntity = EventDispatcher.recentlyChangedEntities.get(new ChangedEntity(originalClass, id));
					if(changedEntity != null){
						return true;
					}
				}
			}
		}
		return false;
	}
	protected Map<ChangedEntity,Map.Entry<Object,EntityEntry>> findStaleObjects(){
		Map<Object,EntityEntry> entries = session.getPersistenceContext().getEntityEntries();
		Map<ChangedEntity,Map.Entry<Object,EntityEntry>> changedEntities = new HashMap<ChangedEntity,Map.Entry<Object,EntityEntry>>();
		synchronized(EventDispatcher.recentlyChangedEntities){
			for(Entry<Object,EntityEntry> entry:entries.entrySet()){
				Object entity = entry.getKey();
				if(!(entity instanceof HibernateProxy && ((HibernateProxy) entity).getHibernateLazyInitializer().isUninitialized())){
					Long id = (Long) entry.getValue().getId();
					Class<? extends IPersistentObject> originalClass = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entry
							.getKey());
					ChangedEntity changedEntity = EventDispatcher.recentlyChangedEntities.get(new ChangedEntity(originalClass, id));
					if(changedEntity != null){
						changedEntities.put(changedEntity, entry);
					}
				}
			}
		}
		return changedEntities;
	}
	public void upgradeStaleObjects(){
		Map<ChangedEntity,Map.Entry<Object,EntityEntry>> changedEntities = findStaleObjects();
		Set<Entry<ChangedEntity,Entry<Object,EntityEntry>>> entrySet = changedEntities.entrySet();
		for(Entry<ChangedEntity,Entry<Object,EntityEntry>> entry:entrySet){
			int objectVersion = entry.getKey().objectVersion;
			if(objectVersion != Integer.MAX_VALUE){
				// Collections may have changed, but not the object itself
				Object entity = entry.getValue().getKey();
				entry.getValue().getValue().forceLocked(entity, objectVersion);
				entry.getValue().getValue().setLockMode(LockMode.NONE);
			}
		}
	}
	public void addEventGenerator(IEventGenerator entity){
		if(eventGenerators == null){
			eventGenerators = Collections.newSetFromMap(new WeakHashMap<IEventGenerator,Boolean>());
		}
		eventGenerators.add(entity);
	}
	public Set<IEventGenerator> getEventGenerators(){
		return eventGenerators == null ? Collections.<IEventGenerator>emptySet() : eventGenerators;
	}
	public AuditWorkUnit getAuditWorkUnit(){
		if(auditWorkUnit == null){
			auditWorkUnit = new AuditWorkUnit(session);
		}
		return auditWorkUnit;
	}
	public InternalHibernatePersistence getPersistence(){
		if(persistence == null){
			persistence = new InternalHibernatePersistence(session, environment);
		}
		return persistence;
	}
	public void clearAuditWorkUnit(){
		auditWorkUnit = null;
	}
	public void cleanUp(){
		auditWorkUnit = null;
		if(eventGenerators != null){
			eventGenerators.clear();
		}
		if(persistence != null){
			persistence.cleanUp();
		}
		eventGenerators = null;
		// TODO Auto-generated method stub
	}
	public long getStartTime(){
		return startTime;
	}
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
	public String getStartingThread(){
		return startingThread;
	}
	public void setStartingThread(String startingThread){
		this.startingThread = startingThread;
	}
	public void logged(){
		hasBeenLogged = true;
	}
	public boolean hasBeenLogged(){
		return hasBeenLogged;
	}
	public ChangedEntity addChangedEntity(Class<? extends IPersistentObject> entity,Long id){
		ChangedEntity changedEntity = new ChangedEntity(entity, id);
		ChangedEntity foundChangedEntity = this.changedEntities.get(changedEntity);
		if(foundChangedEntity == null){
			foundChangedEntity = changedEntity;
			changedEntities.put(changedEntity, changedEntity);
		}
		return foundChangedEntity;
	}
	public Map<ChangedEntity,ChangedEntity> getChangedEntities(){
		return changedEntities;
	}
	public void onFlush(){
		persistence.flushOtherPersistence();
		this.changedEntities.clear();
	}
	public Environment getEnvironment(){
		return environment;
	}
}
