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

public class OnActivatedHandler1858551074079807460 implements ICallEventHandler {
	private IParticipant activatedBy;
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;

	/** Constructor for OnActivatedHandler1858551074079807460
	 * 
	 * @param activatedBy 
	 * @param isEvent 
	 */
	public OnActivatedHandler1858551074079807460(IParticipant activatedBy, boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		setActivatedBy(activatedBy);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for OnActivatedHandler1858551074079807460
	 */
	public OnActivatedHandler1858551074079807460() {
	}

	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4519459331390750289l,uuid="252060@_YslssK0OEeCK48ywUpk_rg")
	public IParticipant getActivatedBy() {
		return this.activatedBy;
	}
	
	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_XbPZkK0OEeCK48ywUpk_rg";
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::requestobject::IRequestObject::onActivated";
	}
	
	public boolean handleOn(Object t, AbstractPersistence persistence) {
		IRequestObject target = (IRequestObject)t;
		if ( isEvent ) {
			return target.consumeOnActivatedOccurrence(getActivatedBy());
		} else {
			target.onActivated(getActivatedBy());
			return true;
		}
	}
	
	public boolean isIsEvent() {
		return this.isEvent;
	}
	
	public Collection<PropertyValue> marshall(Environment env) {
		Collection result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent,env)));
		result.add(new PropertyValue(3572050351186706778l, Value.valueOf(this.getActivatedBy(),env)));
		return result;
	}
	
	public Date scheduleNextOccurrence(Object object) {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setActivatedBy(IParticipant activatedBy) {
		this.activatedBy=activatedBy;
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==3572050351186706778l ) {
				this.setActivatedBy((IParticipant)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),(InternalHibernatePersistence)persistence);
			}
		}
	}

}