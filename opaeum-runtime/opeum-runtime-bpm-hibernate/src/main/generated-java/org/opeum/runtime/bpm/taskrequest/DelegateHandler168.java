package org.opeum.runtime.bpm.taskrequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opeum.runtime.bpm.BusinessRole;
import org.opeum.runtime.bpm.TaskRequest;
import org.opeum.runtime.environment.marshall.PropertyValue;
import org.opeum.runtime.environment.marshall.Value;
import org.opeum.runtime.event.ICallEventHandler;
import org.opeum.runtime.persistence.AbstractPersistence;

public class DelegateHandler168 implements ICallEventHandler {
	private boolean isEvent;
	private Date firstOccurrenceScheduledFor;
	private BusinessRole delegate;

	/** Default constructor for DelegateHandler168
	 */
	public DelegateHandler168() {
	}
	
	/** Constructor for DelegateHandler168
	 * 
	 * @param delegate 
	 * @param isEvent 
	 */
	public DelegateHandler168(BusinessRole delegate, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setDelegate(delegate);
		this.isEvent=isEvent;
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public BusinessRole getDelegate() {
		return this.delegate;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_0lAQAIoaEeCPduia_-NbFw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpeumLibraryForBPM::TaskRequest::delegate";
	}
	
	public boolean handleOn(Object t) {
		TaskRequest target = (TaskRequest)t;
		if ( isEvent ) {
			return target.consumeDelegateOccurrence(getDelegate());
		} else {
			target.delegate(getDelegate());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(169, Value.valueOf(this.getDelegate())));
		result.add(new PropertyValue(-6, Value.valueOf(isEvent)));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setDelegate(BusinessRole delegate) {
		this.delegate=delegate;
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
			
				case 169:
					this.setDelegate((BusinessRole)Value.valueOf(p.getValue(),persistence));
				break;
			
			}
		
		}
	}

}