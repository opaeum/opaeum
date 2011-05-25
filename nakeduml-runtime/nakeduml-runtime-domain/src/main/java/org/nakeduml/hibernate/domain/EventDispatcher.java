package org.nakeduml.hibernate.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEntityEventListener;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.def.AbstractFlushingEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.IMessageSender;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.event.AbstractNakedUmlEvent;
import org.nakeduml.event.ChangeEvent;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractEventSource;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

public class EventDispatcher extends AbstractFlushingEventListener implements PostLoadEventListener,FlushEventListener,FlushEntityEventListener{
	static Map<EventSource,Set<AbstractEventSource>> eventSourceMap = new WeakHashMap<EventSource,Set<AbstractEventSource>>();
	@Override
	public void onPostLoad(PostLoadEvent event){
		if(event.getEntity() instanceof AbstractEventSource){
			addEventSource(event.getSession(), (AbstractEventSource) event.getEntity());
		}
	}
	@Override
	public void onFlushEntity(FlushEntityEvent event) throws HibernateException{
		if(event.getEntity() instanceof AbstractEventSource){
			addEventSource(event.getSession(), (AbstractEventSource) event.getEntity());
		}
	}
	private void addEventSource(EventSource session,AbstractEventSource entity){
		Set<AbstractEventSource> set = eventSourceMap.get(session);
		if(set == null){
			set = new HashSet<AbstractEventSource>();
			eventSourceMap.put(session, set);
		}
		set.add(entity);
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		doFlush(event);// Generate Ids, perform flush events
		Set<AbstractEventSource> eventSources = EventDispatcher.eventSourceMap.get(event.getSession());
		if(eventSources != null){
			eventSources.remove(event.getSession());
			sendEvents(eventSources);
		}
	}
	private void doFlush(FlushEvent event) throws HibernateException{
		final EventSource source = event.getSession();
		if(source.getPersistenceContext().hasNonReadOnlyEntities()){
			flushEverythingToExecutions(event);
			performExecutions(source);
			postFlush(source);
			if(source.getFactory().getStatistics().isStatisticsEnabled()){
				source.getFactory().getStatisticsImplementor().flush();
			}
		}
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
	public static void sendEvents(Collection<AbstractEventSource> eventSources){
		IMessageSender sender = Environment.getInstance().getComponent(IMessageSender.class);
		Set<AbstractNakedUmlEvent> requestedEvents = new HashSet<AbstractNakedUmlEvent>();
		Set<SignalToDispatch> signalsToEntities = new HashSet<SignalToDispatch>();
		Set<SignalToDispatch> signalsToHelpers = new HashSet<SignalToDispatch>();
		for(AbstractEventSource abstractEventSource:eventSources){
			Set<Object> outgoingEvents = abstractEventSource.getOutgoingEvents();
			for(Object object:outgoingEvents){
				if(object instanceof AbstractNakedUmlEvent){
					requestedEvents.add((AbstractNakedUmlEvent) object);
				}else if(object instanceof SignalToDispatch){
					if(((SignalToDispatch) object).getTarget() instanceof AbstractEntity){
						signalsToEntities.add((SignalToDispatch) object);
					}else{
						signalsToHelpers.add((SignalToDispatch) object);
					}
				}
			}
		}
		sender.sendObjectsToQueue(requestedEvents, "queue/EventRequestQueue");
		sender.sendObjectsToQueue(signalsToEntities, "queue/EntitySignalQueue");
		sender.sendObjectsToQueue(signalsToHelpers, "queue/HelperSignalQueue");
	}
	protected AbstractEntity getAbstractEntity(FlushEvent event,ChangeEvent changeEvent){
		try{
			return (AbstractEntity) event.getSession().load(Class.forName(changeEvent.getEventSourceClassName()), changeEvent.getEventSourceId());
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}
}
