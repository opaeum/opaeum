package org.opaeum.runtime.bpm.requestobject.itaskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnSkippedHandler5307650706078019150 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private Participant skippedBy;

	/** Constructor for OnSkippedHandler5307650706078019150
	 * 
	 * @param skippedBy 
	 * @param isEvent 
	 */
	public OnSkippedHandler5307650706078019150(Participant skippedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setSkippedBy(skippedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnSkippedHandler5307650706078019150
	 */
	public OnSkippedHandler5307650706078019150() {
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
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onSkipped";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4506161608879891029l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg")
	public Participant getSkippedBy() {
		return this.skippedBy;
	}
	
	public boolean handleOn(Object t) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnSkippedOccurrence(getSkippedBy());
		} else {
			target.onSkipped(getSkippedBy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(6103866618185670366l, Value.valueOf(this.getSkippedBy())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setSkippedBy(Participant skippedBy) {
		this.skippedBy=skippedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==6103866618185670366l ) {
				this.setSkippedBy((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}