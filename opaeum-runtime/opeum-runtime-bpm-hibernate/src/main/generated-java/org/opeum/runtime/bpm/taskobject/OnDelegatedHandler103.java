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

public class OnDelegatedHandler103 implements ICallEventHandler {
	private BusinessRole delegatedTo;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole delegatedBy;

	/** Default constructor for OnDelegatedHandler103
	 */
	public OnDelegatedHandler103() {
	}
	
	/** Constructor for OnDelegatedHandler103
	 * 
	 * @param delegatedBy 
	 * @param delegatedTo 
	 * @param isEvent 
	 */
	public OnDelegatedHandler103(BusinessRole delegatedBy, BusinessRole delegatedTo, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setDelegatedBy(delegatedBy);
		setDelegatedTo(delegatedTo);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public BusinessRole getDelegatedBy() {
		return this.delegatedBy;
	}
	
	public BusinessRole getDelegatedTo() {
		return this.delegatedTo;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_EE4B0K0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::TaskObject::onDelegated";
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnDelegatedOccurrence(getDelegatedBy(),getDelegatedTo());
		} else {
			target.onDelegated(getDelegatedBy(),getDelegatedTo());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(104, Value.valueOf(this.getDelegatedBy())));
		result.add(new PropertyValue(105, Value.valueOf(this.getDelegatedTo())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setDelegatedBy(BusinessRole delegatedBy) {
		this.delegatedBy=delegatedBy;
	}
	
	public void setDelegatedTo(BusinessRole delegatedTo) {
		this.delegatedTo=delegatedTo;
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
			
				case 105:
					this.setDelegatedTo((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 104:
					this.setDelegatedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}