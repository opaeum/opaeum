package org.opaeum.runtime.bpm.request.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class ClaimHandler7608483210400824401 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for ClaimHandler7608483210400824401
	 * 
	 * @param isEvent 
	 */
	public ClaimHandler7608483210400824401(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for ClaimHandler7608483210400824401
	 */
	public ClaimHandler7608483210400824401() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_Nk_isIobEeCPduia_-NbFw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::request::TaskRequest::claim";
	}
	
	public boolean handleOn(Object t) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeClaimOccurrence();
		} else {
			target.claim();
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