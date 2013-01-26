package org.opaeum.hibernate.domain;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.event.internal.AbstractFlushingEventListener;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDispatcher extends AbstractFlushingEventListener implements
		PostLoadEventListener, FlushEventListener, PostInsertEventListener {
	private static final long serialVersionUID = -8583155822068850343L;
	public static Map<EventSource, SessionAttachment> sessionAttachments = Collections
			.synchronizedMap(new HashMap<EventSource, SessionAttachment>());
	protected static Logger logger =LoggerFactory.getLogger(EventDispatcher.class); 
	static {
		// HAck!! No event to trap session closure
		new Thread(EventDispatcher.class.getName()+"::Grim reaper thread") {
			@Override
			public void run() {
				while (true) {
					try {
						sleep(10000);
						EventSource[] array = sessionAttachments.keySet()
								.toArray(
										new EventSource[sessionAttachments
												.size()]);
						for (EventSource eventSource : array) {
							if (eventSource.isClosed()) {
								SessionAttachment att = sessionAttachments.remove(eventSource);
								att.cleanUp();
							} else {
								SessionAttachment sessionAttachment = sessionAttachments.get(eventSource);
								if(sessionAttachment.getStartTime()<System.currentTimeMillis()-(60000*30) && !sessionAttachment.hasBeenLogged()){
									sessionAttachment.logged();
									logger.error("Potential session leak! Session has been open for " +((System.currentTimeMillis()-sessionAttachment.getStartTime())/60000)+  " minutes");
									logger.error("Session opened from " +sessionAttachment.getStartingThread());
									
								}
							}
						}
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		maybeRegister(event.getEntity(), event.getSession());
	}
	protected AbstractPersistence getPersistence(EventSource session){
		return lazyGetAttachment(session).getPersistence();
	}
	@Override
	public void onPostLoad(PostLoadEvent event) {
		maybeRegister(event.getEntity(), event.getSession());
		try{
			Field declaredField = event.getPersister().getMappedClass().getDeclaredField("persistence");
			if(declaredField != null){
				declaredField.setAccessible(true);
				declaredField.set(event.getEntity(), getPersistence(event.getSession()));
			}
		}catch(NoSuchFieldException e){
		}catch(RuntimeException re){
			throw re;
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}

	private void maybeRegister(Object entity, EventSource session) {
		if (entity instanceof IEventGenerator) {
			SessionAttachment sessionAttachment = lazyGetAttachment(session);
			sessionAttachment.addEventGenerator((IEventGenerator) entity);
		}
	}

	protected SessionAttachment lazyGetAttachment(EventSource session) {
		SessionAttachment sessionAttachment = sessionAttachments
				.get(session);
		if (sessionAttachment == null) {
			sessionAttachment = new SessionAttachment(session);
			sessionAttachments.put(session, sessionAttachment);
		}
		return sessionAttachment;
	}

	@Override
	public void onFlush(FlushEvent event) throws HibernateException {
		final EventSource source = event.getSession();
		if (source.getPersistenceContext().hasNonReadOnlyEntities()) {
			// Generate Ids, perform flush events, register newly inserted
			// objects
			performFlush(event, source);
		}
		// NB!! entities may not have changed, but events may have been
		// generated
		dispatchEventsAndSaveProcesses(event, source);
		postFlush(source);
	}

	protected void cleanup(final EventSource source) {
		sessionAttachments.remove(source);
	}

	protected void dispatchEventsAndSaveProcesses(FlushEvent event,
			final EventSource source) {
		SessionAttachment attachments = sessionAttachments.get(event
				.getSession());
		if (attachments != null) {
			Set<EventOccurrence> dispatchEvents = saveEvents(event, source,
					attachments.getEventGenerators());
			Set<String> cancelledEvents = deleteEvents(event, source);
			performFlush(event, source);
			scheduleEvents(dispatchEvents);
			cancelEvents(cancelledEvents);
		}
	}

	protected void performFlush(FlushEvent event, final EventSource source) {
		flushEverythingToExecutions(event);
		performExecutions(source);
		if (source.getFactory().getStatistics().isStatisticsEnabled()) {
			source.getFactory().getStatisticsImplementor().flush();
		}
	}

	protected void scheduleEvents(Set<EventOccurrence> allEventOccurrences) {
		for (EventOccurrence eo : allEventOccurrences) {
			Environment.getInstance().getEventService().scheduleEvent(eo);
		}
	}

	protected Set<EventOccurrence> saveEvents(FlushEvent event,
			final EventSource source, Set<IEventGenerator> eventGenerators) {
		Set<EventOccurrence> allEventOccurrences = new HashSet<EventOccurrence>();
		for (IEventGenerator eg : eventGenerators) {
			for (OutgoingEvent entry : eg.getOutgoingEvents()) {
				if (entry.getTarget() != null
						&& !(entry.getTarget() instanceof Collection && ((Collection) entry
								.getTarget()).isEmpty())) {
					EventOccurrence occurrence = new EventOccurrence(
							entry.getTarget(), entry.getHandler());
					occurrence.prepareForDispatch();
					source.persist(occurrence);
					allEventOccurrences.add(occurrence);
				}
			}
			eg.getOutgoingEvents().clear();
		}
		return allEventOccurrences;
	}

	protected void cancelEvents(Set<String> uuids) {
		for (String uuid : uuids) {
			Environment.getInstance().getEventService().cancelEvent(uuid);
		}
	}

	protected Set<String> deleteEvents(FlushEvent event,
			final EventSource source) {
		SessionAttachment attachment = sessionAttachments
				.get(event.getSession());
		if (attachment != null) {
			Set<IEventGenerator> eventGenerators = attachment
					.getEventGenerators();
			Set<String> allCancellations = new HashSet<String>();
			Query delete = source
					.createQuery("delete from EventOccurrence where uuid=:uuid");
			for (IEventGenerator eg : eventGenerators) {
				for (CancelledEvent entry : eg.getCancelledEvents()) {
					String eventOccurrenceUuid = EventOccurrence.uuid(
							entry.getTarget(), entry.getEventId());
					delete.setString("uuid", eventOccurrenceUuid);
					delete.executeUpdate();
					allCancellations.add(eventOccurrenceUuid);
				}
				eg.getCancelledEvents().clear();
			}
			return allCancellations;
		} else {
			return Collections.emptySet();
		}
	}

//	protected void performExecutions(EventSource session)
//			throws HibernateException {
//		session.getPersistenceContext().setFlushing(true);
//		try {
//			session.getgetJDBCContext().getConnectionManager().flushBeginning();
//			// we need to lock the collection caches before
//			// executing entity inserts/updates in order to
//			// account for bidi associations
//			session.getActionQueue().prepareActions();
//			session.getActionQueue().executeActions();
//		} catch (HibernateException he) {
//			// log.error("Could not synchronize database state with session",
//			// he);
//			throw he;
//		} finally {
//			session.getPersistenceContext().setFlushing(false);// NUML
//			// Modification
//			// to assist
//			// with auditing
//			session.getJDBCContext().getConnectionManager().flushEnding();
//		}
//	}
}
