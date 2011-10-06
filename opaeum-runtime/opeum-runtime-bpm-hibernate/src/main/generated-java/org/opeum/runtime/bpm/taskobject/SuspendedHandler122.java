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

public class SuspendedHandler122 implements ICallEventHandler {
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole suspendedBy;

	/** Default constructor for SuspendedHandler122
	 */
	public SuspendedHandler122() {
	}
	
	/** Constructor for SuspendedHandler122
	 * 
	 * @param suspendedBy 
	 * @param isEvent 
	 */
	public SuspendedHandler122(BusinessRole suspendedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSuspendedBy(suspendedBy);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_ug7_QK0NEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpeumLibraryForBPM::TaskObject::suspended";
	}
	
	public BusinessRole getSuspendedBy() {
		return this.suspendedBy;
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeSuspendedOccurrence(getSuspendedBy());
		} else {
			target.suspended(getSuspendedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(123, Value.valueOf(this.getSuspendedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSuspendedBy(BusinessRole suspendedBy) {
		this.suspendedBy=suspendedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 123:
					this.setSuspendedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}