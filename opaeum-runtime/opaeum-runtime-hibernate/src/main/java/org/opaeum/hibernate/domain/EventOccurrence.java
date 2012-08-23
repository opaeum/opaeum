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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.AbstractEventOccurrence;
import org.opaeum.runtime.event.EventOccurrenceStatus;
import org.opaeum.runtime.event.IEventHandler;

@Entity(name="EventOccurrence")
@org.hibernate.annotations.Entity(dynamicUpdate=true, dynamicInsert=true)
@Table(name = "opaeum_abstract_event")
@AccessType(	"field")
@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="event_occurrence",table="hi_value")

public class EventOccurrence extends AbstractEventOccurrence{
	private static final long serialVersionUID = 8920092390485701533L;
	@Enumerated(EnumType.STRING)
	private EventOccurrenceStatus status;
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	Long id;
	@Basic
	@Column(name = "event_target_uuid")
	private String eventTargetUuid;
	@Basic
	@Column(name = "event_target_class_id")
	private String eventTargetClassId;
	@Column(name="retry_count")
	private int retryCount;
	@Basic
	@Column(name="trigger_uuid")

	private String triggerUuid;
	@Lob
	@Column(name="property_values")
	private byte[] propertyValues;
	@Lob
	@Column(name="target_value")
	private byte[] targetValue;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="first_occurrence_scheduled_for")
	private Date firstOccurrenceScheduledFor;
	@Column(name="next_occurrence_scheduled_for")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextOccurrenceScheduledFor;

	public EventOccurrence(){
		super();
	}
	public EventOccurrence(Object target,IEventHandler handler){
		super(target, handler);
		if(target instanceof Collection){
			Collection<?> targets = (Collection<?>) target;
			for(Object object:targets){
				if(object instanceof IPersistentObject){
					this.eventTargetUuid= ((IPersistentObject) object).getUid();//Just used to generate a uuid
				}
				if(eventTargetClassId == null){
					this.eventTargetClassId = Environment.getInstance(). getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(object.getClass()));
				}
			}
		}else{
			this.eventTargetClassId = Environment.getInstance().getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(target.getClass()));
			if(target instanceof IPersistentObject){
				this.eventTargetUuid = ((IPersistentObject) target).getUid();
			}
		}
		this.triggerUuid = handler.getHandlerUuid();
		this.firstOccurrenceScheduledFor = handler.getFirstOccurrenceScheduledFor();
	}
	public Date getFirstOccurrenceScheduledFor(){
		return firstOccurrenceScheduledFor;
	}
	public String getEventTargetUuid(){
		return eventTargetUuid;
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
		System.out.println(getEventHandler().getClass());
		System.out.println(propertyValues[0] + "," +propertyValues[1]);
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
		System.out.println(getEventHandler().getClass());
		System.out.println(propertyValues[0] + "," +propertyValues[1]);
		getPropertyValues();
	}
	public Date getNextOccurrenceScheduledFor(){
		return nextOccurrenceScheduledFor;
	}
	public void setNextOccurrenceScheduledFor(Date nextOccurrenceScheduledFor){
		this.nextOccurrenceScheduledFor = nextOccurrenceScheduledFor;
	}
}
