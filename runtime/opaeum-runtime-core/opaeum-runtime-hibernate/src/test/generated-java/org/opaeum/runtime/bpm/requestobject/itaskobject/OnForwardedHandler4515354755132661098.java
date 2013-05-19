package org.opaeum.runtime.bpm.requestobject.itaskobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnForwardedHandler4515354755132661098 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private IParticipant forwardedBy;
	private IParticipant forwardedTo;
	private boolean isEvent;

	/** Constructor for OnForwardedHandler4515354755132661098
	 * 
	 * @param forwardedBy 
	 * @param forwardedTo 
	 * @param isEvent 
	 */
	public OnForwardedHandler4515354755132661098(IParticipant forwardedBy, IParticipant forwardedTo, boolean isEvent) {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2200217753584300366l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg")
	public IParticipant getForwardedBy() {
		return this.forwardedBy;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5281332883183458783l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg")
	public IParticipant getForwardedTo() {
		return this.forwardedTo;
	}
	
	public String getHandlerUuid() {
		return "252060@_NdLN8K0OEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::ITaskObject::onForwarded";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		ITaskObject target = (ITaskObject)t;
		if ( isEvent ) {
			return target.consumeOnForwardedOccurrence(getForwardedBy(),getForwardedTo());
		} else {
			target.onForwarded(getForwardedBy(),getForwardedTo());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(5405405098671388300l, Value.valueOf(this.getForwardedBy(),env)));
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(3907720224420364138l, Value.valueOf(this.getForwardedTo(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setForwardedBy(IParticipant forwardedBy) {
		this.forwardedBy=forwardedBy;
	}
	
	public void setForwardedTo(IParticipant forwardedTo) {
		this.forwardedTo=forwardedTo;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==5405405098671388300l ) {
				this.setForwardedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
				if ( p.getId()==3907720224420364138l ) {
					this.setForwardedTo((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
				} else {
				
				}
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}