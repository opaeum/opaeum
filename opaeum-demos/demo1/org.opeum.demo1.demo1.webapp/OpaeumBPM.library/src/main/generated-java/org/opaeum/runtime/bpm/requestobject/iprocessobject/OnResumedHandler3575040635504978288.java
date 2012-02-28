package org.opaeum.runtime.bpm.requestobject.iprocessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnResumedHandler3575040635504978288 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private String resumedBy;

	/** Constructor for OnResumedHandler3575040635504978288
	 * 
	 * @param resumedBy 
	 * @param isEvent 
	 */
	public OnResumedHandler3575040635504978288(String resumedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setResumedBy(resumedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnResumedHandler3575040635504978288
	 */
	public OnResumedHandler3575040635504978288() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_xd064EuBEeGElKTCe2jfDw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IProcessObject::onResumed";
	}
	
	public String getResumedBy() {
		return this.resumedBy;
	}
	
	public boolean handleOn(Object t) {
		IProcessObject target = (IProcessObject)t;
		if ( isEvent ) {
			return target.consumeOnResumedOccurrence(getResumedBy());
		} else {
			target.onResumed(getResumedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(787801760248251908l, Value.valueOf(this.getResumedBy())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setResumedBy(String resumedBy) {
		this.resumedBy=resumedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==787801760248251908l ) {
				this.setResumedBy((String)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}