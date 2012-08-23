package org.opaeum.runtime.event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.persistence.AbstractPersistence;

public abstract class AbstractEventOccurrence implements IPersistentObject,Serializable{
	private static final long serialVersionUID = -7821427380072881224L;
	private IEventHandler handler;
	private Object eventTarget;
	public AbstractEventOccurrence(Object target,IEventHandler handler){
		this.handler = handler;
		this.eventTarget = target;
	}
	public AbstractEventOccurrence(){
	}
	public abstract Date getFirstOccurrenceScheduledFor();
	protected abstract String getEventTargetClassId();
	public abstract Long getId();
	public abstract EventOccurrenceStatus getStatus();
	public abstract int getRetryCount();
	public abstract void incrementRetryCount();
	protected abstract Value getTargetValue();
	protected abstract Collection<PropertyValue> getPropertyValues();
	public abstract String getHandlerUuid();
	public abstract void setId(Long id);
	protected abstract void setTargetValue(Value valueOf);
	protected abstract void setPropertyValues(Collection<PropertyValue> collection);
	public abstract Date getNextOccurrenceScheduledFor();
	public abstract void setNextOccurrenceScheduledFor(Date nextOccurrenceScheduledFor);
	public String getUuid(){
		return getHandlerUuid() + "$" + getEventTargetUuid() + "$" + getEventTargetClassId();
	}
	public abstract String getEventTargetUuid();
	public boolean maybeTrigger(){
		return handler.handleOn(eventTarget);
	}
	public void markDead(){
		this.setStatus(EventOccurrenceStatus.DEAD);
	}
	public void expire(){
		this.setStatus(EventOccurrenceStatus.EXPIRED);
	}
	protected abstract void setStatus(EventOccurrenceStatus expired);
	public IEventHandler getEventHandler(){
		return handler;
	}
	public int hashCode(){
		return getEventSourceClass().hashCode();
	}
	@Override
	public boolean equals(Object other){
		if(other instanceof AbstractEventOccurrence){
			AbstractEventOccurrence te = (AbstractEventOccurrence) other;
			return te.getEventSourceClass().equals(getEventSourceClass()) && te.getEventTargetUuid().equals(getEventTargetUuid()) && getUuid().equals(te.getUuid());
		}else{
			return false;
		}
	}
	public Object getEventTarget(){
		return this.eventTarget;
	}
	public void prepareForDelivery(AbstractPersistence session){
		JavaMetaInfoMap map = Environment.getInstance().getMetaInfoMap();
		this.handler = map.getEventHandler(getHandlerUuid());
		if(handler == null){
			Class<? extends IEventHandler> clss = IntrospectionUtil.classForName(getHandlerUuid());
			handler = IntrospectionUtil.newInstance(clss);
		}
		handler.unmarshall(this.getPropertyValues(), session);
		eventTarget = Value.valueOf(getTargetValue(), session);
	}
	public Class<?> getEventSourceClass(){
		Class<?> class1 = Environment.getInstance().getMetaInfoMap().getClass(getEventTargetClassId());
		return class1;
	}
	public String getName(){
		return getDescription();
	}
	public String getUid(){
		return getUuid();
	}
	public int getObjectVersion(){
		return 0;
	}
	public static String uuid(Object target,String uuid){
		Long id = null;
		if(target instanceof IPersistentObject){
			id = ((IPersistentObject) target).getId();
		}
		Class<? extends Object> originalClass = IntrospectionUtil.getOriginalClass(target.getClass());
		NumlMetaInfo annotation = originalClass.getAnnotation(NumlMetaInfo.class);
		String eventTargetClassId = annotation == null ? originalClass.getName() : annotation.uuid();
		return uuid + "$" + id + "$" + eventTargetClassId;
	}
	public void prepareForDispatch(){
		this.setTargetValue(Value.valueOf(eventTarget));
		setPropertyValues(handler.marshall());
	}
	public String getDescription(){
		return handler.getClass().getSimpleName();
	}
	public Date getScheduledDate(){
		if(getNextOccurrenceScheduledFor() == null){
			return getFirstOccurrenceScheduledFor();
		}
		return getNextOccurrenceScheduledFor();
	}
	public Date scheduleNextOccurrence(){
		Date d = getEventHandler().scheduleNextOccurrence();
		setNextOccurrenceScheduledFor(d);
		return d;
	}
}