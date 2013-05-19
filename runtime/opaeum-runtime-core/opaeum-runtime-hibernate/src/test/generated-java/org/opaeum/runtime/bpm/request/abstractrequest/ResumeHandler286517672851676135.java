package org.opaeum.runtime.bpm.request.abstractrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ResumeHandler286517672851676135 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for ResumeHandler286517672851676135
	 * 
	 * @param isEvent 
	 */
	public ResumeHandler286517672851676135(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ResumeHandler286517672851676135
	 */
	public ResumeHandler286517672851676135() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_qwWfEIoaEeCPduia_-NbFw";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::AbstractRequest::resume";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		AbstractRequest target = (AbstractRequest)t;
		if ( isEvent ) {
			return target.consumeResumeOccurrence();
		} else {
			target.resume();
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}