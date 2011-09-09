package org.nakeduml.runtime.bpm.taskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.nakeduml.runtime.bpm.BusinessRole;
import org.nakeduml.runtime.bpm.TaskObject;
import org.nakeduml.runtime.environment.marshall.PropertyValue;
import org.nakeduml.runtime.environment.marshall.Value;
import org.nakeduml.runtime.event.IEventHandler;
import org.nakeduml.runtime.persistence.AbstractPersistence;

public class OnDelegatedHandler771 implements IEventHandler {
	private BusinessRole delegatedBy;
	private BusinessRole delegatedTo;
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;

	/** Default constructor for OnDelegatedHandler771
	 */
	public OnDelegatedHandler771() {
	}
	
	/** Constructor for OnDelegatedHandler771
	 * 
	 * @param delegatedBy 
	 * @param delegatedTo 
	 * @param isEvent 
	 */
	public OnDelegatedHandler771(BusinessRole delegatedBy, BusinessRole delegatedTo, boolean isEvent) {
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
		return "OpiumBPM.library.uml@_EE4B0K0OEeCK48ywUpk_rg";
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
		result.add(new PropertyValue(773, Value.valueOf(this.getDelegatedBy())));
		result.add(new PropertyValue(772, Value.valueOf(this.getDelegatedTo())));
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
			
				case 772:
					this.setDelegatedTo((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
				case 773:
					this.setDelegatedBy((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}