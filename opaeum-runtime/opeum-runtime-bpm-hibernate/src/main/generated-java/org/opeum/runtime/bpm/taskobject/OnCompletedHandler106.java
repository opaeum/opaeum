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

public class OnCompletedHandler106 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole completedBy;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for OnCompletedHandler106
	 */
	public OnCompletedHandler106() {
	}
	
	/** Constructor for OnCompletedHandler106
	 * 
	 * @param completedBy 
	 * @param isEvent 
	 */
	public OnCompletedHandler106(BusinessRole completedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setCompletedBy(completedBy);
		this.isEvent=isEvent;
	}

	public BusinessRole getCompletedBy() {
		return this.completedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_a_82cK0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpeumLibraryForBPM::TaskObject::onCompleted";
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnCompletedOccurrence(getCompletedBy());
		} else {
			target.onCompleted(getCompletedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(107, Value.valueOf(this.getCompletedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setCompletedBy(BusinessRole completedBy) {
		this.completedBy=completedBy;
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
			
				case 107:
					this.setCompletedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}