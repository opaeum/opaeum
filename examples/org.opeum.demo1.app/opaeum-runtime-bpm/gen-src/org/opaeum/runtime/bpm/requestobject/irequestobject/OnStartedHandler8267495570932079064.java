package org.opaeum.runtime.bpm.requestobject.irequestobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class OnStartedHandler8267495570932079064 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IParticipant startedBy;

	/** Constructor for OnStartedHandler8267495570932079064
	 * 
	 * @param startedBy 
	 * @param isEvent 
	 */
	public OnStartedHandler8267495570932079064(IParticipant startedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setStartedBy(startedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnStartedHandler8267495570932079064
	 */
	public OnStartedHandler8267495570932079064() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_iBCwEK0NEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IRequestObject::onStarted";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5056953987125697465l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg")
	public IParticipant getStartedBy() {
		return this.startedBy;
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IRequestObject target = (IRequestObject)t;
		if ( isEvent ) {
			return target.consumeOnStartedOccurrence(getStartedBy());
		} else {
			target.onStarted(getStartedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(2842620541558802106l, Value.valueOf(this.getStartedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setStartedBy(IParticipant startedBy) {
		this.startedBy=startedBy;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==2842620541558802106l ) {
				this.setStartedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}