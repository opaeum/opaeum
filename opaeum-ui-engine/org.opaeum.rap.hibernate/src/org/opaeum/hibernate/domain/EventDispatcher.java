package org.opaeum.hibernate.domain;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.event.internal.DefaultFlushEventListener;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class EventDispatcher extends DefaultFlushEventListener implements PostLoadEventListener,FlushEventListener,PostInsertEventListener,
		PersistEventListener{
	private static final long serialVersionUID = -8583155822068850343L;
	static Map<EventSource,Set<IEventGenerator>> eventGeneratorMap = Collections
			.synchronizedMap(new WeakHashMap<EventSource,Set<IEventGenerator>>());
	static Map<EventSource,Set<IProcessObject>> processObjectMap = Collections
			.synchronizedMap(new WeakHashMap<EventSource,Set<IProcessObject>>());
	static Map<EventSource,AbstractHibernatePersistence> persistenceMap = Collections
			.synchronizedMap(new WeakHashMap<EventSource,AbstractHibernatePersistence>());
	private AbstractPersistence getPersistence(EventSource session){
		AbstractHibernatePersistence persistence = persistenceMap.get(session);
		if(persistence == null){
			persistence = new AbstractHibernatePersistence(session){
			};
			persistenceMap.put(session, persistence);
		}
		return persistence;
	}
	@Override
	public void onPostInsert(PostInsertEvent event){
		EventSource session = event.getSession();
		maybeRegister(event.getEntity(), session);
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		maybeRegister(event.getEntity(), event.getSession());
		try{
			Field declaredField = event.getPersister().getMappedClass().getDeclaredField("persistence");
			if(declaredField != null){
				declaredField.setAccessible(true);
				declaredField.set(event.getEntity(), getPersistence(event.getSession()));
			}
		}catch(RuntimeException re){
			throw re;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private void maybeRegister(Object entity,EventSource session){
		if(entity instanceof IEventGenerator){
			Set<IEventGenerator> set = eventGeneratorMap.get(session);
			if(set == null){
				set = new HashSet<IEventGenerator>();
				eventGeneratorMap.put(session, set);
			}
			set.add((IEventGenerator) entity);
		}
		if(entity instanceof IProcessObject){
			Set<IProcessObject> set = processObjectMap.get(session);
			if(set == null){
				set = new HashSet<IProcessObject>();
				processObjectMap.put(session, set);
			}
			set.add((IProcessObject) entity);
		}
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		final EventSource source = event.getSession();
		super.onFlush(event);
		// NB!! entities may not have changed, but events may have been
		// generated
		dispatchEventsAndSaveProcesses(event, source);
		postFlush(source);
	}
	protected void dispatchEventsAndSaveProcesses(FlushEvent event,final EventSource source){
		Set<IEventGenerator> eventGenerators = eventGeneratorMap.get(event.getSession());
		Set<IProcessObject> processes = processObjectMap.get(event.getSession());
		boolean dirtyProcessFound = false;
		if(processes != null){
			for(IProcessObject o:processes){
				if(o.isProcessDirty()){
					ProcessInstanceInfo pii = (ProcessInstanceInfo) source.get(ProcessInstanceInfo.class, o.getProcessInstanceId());
					pii.update();
					dirtyProcessFound = true;
				}
			}
		}
		if(eventGenerators != null){
			Set<EventOccurrence> dispatchEvents = saveEvents(event, source, eventGenerators);
			Set<String> cancelledEvents = deleteEvents(event, source);
			performFlush(event, source);
			scheduleEvents(dispatchEvents);
			cancelEvents(cancelledEvents);
		}else if(dirtyProcessFound){
			performFlush(event, source);
		}
	}
	protected void performFlush(FlushEvent event,final EventSource source){
		flushEverythingToExecutions(event);
		performExecutions(source);
		if(source.getFactory().getStatistics().isStatisticsEnabled()){
			source.getFactory().getStatisticsImplementor().flush();
		}
	}
	protected void scheduleEvents(Set<EventOccurrence> allEventOccurrences){
		for(EventOccurrence eo:allEventOccurrences){
			Environment.getInstance().getEventService().scheduleEvent(eo);
		}
	}
	protected Set<EventOccurrence> saveEvents(FlushEvent event,final EventSource source,Set<IEventGenerator> eventGenerators){
		Set<EventOccurrence> allEventOccurrences = new HashSet<EventOccurrence>();
		for(IEventGenerator eg:eventGenerators){
			for(OutgoingEvent entry:eg.getOutgoingEvents()){
				EventOccurrence occurrence = new EventOccurrence(entry.getTarget(), entry.getHandler());
				occurrence.prepareForDispatch();
				source.persist(occurrence);
				allEventOccurrences.add(occurrence);
			}
			eg.getOutgoingEvents().clear();
		}
		return allEventOccurrences;
	}
	protected void cancelEvents(Set<String> uuids){
		for(String uuid:uuids){
			Environment.getInstance().getEventService().cancelEvent(uuid);
		}
	}
	protected Set<String> deleteEvents(FlushEvent event,final EventSource source){
		Set<IEventGenerator> eventGenerators = eventGeneratorMap.get(event.getSession());
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
	}
	@Override
	public void onPersist(PersistEvent event) throws HibernateException{
		EntityPersister p = event.getSession().getEntityPersister(event.getEntityName(), event.getObject());
		Object[] propertyValues = p.getPropertyValues(event.getObject());
		for(Object object2:propertyValues){
			if(object2 instanceof InterfaceValue){
				InterfaceValue iv = (InterfaceValue) object2;
				if(iv.hasValue() && iv.getIdentifier() == null){
					IPersistentObject value = iv.getValue(getPersistence(event.getSession()));
					if(iv instanceof CascadingInterfaceValue){
						event.getSession().persist(value);
					}
					iv.setValue(value);// Populate the id
				}
			}
		}
	}
	@Override
	public void onPersist(PersistEvent event,Map createdAlready) throws HibernateException{
		onPersist(event);
	}
}
