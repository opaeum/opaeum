package org.nakeduml.runtime.bpm.taskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.nakeduml.runtime.bpm.BusinessRole;
import org.nakeduml.runtime.bpm.TaskObject;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.ICallEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class OnClaimedHandler101 implements ICallEventHandler {
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole claimedBy;

	/** Default constructor for OnClaimedHandler101
	 */
	public OnClaimedHandler101() {
	}
	
	/** Constructor for OnClaimedHandler101
	 * 
	 * @param claimedBy 
	 * @param isEvent 
	 */
	public OnClaimedHandler101(BusinessRole claimedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setClaimedBy(claimedBy);
		this.isEvent=isEvent;
	}

	public BusinessRole getClaimedBy() {
		return this.claimedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_qTa18K0NEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::TaskObject::onClaimed";
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnClaimedOccurrence(getClaimedBy());
		} else {
			target.onClaimed(getClaimedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(102, Value.valueOf(this.getClaimedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setClaimedBy(BusinessRole claimedBy) {
		this.claimedBy=claimedBy;
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
			
				case 102:
					this.setClaimedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}