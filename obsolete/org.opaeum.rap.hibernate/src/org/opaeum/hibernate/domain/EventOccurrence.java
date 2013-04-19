package org.opaeum.hibernate.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.AbstractEventOccurrence;
import org.opaeum.runtime.event.EventOccurrenceStatus;
import org.opaeum.runtime.event.IEventHandler;

@Entity()
@Table(name = "opaeum_abstract_event")
public class EventOccurrence extends AbstractEventOccurrence{
	private static final long serialVersionUID = 8920092390485701533L;
	@Enumerated(EnumType.STRING)
	private EventOccurrenceStatus status;
	@Id
	@GeneratedValue()
	Long id;
	@Basic
	@Column(name = "event_target_id")
	private Long eventTargetId;
	@Basic
	@Column(name = "event_target_class_id")
	private String eventTargetClassId;
	private int retryCount;
	@Basic
	private String triggerUuid;
	@Lob
	private byte[] propertyValues;
	@Lob
	private byte[] targetValue;
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstOccurrenceScheduledFor;
	public EventOccurrence(){
		super();
	}
	public EventOccurrence(Object target,IEventHandler handler){
		super(target, handler);
		if(target instanceof Collection){
			Collection<?> targets = (Collection<?>) target;
			for(Object object:targets){
				if(object instanceof IPersistentObject){
					this.eventTargetId += ((IPersistentObject) object).getId();//Just used to generate a uuid
				}
				if(eventTargetClassId == null){
					this.eventTargetClassId = Environment.getInstance().getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(target.getClass()));
				}
			}
		}else{
			this.eventTargetClassId = Environment.getInstance().getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(target.getClass()));
			if(target instanceof IPersistentObject){
				this.eventTargetId = ((IPersistentObject) target).getId();
			}
		}
		this.triggerUuid = handler.getHandlerUuid();
		this.firstOccurrenceScheduledFor = handler.getFirstOccurrenceScheduledFor();
	}
	public Date getFirstOccurrenceScheduledFor(){
		return firstOccurrenceScheduledFor;
	}
	public Long getEventTargetId(){
		return eventTargetId;
	}
	public String getEventTargetClassId(){
		return this.eventTargetClassId;
	}
	public Long getId(){
		return id;
	}
	public EventOccurrenceStatus getStatus(){
		return status;
	}
	public int getRetryCount(){
		return retryCount;
	}
	public void incrementRetryCount(){
		retryCount++;
	}
	@Override
	public void setId(Long id){
		this.id = id;
	}
	@Override
	protected Value getTargetValue(){
		byte[] value = targetValue;
		return read(value);
	}
	@SuppressWarnings("unchecked")
	protected <T>T read(byte[] value){
		try{
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(value));
			return (T) is.readObject();
		}catch(IOException e){
			throw new RuntimeException(e);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	@Override
	protected Collection<PropertyValue> getPropertyValues(){
		return read(propertyValues);
	}
	@Override
	public String getHandlerUuid(){
		return triggerUuid;
	}
	@Override
	protected void setStatus(EventOccurrenceStatus expired){
		this.status = expired;
	}
	@Override
	protected void setTargetValue(Value value){
		ByteArrayOutputStream out = write(value);
		this.targetValue = out.toByteArray();
	}
	protected ByteArrayOutputStream write(Object value){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			objectOutputStream.writeObject(value);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return out;
	}
	@Override
	protected void setPropertyValues(Collection<PropertyValue> collection){
		propertyValues = write(collection).toByteArray();
	}
}
