package org.opaeum.runtime.bpm.request.abstractrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class StartHandler2809139800710243133 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for StartHandler2809139800710243133
	 * 
	 * @param isEvent 
	 */
	public StartHandler2809139800710243133(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for StartHandler2809139800710243133
	 */
	public StartHandler2809139800710243133() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_-PsMoIoaEeCPduia_-NbFw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::AbstractRequest::start";
	}
	
	public boolean handleOn(Object t) {
		AbstractRequest target = (AbstractRequest)t;
		if ( isEvent ) {
			return target.consumeStartOccurrence();
		} else {
			target.start();
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}