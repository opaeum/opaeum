package org.nakeduml.hibernate.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.FlushEventListener;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.IMessageSender;
import org.nakeduml.environment.SignalToDispatch;
import org.nakeduml.event.AbstractNakedUmlEvent;
import org.nakeduml.event.ChangeEvent;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractEventSource;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

public class EventDispatcher extends DefaultFlushEventListener implements PostLoadEventListener,FlushEventListener{
	static Map<EventSource,Set<AbstractEventSource>> eventSourceMap = new WeakHashMap<EventSource,Set<AbstractEventSource>>();
	@Override
	public void onPostLoad(PostLoadEvent event){
		if(event.getEntity() instanceof AbstractEventSource){
			Set<AbstractEventSource> set = eventSourceMap.get(event.getSession());
			if(set == null){
				set = new HashSet<AbstractEventSource>();
				eventSourceMap.put(event.getSession(), set);
			}
			set.add((AbstractEventSource) event.getEntity());
		}
	}
	@Override
	public void onFlush(FlushEvent event) throws HibernateException{
		super.onFlush(event);// Generate Ids
		Set<AbstractEventSource> eventSources = EventDispatcher.eventSourceMap.get(event.getSession());
		if(eventSources != null){
			eventSources.remove(event.getSession());
			sendEvents(eventSources);
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
