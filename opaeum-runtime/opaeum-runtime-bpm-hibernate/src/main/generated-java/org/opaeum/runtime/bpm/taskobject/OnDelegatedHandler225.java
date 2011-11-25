package org.opaeum.runtime.bpm.taskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.bpm.BusinessRole;
import org.opaeum.runtime.bpm.TaskObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnDelegatedHandler225 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole _delegatedBy;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole _delegatedTo;

	/** Default constructor for OnDelegatedHandler225
	 */
	public OnDelegatedHandler225() {
	}
	
	/** Constructor for OnDelegatedHandler225
	 * 
	 * @param _delegatedBy 
	 * @param _delegatedTo 
	 * @param isEvent 
	 */
	public OnDelegatedHandler225(BusinessRole _delegatedBy, BusinessRole _delegatedTo, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setDelegatedBy(_delegatedBy);
		setDelegatedTo(_delegatedTo);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public BusinessRole getDelegatedBy() {
		return this._delegatedBy;
	}
	
	public BusinessRole getDelegatedTo() {
		return this._delegatedTo;
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
		return "OpaeumLibraryForBPM::TaskObject::onDelegated";
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
		result.add(new PropertyValue(227, Value.valueOf(this.getDelegatedBy())));
		result.add(new PropertyValue(226, Value.valueOf(this.getDelegatedTo())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setDelegatedBy(BusinessRole _delegatedBy) {
		this._delegatedBy=_delegatedBy;
	}
	
	public void setDelegatedTo(BusinessRole _delegatedTo) {
		this._delegatedTo=_delegatedTo;
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
			
				case 226:
					this.setDelegatedTo((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 227:
					this.setDelegatedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}