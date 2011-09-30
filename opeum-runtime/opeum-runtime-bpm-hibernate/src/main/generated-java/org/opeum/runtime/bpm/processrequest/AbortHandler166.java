package org.opeum.runtime.bpm.processrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opeum.runtime.bpm.ProcessRequest;
import org.opeum.runtime.environment.marshall.PropertyValue;
import org.opeum.runtime.environment.marshall.Value;
import org.opeum.runtime.event.ICallEventHandler;
import org.opeum.runtime.persistence.AbstractPersistence;

public class AbortHandler166 implements ICallEventHandler {
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for AbortHandler166
	 */
	public AbortHandler166() {
	}
	
	/** Constructor for AbortHandler166
	 * 
	 * @param isEvent 
	 */
	public AbortHandler166(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_4zDaYK0wEeCTTvcJZSDicw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::ProcessRequest::abort";
	}
	
	public boolean handleOn(Object t) {
		ProcessRequest target = (ProcessRequest)t;
		if ( isEvent ) {
			return target.consumeAbortOccurrence();
		} else {
			target.abort();
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
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
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
			}
		
		}
	}

}