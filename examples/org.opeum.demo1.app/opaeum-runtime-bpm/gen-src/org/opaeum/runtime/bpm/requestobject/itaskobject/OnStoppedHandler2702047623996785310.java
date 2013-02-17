package org.opaeum.runtime.bpm.requestobject.itaskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnStoppedHandler2702047623996785310 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IParticipant stoppedBy;

	/** Constructor for OnStoppedHandler2702047623996785310
	 * 
	 * @param stoppedBy 
	 * @param isEvent 
	 */
	public OnStoppedHandler2702047623996785310(IParticipant stoppedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setStoppedBy(stoppedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnStoppedHandler2702047623996785310
	 */
	public OnStoppedHandler2702047623996785310() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_zwcxEK0NEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onStopped";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9183657584887279827l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg")
	public IParticipant getStoppedBy() {
		return this.stoppedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnStoppedOccurrence(getStoppedBy());
		} else {
			target.onStopped(getStoppedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(6681754435738713602l, Value.valueOf(this.getStoppedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setStoppedBy(IParticipant stoppedBy) {
		this.stoppedBy=stoppedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==6681754435738713602l ) {
				this.setStoppedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}