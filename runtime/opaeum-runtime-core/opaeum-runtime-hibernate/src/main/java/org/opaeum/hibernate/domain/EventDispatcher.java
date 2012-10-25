package org.opaeum.hibernate.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;

public class EventDispatcher extends AbstractFlushingEventListener implements PostLoadEventListener,FlushEventListener,PostInsertEventListener{
	private static final long serialVersionUID = -8583155822068850343L;
	static Map<EventSource,Set<IEventGenerator>> eventGeneratorMap = Collections.synchronizedMap(new WeakHashMap<EventSource,Set<IEventGenerator>>());
	@Override
	public void onPostInsert(PostInsertEvent event){
		maybeRegister(event.getEntity(), event.getSession());
	}
	@Override
	public void onPostLoad(PostLoadEvent event){
		// TODO resolve "Any's"
		maybeRegister(event.getEntity(), event.getSession());
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
		eventGeneratorMap.remove(source);
	}
	protected void dispatchEventsAndSaveProcesses(FlushEvent event,final EventSource source){
		Set<IEventGenerator> eventGenerators = eventGeneratorMap.get(event.getSession());
		if(eventGenerators != null){
			Set<EventOccurrence> dispatchEvents = saveEvents(event, source, eventGenerators);
			Set<String> cancelledEvents = deleteEvents(event, source);
			performFlush(event, source);
			scheduleEvents(dispatchEvents);
			cancelEvents(cancelledEvents);
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
				if(entry.getTarget() != null && !(entry.getTarget() instanceof Collection && ((Collection) entry.getTarget()).isEmpty())){
					EventOccurrence occurrence = new EventOccurrence(entry.getTarget(), entry.getHandler());
					occurrence.prepareForDispatch();
					source.persist(occurrence);
					allEventOccurrences.add(occurrence);
				}
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
			// log.error("Could not synchronize database state with session",
			// he);
			throw he;
		}finally{
			session.getPersistenceContext().setFlushing(false);// NUML
			// Modification
			// to assist
			// with auditing
			session.getJDBCContext().getConnectionManager().flushEnding();
		}
	}
}
