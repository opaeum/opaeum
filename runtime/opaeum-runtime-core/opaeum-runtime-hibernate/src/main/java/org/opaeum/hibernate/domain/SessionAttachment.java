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

import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.engine.EntityEntry;
import org.hibernate.event.EventSource;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.Type;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.event.ChangedEntity;

public class SessionAttachment{
	private class ChangedEntityEntry{
		ChangedEntity changedEntity;
		EntityEntry entry;
		private IPersistentObject object;
		public ChangedEntityEntry(ChangedEntity changedEntity,IPersistentObject object,EntityEntry entry){
			super();
			this.changedEntity = changedEntity;
			this.entry = entry;
			this.object = object;
		}
	}
	private Set<IEventGenerator> eventGenerators;
	protected EventSource session;
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
	/**
	 * 
	 * @return
	 */
	public Map<ChangedEntity,IPersistentObject> synchronizeWithDatabaseAndFindConflicts(){
		Map<ChangedEntity,IPersistentObject> conflicts = new HashMap<ChangedEntity,IPersistentObject>();
		Set<ChangedEntityEntry> changedEntities = findStaleObjects();
		for(ChangedEntityEntry cee:changedEntities){
			if(cee.changedEntity.didVersionIncrement() && cee.object.getObjectVersion() < cee.changedEntity.objectVersion && isDirty(cee.object, cee.entry)){
				conflicts.put(cee.changedEntity, cee.object);
			}else{
				session.refresh(cee.object);
			}
		}
		return conflicts;
	}
	private boolean isInitialised(Object entity){
		return entity instanceof IPersistentObject
				&& !(entity instanceof HibernateProxy && ((HibernateProxy) entity).getHibernateLazyInitializer().isUninitialized());
	}
	private Set<ChangedEntityEntry> findStaleObjects(){
		Map<Object,EntityEntry> entries = session.getPersistenceContext().getEntityEntries();
		Set<ChangedEntityEntry> changedEntities = new HashSet<SessionAttachment.ChangedEntityEntry>();
		for(Entry<Object,EntityEntry> entry:entries.entrySet()){
			Object entity = entry.getKey();
			if(isInitialised(entity)){
				Long id = (Long) entry.getValue().getId();
				Class<? extends IPersistentObject> originalClass = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entry
						.getKey());
				ChangedEntity changedEntity = EventDispatcher.recentlyChangedEntities.get(new ChangedEntity(originalClass, id));
				if(changedEntity != null){
					changedEntities.add(new ChangedEntityEntry(changedEntity, (IPersistentObject) entity, entry.getValue()));
				}
			}
		}
		return changedEntities;
	}
	public void overwriteConflicts(Map<ChangedEntity,IPersistentObject> staleObjects){
		for(Entry<ChangedEntity,IPersistentObject> mapEntry:staleObjects.entrySet()){
			EntityEntry entityEntry = session.getPersistenceContext().getEntry(mapEntry.getValue());
			entityEntry.forceLocked(mapEntry.getValue(), mapEntry.getKey().objectVersion);
			entityEntry.setLockMode(LockMode.NONE);
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
	public InternalHibernatePersistence getPersistence(){
		if(persistence == null){
			persistence = new InternalHibernatePersistence(session, environment);
		}
		return persistence;
	}
	public void cleanUp(){
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
	public ChangedEntity addChangedEntity(ChangedEntity changedEntity ){
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
	private boolean isDirty(IPersistentObject object,EntityEntry entry2){
		EntityPersister persister = entry2.getPersister();
		Type[] propertyTypes = persister.getPropertyTypes();
		Object[] currentState = persister.getPropertyValues(object, EntityMode.POJO);
		Object[] loadedState = entry2.getLoadedState();
		for(int i = 0;i < propertyTypes.length;i++){
			Type type = propertyTypes[i];
			if(type.isDirty(loadedState[i], currentState[i], session)){
				return true;
			}
		}
		return false;
	}
	public Collection<IPersistentObject> reloadStaleObjects(){
		Set<ChangedEntityEntry> findStaleObjects = findStaleObjects();
		Set<IPersistentObject> result=new HashSet<IPersistentObject>();
		for(ChangedEntityEntry cee:findStaleObjects){
			session.refresh(cee.object);
			result.add(cee.object);
		}
		return result;
	}
}
