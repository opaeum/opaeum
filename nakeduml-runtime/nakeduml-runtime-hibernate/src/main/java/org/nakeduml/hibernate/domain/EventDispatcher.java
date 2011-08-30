package org.nakeduml.hibernate.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEntityEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.event.IEventHandler;

public class EventDispatcher extends AbstractFlushingEventListener implements PostLoadEventListener,FlushEventListener,FlushEntityEventListener,PreInsertEventListener{
	static Map<EventSource,Set<IEventGenerator>> eventSourceMap = Collections.synchronizedMap(new WeakHashMap<EventSource,Set<IEventGenerator>>());
	@Override
	public void onPostLoad(PostLoadEvent event){
		if(event.getEntity() instanceof IEventGenerator){
			addEventSource(event.getSession(), (IEventGenerator) event.getEntity());
		}
	}
	@Override
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException{
		if(event.getEntity() instanceof IEventGenerator){
			addEventSource(event.getSession(), (IEventGenerator) event.getEntity());
		}
	}
	private void addEventSource(EventSource session,IEventGenerator entity){
		Set<IEventGenerator> set = eventSourceMap.get(session);
		if(set == null){
			set = new HashSet<IEventGenerator>();
			eventSourceMap.put(session, set);
		}
		set.add(entity);
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		final EventSource source = event.getSession();
		if(source.getPersistenceContext().hasNonReadOnlyEntities()){
			// Generate Ids, perform flush events
			performFlush(event, source);
		}
		//NB!! entities may not have changed, but events may have been generated
		dispatchEvents(event, source);
		postFlush(source);
	}
	protected void dispatchEvents(FlushEvent event,final EventSource source){
		Set<IEventGenerator> eventGenerators = eventSourceMap.get(event.getSession());
		if(eventGenerators != null){
			Set<EventOccurrence> dispatchEvents = saveEvents(event, source, eventGenerators);
			Set<String> cancelledEvents = deleteEvents(event, source);
			performFlush(event, source);
			postFlush(source);
			scheduleEvents(dispatchEvents);
			cancelEvents(cancelledEvents);
			eventSourceMap.remove(event.getSession());
		}else{
			performFlush(event, source);
			postFlush(source);
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
			for(Entry<Object,IEventHandler> entry:eg.getOutgoingEvents().entrySet()){
				EventOccurrence occurrence = new EventOccurrence(entry.getKey(), entry.getValue());
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
		Set<IEventGenerator> eventGenerators = eventSourceMap.get(event.getSession());
		Set<String> allCancellations = new HashSet<String>();
		Query delete = source.createQuery("delete from EventOccurrence where uuid=:uuid");
		for(IEventGenerator eg:eventGenerators){
			for(Entry<Object,String> entry:eg.getCancelledEvents().entrySet()){
				delete.setString("uuid", EventOccurrence.uuid(entry.getKey(), entry.getValue()));
				delete.executeUpdate();
				allCancellations.add(entry.getValue());
			}
			eg.getCancelledEvents().clear();
		}
		return allCancellations;
	}
	protected void performExecutions(EventSource session) throws HibernateException{
		session.getPersistenceContext().setFlushing(true);
		try{
			session.getJDBCContext().getConnectionManager().flushBeginning();
			// we need to lock the collection caches before
			// executing entity inserts/updates in order to
			// account for bidi associations
			session.getActionQueue().prepareActions();
			session.getActionQueue().executeActions();
		}catch(HibernateException he){
			// log.error("Could not synchronize database state with session", he);
			throw he;
		}finally{
			session.getPersistenceContext().setFlushing(false);// NUML Modification to assist with auditing
			session.getJDBCContext().getConnectionManager().flushEnding();
		}
	}
	@Override
	public boolean onPreInsert(PreInsertEvent event){
		if(event.getEntity() instanceof IEventGenerator){
			addEventSource(event.getSession(), (IEventGenerator) event.getEntity());
		}
		return false;
	}
}
