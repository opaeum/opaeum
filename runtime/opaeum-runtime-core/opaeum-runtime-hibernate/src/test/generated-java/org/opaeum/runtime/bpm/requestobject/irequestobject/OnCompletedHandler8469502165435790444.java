package org.opaeum.runtime.bpm.requestobject.irequestobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnCompletedHandler8469502165435790444 implements ICallEventHandler {
	private IParticipant completedBy;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for OnCompletedHandler8469502165435790444
	 * 
	 * @param completedBy 
	 * @param isEvent 
	 */
	public OnCompletedHandler8469502165435790444(IParticipant completedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setCompletedBy(completedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnCompletedHandler8469502165435790444
	 */
	public OnCompletedHandler8469502165435790444() {
	}

	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=412315788950580691l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg")
	public IParticipant getCompletedBy() {
		return this.completedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_a_82cK0OEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IRequestObject::onCompleted";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IRequestObject target = (IRequestObject)t;
		if ( isEvent ) {
			return target.consumeOnCompletedOccurrence(getCompletedBy());
		} else {
			target.onCompleted(getCompletedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(2231883736722554878l, Value.valueOf(this.getCompletedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setCompletedBy(IParticipant completedBy) {
		this.completedBy=completedBy;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==2231883736722554878l ) {
				this.setCompletedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}