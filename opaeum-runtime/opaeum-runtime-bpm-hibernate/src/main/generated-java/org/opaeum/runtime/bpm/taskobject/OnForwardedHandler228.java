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

public class OnForwardedHandler228 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole _forwardedBy;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole _forwardedTo;

	/** Default constructor for OnForwardedHandler228
	 */
	public OnForwardedHandler228() {
	}
	
	/** Constructor for OnForwardedHandler228
	 * 
	 * @param _forwardedBy 
	 * @param _forwardedTo 
	 * @param isEvent 
	 */
	public OnForwardedHandler228(BusinessRole _forwardedBy, BusinessRole _forwardedTo, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setForwardedBy(_forwardedBy);
		setForwardedTo(_forwardedTo);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public BusinessRole getForwardedBy() {
		return this._forwardedBy;
	}
	
	public BusinessRole getForwardedTo() {
		return this._forwardedTo;
	}
	
	public String getHandlerUuid() {
		return "252060@_NdLN8K0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::TaskObject::onForwarded";
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnForwardedOccurrence(getForwardedBy(),getForwardedTo());
		} else {
			target.onForwarded(getForwardedBy(),getForwardedTo());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(230, Value.valueOf(this.getForwardedBy())));
		result.add(new PropertyValue(229, Value.valueOf(this.getForwardedTo())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setForwardedBy(BusinessRole _forwardedBy) {
		this._forwardedBy=_forwardedBy;
	}
	
	public void setForwardedTo(BusinessRole _forwardedTo) {
		this._forwardedTo=_forwardedTo;
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
			
				case 229:
					this.setForwardedTo((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 230:
					this.setForwardedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}