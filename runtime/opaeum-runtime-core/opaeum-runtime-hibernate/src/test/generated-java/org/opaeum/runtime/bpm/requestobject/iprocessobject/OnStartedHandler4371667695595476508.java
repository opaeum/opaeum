package org.opaeum.runtime.bpm.requestobject.iprocessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnStartedHandler4371667695595476508 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IBusinessRole startedBy;

	/** Constructor for OnStartedHandler4371667695595476508
	 * 
	 * @param startedBy 
	 * @param isEvent 
	 */
	public OnStartedHandler4371667695595476508(IBusinessRole startedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setStartedBy(startedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnStartedHandler4371667695595476508
	 */
	public OnStartedHandler4371667695595476508() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_v7N4oEuBEeGElKTCe2jfDw";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IProcessObject::onStarted";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7771026382181224421l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw")
	public IBusinessRole getStartedBy() {
		return this.startedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IProcessObject target = (IProcessObject)t;
		if ( isEvent ) {
			return target.consumeOnStartedOccurrence(getStartedBy());
		} else {
			target.onStarted(getStartedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(8825299842246312l, Value.valueOf(this.getStartedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setStartedBy(IBusinessRole startedBy) {
		this.startedBy=startedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==8825299842246312l ) {
				this.setStartedBy((IBusinessRole)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}