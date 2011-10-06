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

public class OnActivatedHandler110 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole activatedBy;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for OnActivatedHandler110
	 */
	public OnActivatedHandler110() {
	}
	
	/** Constructor for OnActivatedHandler110
	 * 
	 * @param activatedBy 
	 * @param isEvent 
	 */
	public OnActivatedHandler110(BusinessRole activatedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setActivatedBy(activatedBy);
		this.isEvent=isEvent;
	}

	public BusinessRole getActivatedBy() {
		return this.activatedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_XbPZkK0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpeumLibraryForBPM::TaskObject::onActivated";
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnActivatedOccurrence(getActivatedBy());
		} else {
			target.onActivated(getActivatedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(111, Value.valueOf(this.getActivatedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setActivatedBy(BusinessRole activatedBy) {
		this.activatedBy=activatedBy;
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
			
				case 111:
					this.setActivatedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}