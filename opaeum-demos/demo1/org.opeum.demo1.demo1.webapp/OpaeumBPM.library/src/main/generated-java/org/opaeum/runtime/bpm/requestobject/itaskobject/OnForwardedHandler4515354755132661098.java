package org.opaeum.runtime.bpm.requestobject.itaskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnForwardedHandler4515354755132661098 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private Participant forwardedBy;
	private Participant forwardedTo;
	private boolean isEvent;

	/** Constructor for OnForwardedHandler4515354755132661098
	 * 
	 * @param forwardedBy 
	 * @param forwardedTo 
	 * @param isEvent 
	 */
	public OnForwardedHandler4515354755132661098(Participant forwardedBy, Participant forwardedTo, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setForwardedBy(forwardedBy);
		setForwardedTo(forwardedTo);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnForwardedHandler4515354755132661098
	 */
	public OnForwardedHandler4515354755132661098() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2435983182155799959l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg")
	public Participant getForwardedBy() {
		return this.forwardedBy;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=938298307904775797l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg")
	public Participant getForwardedTo() {
		return this.forwardedTo;
	}
	
	public String getHandlerUuid() {
		return "252060@_NdLN8K0OEeCK48ywUpk_rg";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onForwarded";
	}
	
	public boolean handleOn(Object t) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnForwardedOccurrence(getForwardedBy(),getForwardedTo());
		} else {
			target.onForwarded(getForwardedBy(),getForwardedTo());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(5405405098671388300l, Value.valueOf(this.getForwardedBy())));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(3907720224420364138l, Value.valueOf(this.getForwardedTo())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setForwardedBy(Participant forwardedBy) {
		this.forwardedBy=forwardedBy;
	}
	
	public void setForwardedTo(Participant forwardedTo) {
		this.forwardedTo=forwardedTo;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==5405405098671388300l ) {
				this.setForwardedBy((Participant)Value.valueOf(p.getValue(),persistence));
			} else {
				if ( p.getId()==3907720224420364138l ) {
					this.setForwardedTo((Participant)Value.valueOf(p.getValue(),persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}