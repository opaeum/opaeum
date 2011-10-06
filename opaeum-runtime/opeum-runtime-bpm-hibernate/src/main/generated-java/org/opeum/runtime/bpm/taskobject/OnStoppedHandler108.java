package org.opeum.runtime.bpm.taskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opeum.runtime.bpm.BusinessRole;
import org.opeum.runtime.bpm.TaskObject;
import org.opeum.runtime.environment.marshall.PropertyValue;
import org.opeum.runtime.environment.marshall.Value;
import org.opeum.runtime.event.ICallEventHandler;
import org.opeum.runtime.persistence.AbstractPersistence;

public class OnStoppedHandler108 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole stoppedBy;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for OnStoppedHandler108
	 */
	public OnStoppedHandler108() {
	}
	
	/** Constructor for OnStoppedHandler108
	 * 
	 * @param stoppedBy 
	 * @param isEvent 
	 */
	public OnStoppedHandler108(BusinessRole stoppedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setStoppedBy(stoppedBy);
		this.isEvent=isEvent;
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
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpeumLibraryForBPM::TaskObject::onStopped";
	}
	
	public BusinessRole getStoppedBy() {
		return this.stoppedBy;
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnStoppedOccurrence(getStoppedBy());
		} else {
			target.onStopped(getStoppedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(109, Value.valueOf(this.getStoppedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setStoppedBy(BusinessRole stoppedBy) {
		this.stoppedBy=stoppedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 109:
					this.setStoppedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}