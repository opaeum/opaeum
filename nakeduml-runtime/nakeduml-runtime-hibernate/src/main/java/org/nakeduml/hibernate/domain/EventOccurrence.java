package org.nakeduml.hibernate.domain;

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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.AbstractEventOccurrence;
import org.nakeduml.runtime.event.EventOccurrenceStatus;
import org.nakeduml.runtime.event.IEventHandler;

@Entity()
@Table(name = "numl_abstract_event")
public class EventOccurrence extends AbstractEventOccurrence{
	private static final long serialVersionUID = 8920092390485701533L;
	@Enumerated(EnumType.STRING)
	private EventOccurrenceStatus status;
	@Id
	Long id;
	@Basic
	@Column(name = "event_target_id")
	private Long eventTargetId;
	@Basic
	@Column(name = "event_target_class_id")
	private Integer eventTargetClassId;
	private int retryCount;
	@Basic
	private String triggerUuid;
	@Lob
	private byte[] propertyValues;
	@Basic
	private boolean targetIsEntity;
	@Lob
	private byte[] targetValue;
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstOccurrenceScheduledFor;
	public EventOccurrence(){
		super();
	}
	public EventOccurrence(Object target,IEventHandler handler){
		super(target, handler);
		this.eventTargetClassId = Environment.getMetaInfoMap().getNakedUmlId(IntrospectionUtil.getOriginalClass(target.getClass()));
		if(target instanceof IPersistentObject){
			this.targetIsEntity=true;
			this.eventTargetId = ((IPersistentObject) target).getId();
		}
		this.eventTargetClassId = Environment.getMetaInfoMap().getNakedUmlId(IntrospectionUtil.getOriginalClass(target.getClass()));
		this.triggerUuid = handler.getHandlerUuid();
		this.firstOccurrenceScheduledFor = handler.getFirstOccurrenceScheduledFor();
	}
	public Date getFirstOccurrenceScheduledFor(){
		return firstOccurrenceScheduledFor;
	}
	public Long getEventTargetId(){
		return eventTargetId;
	}
	public Integer getEventTargetClassId(){
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
	public boolean targetIsEntity(){
		return this.targetIsEntity;
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
	protected <T> T read(byte[] value){
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
		this.targetValue= out.toByteArray();
	}
	protected ByteArrayOutputStream write(Object value){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			ObjectOutputStream objectOutputStream= new ObjectOutputStream(out);
			objectOutputStream.writeObject(value);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return out;
	}
	@Override
	protected void setPropertyValues(Collection<PropertyValue> collection){
		write(collection);
	}
}
