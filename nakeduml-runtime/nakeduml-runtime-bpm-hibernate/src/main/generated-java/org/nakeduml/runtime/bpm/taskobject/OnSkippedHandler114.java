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

public class OnSkippedHandler114 implements ICallEventHandler {
	private boolean isEvent;
	private BusinessRole skippedBy;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for OnSkippedHandler114
	 */
	public OnSkippedHandler114() {
	}
	
	/** Constructor for OnSkippedHandler114
	 * 
	 * @param skippedBy 
	 * @param isEvent 
	 */
	public OnSkippedHandler114(BusinessRole skippedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSkippedBy(skippedBy);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_fdkRQK0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpiumLibraryForBPM::TaskObject::onSkipped";
	}
	
	public BusinessRole getSkippedBy() {
		return this.skippedBy;
	}
	
	public boolean handleOn(Object t) {
		TaskObject target = (TaskObject)t;
		if ( isEvent ) {
			return target.consumeOnSkippedOccurrence(getSkippedBy());
		} else {
			target.onSkipped(getSkippedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(115, Value.valueOf(this.getSkippedBy())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSkippedBy(BusinessRole skippedBy) {
		this.skippedBy=skippedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			switch ( p.getId() ) {
				case -6:
					this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
				break;
			
				case 115:
					this.setSkippedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}