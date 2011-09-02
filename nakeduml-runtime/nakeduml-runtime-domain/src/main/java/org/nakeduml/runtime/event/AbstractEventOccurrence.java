package org.nakeduml.runtime.event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.environment.JavaMetaInfoMap;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.persistence.AbstractPersistence;

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
	protected abstract Integer getEventTargetClassId();
	public abstract Long getId();
	public abstract EventOccurrenceStatus getStatus();
	public abstract int getRetryCount();
	public abstract void incrementRetryCount();
	protected abstract Value getTargetValue();
	protected abstract Collection<PropertyValue> getPropertyValues();
	public abstract String getHandlerUuid();
	public abstract boolean targetIsEntity();
	public abstract void setId(Long id);
	protected abstract void setTargetValue(Value valueOf);
	protected abstract void setPropertyValues(Collection<PropertyValue> collection);
	public String getUuid(){
		return getHandlerUuid() + "$" + getEventTargetId() + "$" + getEventTargetClassId();
	}
	public abstract Long getEventTargetId();
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
			return te.getEventSourceClass().equals(getEventSourceClass()) && te.getEventTargetId().equals(getEventTargetId()) && getUuid().equals(te.getUuid());
		}else{
			return false;
		}
	}
	// For Mocking Purposes
	public Object getEventTarget(){
		return this.eventTarget;
	}
	public void prepareForDelivery(AbstractPersistence session){
		JavaMetaInfoMap map = Environment.getMetaInfoMap();
		this.handler = map.getEventHandler(getHandlerUuid());
		if(handler==null){
			handler=(IEventHandler)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(getHandlerUuid()));
		}
		handler.unmarshall(this.getPropertyValues(), session);
		eventTarget = Value.valueOf(getTargetValue(), session);
	}
	public Class<?> getEventSourceClass(){
		return Environment.getMetaInfoMap().getClass(getEventTargetClassId());
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
		int eventTargetClassId = Environment.getMetaInfoMap().getNakedUmlId(IntrospectionUtil.getOriginalClass(target.getClass()));
		return uuid + "$" + id + "$" + eventTargetClassId;
	}
	public void prepareForDispatch(){
		this.setTargetValue(Value.valueOf(eventTarget));
		setPropertyValues(handler.marshall());
	}
	public String getDescription(){
		return handler.getClass().getSimpleName();
	}
}