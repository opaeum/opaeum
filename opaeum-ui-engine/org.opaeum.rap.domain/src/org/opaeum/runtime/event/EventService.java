package org.opaeum.runtime.event;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.opaeum.runtime.domain.ExceptionAnalyser;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventService{
	private final class EventOccurenceRunner implements Runnable{
		private AbstractEventOccurrence event;
		private Logger logger = LoggerFactory.getLogger(getClass());
		private EventOccurenceRunner(AbstractEventOccurrence event){
			this.event = event;
		}
		@Override
		public void run(){
			try{
				Environment.getInstance().startRequestContext();
				UmtPersistence umtPersistence = Environment.getInstance().getComponent(UmtPersistence.class);
				handleInTryBlock(umtPersistence);
				Environment.getInstance().endRequestContext();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		private void handleInTryBlock(UmtPersistence umtPersistence){
			try{
				umtPersistence.beginTransaction();
				event = umtPersistence.find(IntrospectionUtil.getOriginalClass(event), event.getId());
				event.prepareForDelivery(umtPersistence);
				actions.remove(event.getUuid());
				if(event.maybeTrigger()){
					umtPersistence.remove(event);
				}else{
					ScheduledThreadPoolExecutor queue = getQueue(event.getEventHandler().getQueueName(), event.getEventHandler()
							.getConsumerPoolSize());
					long delay = event.getEventHandler().scheduleNextOccurrence().getTime() - System.currentTimeMillis();
					ScheduledFuture<?> schedule = queue.schedule(new EventOccurenceRunner(event), delay, TimeUnit.MILLISECONDS);
					actions.put(event.getUuid(), schedule);
				}
				umtPersistence.commitTransaction();
			}catch(Throwable e){
				try{
					umtPersistence.rollbackTransaction();
				}catch(Exception e2){
				}
				event.incrementRetryCount();
				ExceptionAnalyser ea = new ExceptionAnalyser(e);
				if(ea.isResourceAllocationTimeout()){
					// NB!!! it seems that when a Resource Timeout occurs in
					// JBoss the bean's environment becomes unstable and ejb
					// references can't
					// acquire the necessary locks:
					// org.opaeum.environment.adaptor.AbstractEventMdb]
					// (Thread-6
					// (group:HornetQ-client-global-threads-1274855145)) null:
					// java.lang.InterruptedException
					// at
					// java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireSharedNanos(AbstractQueuedSynchronizer.java:1302)
					// [:1.6.0_24]
					// Redeliver immediately and hope for the best
					getQueue(event.getEventHandler().getQueueName(), event.getEventHandler().getConsumerPoolSize()).schedule(this, 0,
							TimeUnit.MILLISECONDS);
				}else if(ea.isStaleStateException() || ea.isDeadlockException()){
					if(event.getRetryCount() < 20){
						logger.debug("Retrying {0} because of {1}", event.getDescription(), ea.getRootCause().toString());
						getQueue(event.getEventHandler().getQueueName(), event.getEventHandler().getConsumerPoolSize()).schedule(this, 1000,
								TimeUnit.MILLISECONDS);
					}else{
						Throwable rootCause = ea.getRootCause();
						if(rootCause instanceof SQLException && ea.getStackTrace(rootCause).contains("Call getNextException to see the cause")){
							logger.debug("Unresolved exception found {0}", rootCause.toString());
						}
						logger.debug("RetryCount exceeded for signal {0}", event.getDescription());
						ea.throwRootCause();
					}
				}else{
					if(ea.stringOccurs("getNodeInstancesRecursively") && ea.stringOccurs("java.lang.NullPointerException")){
						// swallow this
						logger.debug("Process had already completed on delivery of {0}", event.getDescription());
					}else{
						Throwable rootCause = ea.getRootCause();
						logger.debug("Exception {0} can not be retried", rootCause);
						event.markDead();
						ea.throwRootCause();
					}
				}
			}
		}
	}
	Map<String,ScheduledThreadPoolExecutor> queues = Collections.synchronizedMap(new HashMap<String,ScheduledThreadPoolExecutor>());
	Map<String,ScheduledFuture<?>> actions = Collections.synchronizedMap(new HashMap<String,ScheduledFuture<?>>());
	CmtPersistence persistence;
	public synchronized void startUp(Collection<AbstractEventOccurrence> events){
		for(final AbstractEventOccurrence event:events){
			scheduleEvent(event);
		}
	}
	public synchronized void cancelEvent(String uuid){
		actions.get(uuid).cancel(true);
	}
	public synchronized void scheduleEvents(final Collection<? extends AbstractEventOccurrence> events){
		for(AbstractEventOccurrence event:events){
			scheduleEvent(event);
		}
	}
	private void scheduleEvent(AbstractEventOccurrence event){
		ScheduledThreadPoolExecutor queue = getQueue(event.getEventHandler().getQueueName(), event.getEventHandler().getConsumerPoolSize());
		long delay = event.getEventHandler().getFirstOccurrenceScheduledFor().getTime() - System.currentTimeMillis();
		ScheduledFuture<?> schedule = queue.schedule(new EventOccurenceRunner(event), Math.max(delay, 10), TimeUnit.MILLISECONDS);
		actions.put(event.getUuid(), schedule);
	}
	private ScheduledThreadPoolExecutor getQueue(String name,int poolSize){
		ScheduledThreadPoolExecutor result = queues.get(name);
		if(result == null){
			result = new ScheduledThreadPoolExecutor(poolSize);
			result.setMaximumPoolSize(poolSize);
			queues.put(name, result);
		}
		return result;
	}
}
